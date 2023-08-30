package Personnages;

/**
 * Un des quatre personnages que le joueur peut choisir. 
 * La courgette est une extension de la classe Personnage.
 * Elle hérite donc de ses paramètres.
 * Chacun des quatre personnages au choix se démarquent par leurs caractéristiques différentes 
 * L'attaque (p_attaque) ou le charisme (p_charisme) varient selon le personnage
 */
public class Courgette extends Personnage {

    /**
     * C'est le premier constructeur. Il ne prend que nom comme paramètre.
     * Il fait appel au constructeur de la classe parent Personnage,
     * soit pv, pv_max, p_attaque et p_charisme
     * @param nom : le nom du personnage
     */
    public Courgette(String nom){
        super (nom,100,100,60,15);
    }

    /**
     * Deuxième constructeur avec des paramètres personnalisés
     * @param nom : nom à donner au personnage
     * @param pv : nombre initial de PV
     * @param max_pv : nombre max de PV
     * @param p_attaque : nombre de PA
     * @param p_charisme : nombre de PC
     */
    public Courgette(String nom, int pv, int max_pv, int p_attaque, int p_charisme) {
        super(nom, pv, max_pv, p_attaque, p_charisme);
    }

    /**
     * Méthode pour afficher une présentation de la courgette. Appelée au début du jeu et au réveil dans l'infirmerie
     */
    public void presentation() {
        System.out.println();
        System.out.println("Rebonjour " + getNom() + ", vous êtes une courgette radieuse. Vous avez actuellement " + getPV() + " points de vie sur " + getMaxPV() + ", " + getPAttaque() + 
        " point d'attaque et " + getPCharisme() + " points de charisme.");
        System.out.println();
    }
    
}

