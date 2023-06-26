package Personnages;

public class Artiste extends Personnage{

    public Artiste(String nom, int pv, int max_pv, int p_attaque, int p_charisme) {
        super(nom, pv, max_pv, p_attaque, p_charisme);
    }

    public void presentation() {
        System.out.println("Je suis l'artiste " + getNom() + ", j'ai " + getPV() + " points de vie, " + getPAttaque() + 
        " points d'attaque et " + getPCharisme() + " points de charisme.");
    }

    
}