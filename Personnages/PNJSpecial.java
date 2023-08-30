package Personnages;

import java.io.IOException;
import java.util.Scanner;

import Combats.Combat;
import Objets.Objet;

/**
 * Représentation d'un PNJ spécial dans le jeu. Hérite de PNJ. À la différence des PNJ non spéciaux, le PNJ
 * spécial n'est pas aléatoire. Certains menus d'interaction et certains comportements diffèrent.
 * En particulier, le PNJ spécial possède un attribut qui indique le nom de l'objet demandé, un autre qui indique
 * s'il est satisfait (il a obtenu l'objet demandé) et un indice à afficher au joueur quand il est vaincu ou
 * satisfait.
 */
public class PNJSpecial extends PNJ {

    private String indice;
    private String nomObjetDemande;
    private boolean satisfait;

    /**
     * Constructeur de PNJSpecial. Prend les paramètres à passer au constructeur de PNJ, plus l'indice,
     * le nom de l'objet demandé. Initialise satisfait à false.
     * @param nom : le nom du PNJ
     * @param type : le type du PNJ
     * @param article : l'article à accoler au type
     * @param indice : l'indice à afficher au joueur
     * @param textePNJ : la réplique du PNJ
     * @param max_pv : le nombre maximum de PV
     * @param p_attaque : le nombre de PA
     * @param p_charisme : le nombre de PC
     * @param nomObjetDemande : le nom de l'objet voulu par le PNJ
     */
    public PNJSpecial(String nom, String type, String article, String indice, String textePNJ, int max_pv, int p_attaque, int p_charisme, String nomObjetDemande) {
        super(nom, type, article, textePNJ, max_pv, p_attaque, p_charisme);  // Ici, max_pv est passé deux fois à cause du constructeur de Personnage
        this.indice = indice;
        this.nomObjetDemande = nomObjetDemande;
        this.satisfait = false;
    }

    public String getIndice() {
        return indice;
    }

    public String getNomObjetDemande(){
        return this.nomObjetDemande;
    }

    public boolean getSatisfait(){
        return this.satisfait;
    }

    public void setIndice(String indice){
        this.indice = indice;
    }

    public void setNomObjetDemande(String nomObjetDemande){
        this.nomObjetDemande = nomObjetDemande;
    }

    public void setSatisfait(boolean satisfait){
        this.satisfait = satisfait;
    }

    /**
     * Menu d'interaction avec le PNJ et choix du joueur. Surcharge la méthode de la classe parente pour définir
     * un comportement particulier si le PNJ spécial est vaincu ou satisfait. Dans ce cas, le PNJ spécial 
     * n'est plus agressif avec le joueur et celui-ci peut lui demander de répéter l'indice.
     * @return le choix du joueur ("1" pour parler/répéter l'indice, "2" pour donner un objet, "3" pour se 
     * battre, "4" pour s'en aller)
     */
    public String menuPNJ(){

        String choix = "";
        Scanner sc = new Scanner(System.in);

        // le PNJ n'est ni satisfait ni vaincu, l'interaction est la même que pour les PNJ aléatoires
        if(!this.satisfait && !this.getVaincu()){
            choix = super.menuPNJ();
        }

        else{
            System.out.println();
            System.out.println(this.decrire() + " vous dit : 'Ah, c'est toi ! Comment puis-je t'aider ?'");
            while(!choix.equals("1") && !choix.equals("2") && !choix.equals("3") && !choix.equals("4")){
                System.out.println("À vous de réagir :");
                System.out.println("1 - Répète-moi l'indice !");
                System.out.println("2 - Tiens, c'est pour toi !");
                System.out.println("3 - Baston !");
                System.out.println("4 - Annuler");
                choix = sc.nextLine();
            }
        }

        return choix;
    }

    /**
     * Surcharge la méthode de la classe parente pour implémenter un comportement particulier si le PNJ spécial est 
     * satisfait ou vaincu. Dans ce cas, il donne son indice. Sinon, il appelle la méthode de la classe parente.
     * @return true si le joueur a résolu l'énigme (seulement possible avec Boss), sinon false
     */
    public boolean parler(){
        if(!this.satisfait && !this.getVaincu()){ // si ni satisfait ni vaincu, répète sa phrase
            super.parler();
        }
        else{ // sinon, répète l'indice
            this.donnerIndice();
        }
        return false;
    }

