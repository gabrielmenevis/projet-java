package Personnages;

//import Objets.Objet;
// import Salle;


public abstract class Personnage {

    private String nom;
    private int pv;
    private int max_pv;
    protected int p_attaque;
    private int p_charisme;
    private boolean charme;
    //private Inventaire inventaire = 0;

    public Personnage(String nom, int pv, int max_pv, int p_attaque, int p_charisme) {
        super();
        this.nom = nom;
        this.max_pv = max_pv;
        this.pv = max_pv;
        this.p_attaque = p_attaque;
        this.p_charisme = p_charisme;
        this.charme = false;
        //this.inventaire = new Inventaire();
    }

    public String getNom() {
        return nom;
    }

    public int getPV() {
        return pv;
    }

    public int getMaxPV() {
        return max_pv;
    }

    public int getPAttaque() {
        return p_attaque;
    }

    public int getPCharisme() {
        return p_charisme;
    }

    // méthode perte de PV à utiliser dans la fonction combats
    public void perdrePV(int degats) {
        this.pv -= degats;
        if (this.pv < 0) this.pv = 0; // Assure que les PV ne tombent pas en dessous de 0
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
}


