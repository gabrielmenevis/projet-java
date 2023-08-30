package Personnages;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import Combats.Combat;
import Objets.*;

/**
 * Représentation d'un PNJ (personnage non joueur) dans le jeu. Hérite de Personnage. Possède en plus 
 * une réplique à affiche (textePNJ), un type et un article, une base de PA utilisée en combat, un indicateur
 * pour savoir s'il a déjà été vaincu.
 * Ses méthodes lui permettent notamment de se présenter, de présenter un menu d'interaction, de dire sa réplique;
 * de combattre le joueur ou de l'attraper en train de fouiller et de recevoir un objet.
 */
public class PNJ extends Personnage {

    private String textePNJ;
    private String article;
    private String type;
    private int base_p_attaque;
    private boolean vaincu;

    /**
     * Constructeur du PNJ. Prend les paramètres passés au constructeur de la classe parente et
     * d'autres paramètres spécifiques à la place. Initialise vaincu à false.
     * @param nom : le nom du PNJ
     * @param type : le type du PNJ
     * @param article : l'article à accoler au type
     * @param textePNJ : la réplique du PNJ
     * @param max_pv : le nombre maximum de PV
     * @param p_attaque : le nombre de PA
     * @param p_charisme : le nombre de PC
     */
    public PNJ(String nom, String type, String article, String textePNJ, int max_pv, int p_attaque, int p_charisme) {
        super(nom, max_pv, p_attaque, p_charisme);  // Ici, max_pv est passé deux fois à cause du constructeur de Personnage
        this.type = type;
        this.article = article;
        this.textePNJ = textePNJ;
        this.base_p_attaque = p_attaque;
        this.vaincu = false;
    }

    public String getType(){
        return this.type;
    }

    public String getTextePNJ() {
        return textePNJ;
    }

    public String getArticle(){
        return this.article;
    }

