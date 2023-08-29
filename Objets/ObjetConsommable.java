package Objets;

import java.util.Random;


/**
 * Représentation de l'objet consommable. Hérite de la classe abstraite Objet.
 * Implémente en plus une probabilité d'apparition (probaSpawn) et une méthode pour déterminer si
 * l'objet apparaît en fonction de cette probabilité.
 */
public class ObjetConsommable extends Objet {

    private int probaSpawn;

    /**
     * Constructeur pour l'objet consommable. Prend les paramètres reçus par Objet() et la probabilité de spawn.
     * @param nom : nom de l'objet
     * @param articleDefini : article défini à utiliser avec l'objet
     * @param articleIndefini : article indéfini à utiliser avec l'objet
     * @param valeurAjoutee : valeur positive ou négative quantifiant l'effet de l'objet sur une stat
     * @param attributTouche : statistique touchée par l'objet (PV, PA, PC)
     * @param utilisation : phrase à afficher lors de l'utilisation
     * @param effet : phrase à afficher pour décrire l'effet de l'objet
     * @param probaSpawn : la probabilité (sur 10) d'apparition de l'objet
     */
    public ObjetConsommable(String nom, String articleDefini, String articleIndefini, int valeurAjoutee, String attributTouche, String utilisation, String effet, int probaSpawn){
        super(nom, articleDefini, articleIndefini, valeurAjoutee, attributTouche, utilisation, effet);
        this.probaSpawn = probaSpawn;
    }

    public int getProbaSpawn(){
        return this.probaSpawn;
    }

    public void setProbaSpawn(int probaSpawn){
        this.probaSpawn = probaSpawn;
    }

    /**
     * Détermine si l'objet apparaît das une pièce en fonction de sa probabilité de spawn.
     * Probabilité : probaSpawn/10
     * @return vrai si l'objet apparaît, sinon faux
     */
    public boolean apparaitre(){
        Random r = new Random();
        if(r.nextInt(10) >= this.probaSpawn){
            return false;
        } else return true;
    }
    
}
