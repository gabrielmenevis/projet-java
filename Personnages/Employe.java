package Personnages;

public class Employe extends Personnage {

    public Employe(String nom){
        super(nom,5,10,4,4);
    }
    
    public Employe(String nom, int pv, int max_pv, int p_attaque, int p_charisme) {
        super(nom, pv, max_pv, p_attaque, p_charisme);
    }

    public void presentation() {
        System.out.println();
        System.out.println("Rebonjour employé.e" + getNom() + ", vous avez actuellement " + getPV() + " points de vie sur " + getMaxPV() + ", " + getPAttaque() + 
        " points d'attaque et " + getPCharisme() + " points de charisme.");
        System.out.println();
    }

    public static void main(String[] args) {

        Employe employe = new Employe("à définir",2, 8, 4, 3);

        employe.presentation();
    }
    
    }