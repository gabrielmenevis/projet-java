package Personnages;

public abstract class Personnage {

    private String nom;
    private int pv = 0;
    private int max_pv = 0;
    private int p_attaque = 0;
    private int p_charisme = 0;

    public Personnage(String nom, int pv, int max_pv, int p_attaque, int p_charisme) {
        super();
        this.nom = nom;
        this.pv = pv;
        this.max_pv = max_pv;
        this.p_attaque = p_attaque;
        this.p_charisme = p_charisme;
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

    //     Pour verifier dans le terminal
    public void presentation() {
        System.out.println("Je suis le personnage " + this.nom + ", j'ai " + this.pv + " points de vie, " + this.p_attaque + " points d'attaque et " + this.p_charisme + " points de charisme.");
    }

}
