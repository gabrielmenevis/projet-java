package Personnages;

import Objets.Inventaire;
import Objets.Objet;
import Objets.ObjetConsommable;

public abstract class Personnage {

    private String nom;
    private int pv;
    private int max_pv;
    private int p_attaque;
    private int p_charisme;
    private boolean charme;
    private Inventaire inventaire;

    public Personnage(String nom, int pv, int max_pv, int p_attaque, int p_charisme) {
        super();
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

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setP_attaque(int p_attaque) {
        this.p_attaque = p_attaque;
    }

    public int getPCharisme() {
        return this.p_charisme;
    }

    public Inventaire getInventaire(){
        return this.inventaire;
    }

    // méthode perte de PV à utiliser dans la fonction combats
    public void perdrePV(int degats) {
        this.pv -= degats;
        if (this.pv < 0) this.pv = 0; // Assure que les PV ne tombent pas en dessous de 0
    }
     public void perdrePA(int degats) {
        this.p_attaque -= degats;
        if (this.p_attaque < 0) this.p_attaque = 0;
     }
      public void perdrePC(int degats) {
        this.p_charisme -= degats;
        if (this.p_charisme < 0) this.p_charisme = 0;
      }

    public void gagnerPA(int ajout_pa) {
        this.p_attaque += ajout_pa;
        if (this.p_attaque < 0) this.p_attaque = 1;
    }

    public void gagnerPV(int ajout_pv) {
        this.pv += ajout_pv;
        if (this.pv < 0) this.pv = 1;
    }

    public void gagnerPC(int ajout_pc) {
        this.p_charisme += ajout_pc;
        if (this.p_charisme < 0) this.p_charisme = 1;
    }

    public boolean isCharme() {
        return charme;
    }

    public void setCharme(boolean charme) {
        this.charme = charme;
    }

    public void ouvrirInventaire(){
        Objet objetChoisi;
        objetChoisi = this.inventaire.menuInventaire();
        // utiliser l'objet si le joueur en a fait le choix
        if(objetChoisi != null){
            boolean restant;
            objetChoisi.utilisationObjet(this);
            System.out.println();
            if(objetChoisi instanceof ObjetConsommable){ // les messages affichés dépendent de la nature de l'objet
                restant = this.inventaire.enleverObjet((ObjetConsommable) objetChoisi);
                if(!restant){
                    System.out.println("Vous n'avez plus de " + objetChoisi.getNom() + "...");
                    System.out.println("J'espère que vous en avez bien profité.");
                    System.out.println();
                }
            }
            else{
                System.out.println("Alors, ça valait le coup de " + objetChoisi.getArticleDefini() + " " + objetChoisi.getNom() + " ?");
                System.out.println("On n'en trouve pas à tous les coins de rue. En fait vous n'en trouverez plus.");
                System.out.println("Mais pas de panique, vous pouvez toujours consulter son effet depuis l'inventaire, si le coeur vous en dit. Je dis ça, je dis rien...");
                System.out.println();
            }
        }
    }

    public void donnerObjet(PNJ pnj){

        Objet objet;
        boolean pnjAccepte;

        System.out.println("Une lueur d'avidité scintille dans les yeux de " + pnj.decrire());

        objet = this.inventaire.menuDonnerObjet();

        if(objet == null){
            System.out.println();
            System.out.println(pnj.decrire() + " vous fixe avec dédain.");
            System.out.println("En même temps, pourquoi lui promettre quelque chose si c'est pour ne rien lui donner ?");
            System.out.println();
        }

        else{ //TODO: ce qui se passe quand on donne l'objet au PNJ
            System.out.println("Vous proposez " + objet.getArticleDefini() + " " + objet.getNom());
            pnjAccepte = pnj.recevoirObjet(objet);
            if(pnjAccepte){
                System.out.println("Super !");
            }
            else{
                System.out.println("On dirait que ça n'a pas trop plu... Vous gardez " + objet.getArticleDefini() + " " + objet.getNom() + " dans votre inventaire.");
            }
        }
    }

    public abstract void presentation();
}


