package Personnages;

public class Sportif extends Personnage {

    public Sportif(String nom, int pv, int max_pv, int p_attaque, int p_charisme) {
        super(nom, pv, max_pv, p_attaque, p_charisme);
    }
        
    public void presentation() {
        System.out.println("Sportif " + getNom() + ", " + getPV() + " points de vie, " + getPAttaque() + 
        " points d'attaque et " + getPCharisme() + " points de charisme.");
    }


}


