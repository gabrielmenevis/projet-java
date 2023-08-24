package Personnages;

public class Artiste extends Personnage{

    public Artiste(String nom, int pv, int max_pv, int p_attaque, int p_charisme) {
        super(nom, pv, max_pv, p_attaque, p_charisme);
    }

    public void presentation() {
        System.out.println("nom artiste " + getNom() + ", " + getPV() + " point de vie sur " + getMaxPV() + ", " + getPAttaque() + 
        " points d'attaque et " + getPCharisme() + " points de charisme.");
    }

    public static void main(String[] args) {

        Artiste artiste = new Artiste("à définir", 1, 6, 6, 4);

        artiste.presentation();
    }
    
}