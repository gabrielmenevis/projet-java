package Objets;

import Personnages.Personnage;

public class ObjetUnique extends Objet {

    private boolean dejaPris;
    // private boolean utilise;

    public ObjetUnique(String nom, String articleDefini, String articleIndefini, int valeurAjoutee, String attributTouche, String utilisation, String effet){
        super(nom, articleDefini, articleIndefini, valeurAjoutee, attributTouche, utilisation, effet);
        this.dejaPris = false;
        // this.utilise = false;
    }

    public boolean getDejaPris(){
        return this.dejaPris;
    }

    // public boolean getUtilise(){
    //     return this.utilise;
    // }

    public void setDejaPris(boolean dejaPris){
        this.dejaPris = dejaPris;
    }

    // public void setUtilise(boolean utilise){
    //     this.utilise = utilise;
    // }

    // les objets uniques apparaissent à coup sûr
    public boolean apparaitre(){
        if(!this.dejaPris){
            return true;
        }
        else{
            return false;
        }
    }

    public void utilisationObjet(Personnage perso){
        super.utilisationObjet(perso);
        perso.getInventaire().rangerObjetUtilise(this);
        // this.utilise = true;
    }

    public String menuObjet(){

        String rep;

        // appel à la méthode de la classe parente
        rep = super.menuObjet();

        // si l'objet est utilisé ou rangé dans l'inventaire, il n'est plus dispo dans la salle
        if(rep.equals("2") || rep.equals("3")){
            this.dejaPris = true;
        }

        // on retourne le choix du joueur
        return rep;
    }
    
}
