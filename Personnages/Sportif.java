package Personnages;

public class Sportif extends Personnage {

    public Sportif(String nom, int pv, int max_pv, int p_attaque, int p_charisme) {
        super(nom, pv, max_pv, p_attaque, p_charisme);
    }
        
    public void presentation() {
        System.out.println( getNom() + ", " + getPV() + " points de vie, " + getPAttaque() + 
        " points d'attaque et " + getPCharisme() + " point de charisme.");

    }

    public static void main(String[] args) {

        Sportif sportif = new Sportif("Sam", 4, 10, 10, 1);

        sportif.presentation();
    }

}


