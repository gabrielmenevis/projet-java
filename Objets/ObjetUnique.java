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

    // les objets uniques apparaissent à coup sûr s'ils ne sont pas déjà pris
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


    public boolean desequiper(Personnage perso){

        boolean mourir = false;

        // on annule l'effet de l'objet sur le personnage
        switch(this.getAttributTouche()){

            case "PV":
            if(this.getValeurAjoutee() > 0){
                perso.perdrePV(this.getValeurAjoutee());
            }
            else if(this.getValeurAjoutee() < 0){
                perso.gagnerPV(this.getValeurAjoutee() * -1); // inversion de la valeur ajoutée
            }
            break;

            case "PA":
            if(this.getValeurAjoutee() > 0){
                perso.perdrePA(this.getValeurAjoutee());
            }
            else if(this.getValeurAjoutee() < 0){
                perso.gagnerPA(this.getValeurAjoutee() * -1);
            }
            break;

            case "PC":
            if(this.getValeurAjoutee() > 0){
                perso.perdrePC(this.getValeurAjoutee());
            }
            else if(this.getValeurAjoutee() < 0){
                perso.gagnerPC(this.getValeurAjoutee() * -1);
            }
            break;
        }

        // information au joueur
        if(this.getValeurAjoutee() > 0){
            System.out.println("Vous perdez " + (this.getValeurAjoutee()) + " " + this.getAttributTouche() + " en déséquipant " + this.getArticleDefini() + " " + this.getNom());
        }
        else if(this.getValeurAjoutee() < 0){
            System.out.println("Vous gagnez " + (this.getValeurAjoutee()*-1) + " " + this.getAttributTouche() + " en déséquipant " + this.getArticleDefini() + " " + this.getNom());
        }

        perso.afficherStats();

        // on vérifie que le personnage n'est pas mort
        if(perso.getPV() <= 0){
            mourir = true;
        }

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
    
}