    public boolean getVaincu(){
        return this.vaincu;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setTextePNJ(String textePNJ){
        this.textePNJ = textePNJ;
    }

    public void setArticle(String article){
        this.article = article;
    }
    
    public void setVaincu(boolean vaincu){
        this.vaincu = vaincu;
    }

    /**
     * Le PNJ se présente en donnant son nom, son type et sa réplique.
     */
    public void presentation() {
        System.out.println("Je suis " + this.getNom() + " " + this.article + " " + this.type + ". " + this.textePNJ);
    }

    /**
     * Menu d'interaction avec le PNJ et choix du joueur.
     * @return le choix du joueur ("1" pour parler encore, "2" pour donner un objet, "3" pour se battre,
     * "4" pour tourner les talons)
     */
    public String menuPNJ(){

        String choix = "";
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println(this.decrire() + " vous dit : '" + this.textePNJ + "'");
        System.out.println("Non mais ! " + this.getNom() + " vous provoque ou quoi ?");

        while(!choix.equals("1") && !choix.equals("2") && !choix.equals("3") && !choix.equals("4")){
            System.out.println("À vous de réagir :");
            System.out.println("1 - Répète un peu pour voir...");
            System.out.println("2 - Tiens, c'est pour toi !");
            System.out.println("3 - Tu veux te battre c'est ça ??!");
            System.out.println("4 - Annuler");
            choix = sc.nextLine();
        }
        
        return choix;
    }

    /**
     * Le PNJ dit sa réplique.
     * @return : true si le joueur a résolu l'énigme (seulement possible avec Boss), sinon false
     */
    public boolean parler(){
        System.out.println();
        System.out.println(this.getNom() + " vous dit : '" + this.textePNJ + "'");
        System.out.println();
        return false;
    }

    /**
     * Lancement du combat avec le PNJ. Si le PNJ est déjà vaincu, il supplie le joueur et le combat n'a pas
     * lieu.
     * @param perso : le joueur qui lance le combat
     * @return true si le joueur est mort en combat, sinon false
     * @throws IOException : si problème de lecture du fichier dans Combat.lancerCombat()
     */
    public boolean combattre(Personnage perso) throws IOException{

        Combat combat = new Combat(perso, this);
        boolean mourir;

        if(this.vaincu){ // le joueur a déjà vaincu le PNJ
            System.out.println();
            System.out.println("Vous avez déjà vaincu " + this.getNom() + " qui vous implore : 'Pitié... Laisse-moi tranquille...'");
            mourir = false;
        }
        else{ // sinon le combat se lance
            mourir = combat.lancerCombat();
        }

        return mourir;
    }

    /**
     * Détermine si le PNJ accepte un objet donné. Le PNJ de base n'accepte pas les objets uniques. Il a une chance
     * d'accepter un consommable basée sur la rareté de celui-ci.
     * PNJSpecial surcharge cette méthode pour n'accepter qu'un objet unique précis.
     * Boss surcharge cette méthode pour n'accepter aucun objet.
     * @param o : objet donné
     * @return true si le PNJ accepte l'objet, sinon false
     */
    public boolean recevoirObjet(Objet o){

        boolean accepte;
        int tirage;
        Random r = new Random();

        System.out.println();
        // les PNJs aléatoires n'acceptent pas les objets uniques
        if(o instanceof ObjetUnique){
            System.out.println(this.getNom() + " vous dit : 'Peuh ! ça ne m'intéresse pas.'");
            accepte = false;
        }

        else{
            // probabilité d'accepter l'objet proportionnelle à la rareté de l'objet
            tirage = r.nextInt(10);
            if(tirage >= ((ObjetConsommable)o).getProbaSpawn()){
                accepte = true;
                System.out.println(this.getNom() + " vous dit : '" + o.getArticleIndefini() + " " + o.getNom() + " ? Merci, il ne fallait pas...'");
            }
            else{
                accepte = false;
                System.out.println(this.getNom() + " vous dit : 'Peuh ! ça ne m'intéresse pas.'");
            }
        }

        return accepte;
    }

    /**
     * Menu d'interaction qui apparaît quand le PNJ trouve le joueur en train de fouiller. Le joueur peut
     * tenter de calmer le PNJ en lui donnant un objet (appel à perso.donnerObjet()) ou choisir de combattre le PNJ.
     * @param perso : le joueur attrapé par le PNJ
     * @throws IOException : si une erreur de lecture se produit dans Combat.lancerCombat()
     */
    public void attraper(Personnage perso) throws IOException{

        String choix = "";
        Scanner sc = new Scanner(System.in);
        Combat combat;
        boolean corrompre, mourirAuCombat;

        // boucle de saisie du choix
        while(!(choix.equals("1")) && !(choix.equals("2"))){
            System.out.println(this.decrire() + " s'approche de vous d'un air suspicieux... Vous avez peut-être encore une chance d'échapper au combat :");
            System.out.println("1 - Tenter de corrompre " + this.getNom() + " en lui donnant un objet.");
            System.out.println("2 - Combat");
            choix = sc.nextLine();
        }

        switch(choix){

            // corrompre le PNJ en lui donnant un objet
            case "1":
            corrompre = perso.donnerObjet(this);
            System.out.println();
            if(corrompre){ // le PNJ a accepté l'objet
                System.out.println("Ouf ! Vous l'avez échappé belle !");
                System.out.println(this.decrire() + " s'est laissé(e) amadouer par votre 'cadeau'... Croisez les doigts pour que ça marche la prochaine fois.");
            }
            else{ // le PNJ n'accepte pas l'objet, lancer le combat
                System.out.println("Aïe aïe aïe... Pas le choix, il faudra combattre " + this.getNom());
                combat = new Combat(perso, this);
                mourirAuCombat = combat.lancerCombat();
                if(!mourirAuCombat){ // si le joueur est vainqueur
                    System.out.println();
                    System.out.println("Avec la raclée que vous lui avez mise, " + this.decrire() + " ne viendra plus vous embêter de si tôt...");
                    System.out.println();
                }
            }
            System.out.println();
            break;

            // combattre le PNJ
            case "2":
            combat = new Combat(perso, this);
            mourirAuCombat = combat.lancerCombat();
            System.out.println();
            if(!mourirAuCombat){ // si le joueur est vainqueur
                System.out.println("Avec la raclée que vous lui avez mise, " + this.decrire() + " ne viendra plus vous embêter de si tôt...");
                System.out.println();
            }
            break;
        }
    }

    /**
     * Retourne une description simple du PNJ (nom, article, type)
     * @return : une chaîne qui décrit le PNJ
     */
    public String decrire(){
        return this.getNom() + " " + this.article + " " + this.type;
    }

    /**
     * Remet les PA du PNJ à leur quantité de base.
     */
    public void resetPAttaque(){
        setP_attaque(this.base_p_attaque);
    }
}
