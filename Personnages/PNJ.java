package Personnages;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import Combats.Combat;
import Objets.*;

public class PNJ extends Personnage {

    private String textePNJ;
    private String article;
    private String type;
    private int base_p_attaque;
    private boolean vaincu;

    public PNJ(String nom, String type, String article, String textePNJ, int max_pv, int p_attaque, int p_charisme) {
        super(nom, max_pv, max_pv, p_attaque, p_charisme);  // Ici, max_pv est passé deux fois à cause du constructeur de Personnage
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

    public void presentation() {
        System.out.println("Je suis " + this.getNom() + " " + this.article + " " + this.type + ". " + this.textePNJ);
    }

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

    public void parler(){
        System.out.println();
        System.out.println(this.getNom() + " vous dit : '" + this.textePNJ + "'");
        System.out.println();
    }

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

    public void attraper(Personnage perso) throws IOException{

        String choix = "";
        Scanner sc = new Scanner(System.in);
        Combat combat;
        boolean corrompre, mourirAuCombat;

        while(!(choix.equals("1")) && !(choix.equals("2"))){
            System.out.println(this.decrire() + " s'approche de vous d'un air suspicieux... Vous avez peut-être encore une chance d'échapper au combat :");
            System.out.println("1 - Tenter de corrompre " + this.getNom() + " en lui donnant un objet.");
            System.out.println("2 - Combat");
            choix = sc.nextLine();
        }

        switch(choix){

            case "1":
            corrompre = perso.donnerObjet(this);
            System.out.println();
            if(corrompre){
                System.out.println("Ouf ! Vous l'avez échappé belle !");
                System.out.println(this.decrire() + " s'est laissé(e) amadouer par votre 'cadeau'... Croisez les doigts pour que ça marche la prochaine fois.");
            }
            else{
                System.out.println("Aïe aïe aïe... Pas le choix, il faudra combattre " + this.getNom());
                combat = new Combat(perso, this);
                mourirAuCombat = combat.lancerCombat();
                if(!mourirAuCombat){
                    System.out.println();
                    System.out.println("Avec la raclée que vous lui avez mise, " + this.decrire() + " ne viendra plus vous embêter de si tôt...");
                    System.out.println();
                }
            }
            System.out.println();
            break;

            case "2":
            combat = new Combat(perso, this);
            mourirAuCombat = combat.lancerCombat();
            System.out.println();
            if(!mourirAuCombat){
                System.out.println("Avec la raclée que vous lui avez mise, " + this.decrire() + " ne viendra plus vous embêter de si tôt...");
                System.out.println();
            }
            break;
        }
    }

    public String decrire(){
        return this.getNom() + " " + this.article + " " + this.type;
    }

    public void resetPAttaque(){
        setP_attaque(this.base_p_attaque);
    }
}
