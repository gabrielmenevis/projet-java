package Personnages;

public class Courgette extends Personnage {

    public Courgette(String nom, int pv, int max_pv, int p_attaque, int p_charisme) {
        super(nom, pv, max_pv, p_attaque, p_charisme);
    }

    public void presentation() {
        System.out.println("nom courgette " + getNom() + ", " + getPV() + " points de vie, " + getPAttaque() + 
        " points d'attaque et " + getPCharisme() + " points de charisme.");
    }

    public static void main(String[] args) {

        Courgette courgette = new Courgette("à définir", 6, 12, 1, 2);

        courgette.presentation();
    }
    
}

