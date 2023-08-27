package Objets;

import Personnages.Personnage;

public class ObjetUnique extends Objet {

    private boolean dejaPris;

    public ObjetUnique(String nom, String articleDefini, String articleIndefini, int valeurAjoutee, String attributTouche, String utilisation, String effet){
        super(nom, articleDefini, articleIndefini, valeurAjoutee, attributTouche, utilisation, effet);
        this.dejaPris = false;
    }

    public boolean getDejaPris(){
        return this.dejaPris;
    }

    public void setDejaPris(boolean dejaPris){
        this.dejaPris = dejaPris;
    }

    // les objets uniques apparaissent à coup sûr
    public boolean apparaitre(){
        if(!this.dejaPris){
            return true;
        }
        else{
            return false;
        }
    }


    public boolean utilisationObjet(Personnage perso){
        boolean mourir;
        mourir = super.utilisationObjet(perso);
        perso.getInventaire().rangerObjetUtilise(this);
        // this.utilise = true;
        return mourir;
    }
  
  
    public String menuObjetTrouve(){

        String rep;

        // appel à la méthode de la classe parente
        rep = super.menuObjetTrouve();

        // si l'objet est utilisé ou rangé dans l'inventaire, il n'est plus dispo dans la salle
        if(rep.equals("2") || rep.equals("3")){
            this.dejaPris = true;
        }

        // on retourne le choix du joueur
        return rep;
    }

    // public void donner(){

    // }
    
}
