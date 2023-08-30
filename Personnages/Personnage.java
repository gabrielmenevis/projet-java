package Personnages;

import Objets.Inventaire;
import Objets.Objet;
import Objets.ObjetConsommable;
import Objets.ObjetUnique;

/**
 * Représentation d'un personnage dans le jeu. Le joueur est un Personnage. Les PNJ sont représentés par des
 * classes héritées de Personnage (PNJ ou PNJSPecial ou Boss qui héritent elles-mêmes de PNJ).
 * Un personnage a un nom, des statistiques (PV, PA, PC) et un inventaire.
 */
public abstract class Personnage {

    private String nom;
    private int pv;
    private int max_pv;
    private int p_attaque;
    private int p_charisme;
    private boolean charme; // indique si le personnage est charmé au cours d'un combat
    private Inventaire inventaire;

    /**
     * Constructeur de la classe Personnage. Crée un inventaire vide et initialise charme à false.
     * @param nom : nom du personnage
     * @param pv : nombre de PV initial du personnage
     * @param max_pv : nombre max initial de PV du personnage
     * @param p_attaque : nombre de PA initial du personnage
     * @param p_charisme : nombre de PC initial du personnage
     */
    public Personnage(String nom, int pv, int max_pv, int p_attaque, int p_charisme) {
        this.nom = nom;
        this.max_pv = max_pv;
        this.pv = max_pv;
        this.p_attaque = p_attaque;
        this.p_charisme = p_charisme;
        this.charme = false;
        this.inventaire = new Inventaire();
    }

    public String getNom() {
        return this.nom;
    }

    public int getPV() {
        return this.pv;
    }

    public int getMaxPV() {
        return this.max_pv;
    }

    public int getPAttaque() {
        return this.p_attaque;
    }

    public int getPCharisme() {
        return this.p_charisme;
    }

