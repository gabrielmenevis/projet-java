package Objets;

import java.util.Random;


public class ObjetConsommable extends Objet {

    private int probaSpawn;

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

    // détermine si l'objet apparaît dans une pièce en fonction de sa probabilité de spawn
    public boolean apparaitre(){
        Random r = new Random();
        if(r.nextInt(10) >= this.probaSpawn){
            return false;
        } else return true;
    }
    
}
