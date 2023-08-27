package Personnages;

public class Sportif extends Personnage {

    public Sportif(String nom){
        super(nom,10,10,1000,1);
    }

    public Sportif(String nom, int pv, int max_pv, int p_attaque, int p_charisme) {
        super(nom, pv, max_pv, p_attaque, p_charisme);
    }
        
    public void presentation() {
        System.out.println(getNom() + ", " + getPV() + " points de vie sur " + getMaxPV() + ", " + getPAttaque() + 
        " points d'attaque et " + getPCharisme() + " point de charisme.");

    }

    public static void main(String[] args) {

        Sportif sportif = new Sportif("Sam?", 5, 10, 10, 1);

        sportif.presentation();
    }

}