    public Inventaire getInventaire(){
        return this.inventaire;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setP_attaque(int p_attaque) {
        this.p_attaque = p_attaque;
    }

    public void setMaxPV(int maxPV){
        this.max_pv = maxPV;
    }

    public void setPV(int PV){
        this.pv = PV;
    }

    /**
     * Réinitialise le nombre de PV au nombre max de PV du personnage.
     */
    public void resetPV(){
        this.pv = this.max_pv;
    }

    /**
     * Méthode perte de PV à utiliser dans les combats ou lors de l'utilisation d'un objet.
     * Empêche que les PV tombent en-dessous de 0.
     * @param degats : nombre de PV en moins
     */
    public void perdrePV(int degats) {
        this.pv -= degats;
        if (this.pv <= 0) {
            this.pv = 0;
        }
    }

    /**
     * Méthode perte de PA à utiliser dans les combats ou lors de l'utilisation d'un objet.
     * Empêche que les PA tombent en-dessous de 0.
     * @param degats : nombre de PA en moins
     */
    public void perdrePA(int degats) {
        this.p_attaque -= degats;
        if (this.p_attaque < 0) {
            this.p_attaque = 0;
            System.out.println("vous n'avez plus de PA, il faut reprendre des forces...");
        }

    }

    /**
     * Méthode perte de PC à utiliser dans les combats ou lors de l'utilisation d'un objet.
     * Empêche que les PC tombent en-dessous de 0.
     * @param degats : nombre de PC en moins
     */
    public void perdrePC(int degats) {
        this.p_charisme -= degats;
        if (this.p_charisme < 0){
            this.p_charisme = 0;
            System.out.println("vous n'avez plus de PC...");

        } 
    }

    /**
     * Méthode gain de PA à utiliser dans les combats ou lors de l'utilisation d'un objet.
     * @param ajout_pa : nombre de PA en plus
     */
    public void gagnerPA(int ajout_pa) {
        this.p_attaque += ajout_pa;
        
    }

    /**
     * Méthode gain de PV à utiliser dans les combats ou lors de l'utilisation d'un objet.
     * Empêche que le nombre de PV dépasse le nombre max de PV
     * @param ajout_pv : nombre de PV en plus
     */
    public void gagnerPV(int ajout_pv) {
        this.pv += ajout_pv;
        if (this.pv > this.max_pv) this.pv = this.max_pv;
    }

    /**
     * Méthode gain de PC à utiliser dans les combats ou lors de l'utilisation d'un objet.
     * @param ajout_pc : nombre de PC en plus
     */
    public void gagnerPC(int ajout_pc) {
        this.p_charisme += ajout_pc;
    }

    /**
     * Indique si le personnage est charmé.
     * @return true si le personnage est charmé, sinon false
     */
    public boolean isCharme() {
        return charme;
    }

    public void setCharme(boolean charme) {
        this.charme = charme;
    }

    /**
     * Ouvre l'inventaire du personnage en appelant le menu inventaire et en utilisant l'objet choisi,
     * puis en l'enlevant de l'inventaire ou en le plaçant dans la liste des objets uniques utilisés.
     * @return true si le joueur meurt en utilisant l'objet, false sinon
     */
    public boolean ouvrirInventaire(){
        Objet objetChoisi;
        boolean mourir = false;
        objetChoisi = this.inventaire.menuInventaire();
        // utiliser l'objet si le joueur en a fait le choix
        if(objetChoisi != null){
            boolean restant;
            mourir = objetChoisi.utilisationObjet(this);
            System.out.println();
            if(objetChoisi instanceof ObjetConsommable){ // les messages affichés dépendent de la nature de l'objet
                restant = this.inventaire.enleverObjet(objetChoisi);
                if(!restant){
                    System.out.println("Vous n'avez plus de " + objetChoisi.getNom() + "...");
                    System.out.println("J'espère que vous en avez bien profité.");
                    System.out.println();
                }
            }
            else{
                System.out.println("Alors, ça valait le coup de " + objetChoisi.getArticleDefini() + " " + objetChoisi.getUtilisation() + " ?");
                System.out.println("On n'en trouve pas à tous les coins de rue. En fait vous n'en trouverez plus.");
                System.out.println("Mais pas de panique, vous pouvez toujours consulter son effet depuis l'inventaire, si le coeur vous en dit. Je dis ça, je dis rien...");
                System.out.println();
            }
        }

        return mourir;
    }

    /**
     * Menu pour donner un objet à un PNJ. Affiche des messages en fonction des choix effectués et gère 
     * le stock de l'objet donné dans l'inventaire.
     * @param pnj : le pnj auquel on veut donner un objet
     * @return true si le pnj accepte l'objet, false sinon
     */
    public boolean donnerObjet(PNJ pnj){

        Objet objet;
        boolean pnjAccepte, restant;        

        objet = this.inventaire.menuDonnerObjet();

        if(objet == null){
            System.out.println();
            System.out.println(pnj.decrire() + " vous fixe avec dédain.");
            System.out.println("En même temps, pourquoi lui promettre quelque chose si c'est pour ne rien lui donner ?");
            System.out.println();
            pnjAccepte = false;
        }

        else{
            System.out.println("Vous proposez " + objet.getArticleDefini() + " " + objet.getNom());
            pnjAccepte = pnj.recevoirObjet(objet);
            if(pnjAccepte){
                System.out.println("Super !");
                if(this.inventaire.getObjetsUniquesUtilises().contains(objet)){
                    ((ObjetUnique) objet).desequiper(this); // si l'objet était équipé, on enlève ses effets
                }
                restant = this.inventaire.enleverObjet(objet); // on enlève l'objet de l'inventaire
                if(!restant){ // c'était le dernier objet de ce type dans votre inventaire
                    System.out.println();
                    System.out.println("Vous n'avez plus de " + objet.getNom() + "... Le coeur sur la main, hein ?");
                    System.out.println();
                }
            }
            else{
                System.out.println();
                System.out.println("On dirait que ça n'a pas trop plu... Vous gardez " + objet.getArticleDefini() + " " + objet.getNom() + " dans votre inventaire.");
                System.out.println();
            }
        }

        return pnjAccepte;
    }

    /**
     * Affiche un message informatif sur les stats du personnage.
     */
    public void afficherStats(){
        System.out.println("Vous avez " + this.pv + "/" + this.max_pv + "PV, " + this.p_attaque + "PA et " + this.p_charisme + "PC.");
    }

    public abstract void presentation();

    public abstract void donnerIndice();
}