    /**
     * Lancement du combat avec le PNJ spécial. Surcharge la méthode de la classe parente pour afficher des 
     * messages personnalisés si le joueur a déjà vaincu ou satisfait le PNJ. Sinon, le combat se lance.
     * @param perso : le joueur que le PNJ doit combattre
     * @return true si le joueur meurt au combat, sinon false
     * @throws IOException : erreur lecture de fichier dans Combat.lancerCombat()
     */
    public boolean combattre(Personnage perso) throws IOException{

        boolean mourir;
        Combat combat = new Combat(perso, this);

        if(this.satisfait){ // le joueur a donné l'objet demandé au PNJ
            System.out.println();
            System.out.println(this.decrire() + " vous a donné un indice... Vous n'allez pas lui taper dessus, quand même.");
            System.out.println("Mais peut-être que " + this.getNom() + " peut vous répéter l'indice si vous lui parlez gentiment.");
            System.out.println();
            mourir = false;
        }

        else if(this.getVaincu()){ // le joueur a battu le PNJ
            System.out.println();
            System.out.println(this.decrire() + " vous implore :");
            System.out.println("Pitié... Je t'ai déjà dit ce que tu voulais savoir...");
            System.out.println("Il te suffit de me parler si tu veux que je répète l'indice...");
            System.out.println();
            mourir = false;
        }

        else{ // sinon le combat se lance
            mourir = combat.lancerCombat();
            if(this.getVaincu() || this.isCharme()){
                System.out.println();
                System.out.println("Incroyable ! Vous avez vaincu " + this.decrire());
                this.donnerIndice();
            }
        }

        return mourir;
    }

    /**
     * Détermine si le PNJ spécial accepte un objet donné. Le PNJ spécial n'accepte que l'objet dont le nom
     * est égal à son attribut nomObjetDemande
     * @param o : objet donné
     * @return true si le PNJ accepte l'objet, sinon false
     */
    public boolean recevoirObjet(Objet o){
        
        boolean accepte;

        // si l'objet est l'objet demandé
        if(o.getNom().equals(this.nomObjetDemande)){
            accepte = true;
            this.satisfait = true; // le PNJ est maintenant satisfait
            System.out.println();
            System.out.println(o.getArticleIndefini() + " " + o.getNom() + " !!!!! J'en ai toujours rêvé...");
            this.donnerIndice(); // le PNJ donne l'indice
            System.out.println();
        }

        else{
            accepte = false;
            System.out.println();
            System.out.println("Tu crois que " + o.getArticleIndefini() + " " + o.getNom() + " de pacotille va m'amadouer ??");
            System.out.println("Non, une seule chose m'intéresse : " + this.nomObjetDemande);
            System.out.println();
        }

        return accepte;
    }

    /**
     * Le PNJ spécial donne son indice au joueur et attend confirmation que celui-ci l'a bien lu.
     */
    public void donnerIndice(){

        Scanner sc = new Scanner(System.in);
        String choix = "";

        System.out.println();
        System.out.println(this.decrire() + " vous dit :");
        System.out.println("Écoute attentivement ce que j'ai à te dire... Tu en auras besoin pour retrouver ce que tu cherches.");
        System.out.println(this.indice);

        while(!(choix.equals("1")) && !(choix.equals("2"))){
            System.out.println();
            System.out.println("Tu as bien compris ?");
            System.out.println("1 - Oui");
            System.out.println("2 - Non");
            choix = sc.nextLine();
        }

        if(choix.equals("1")){
            System.out.println();
            System.out.println("Bien. N'hésite pas à revenir me voir...");
            System.out.println();
        }
        else{
            System.out.println();
            System.out.println("Ne fais pas l'idiot. Mais tu peux toujours me parler si tu veux entendre mon indice à nouveau...");
            System.out.println();
        }
    }
    
}
