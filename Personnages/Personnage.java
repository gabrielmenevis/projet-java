package Personnages;

import Objets.Inventaire;
import Objets.Objet;

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
            restant = this.inventaire.enleverObjet(objetChoisi);
            if(!restant){
                System.out.println("Vous n'avez plus de " + objetChoisi.getNom() + "...");
                System.out.println("J'espère que vous en avez bien profité.");
                System.out.println();
            }
        }
    }
}


