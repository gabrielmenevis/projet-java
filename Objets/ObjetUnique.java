package Objets;

import Personnages.Personnage;


/**
 * Représentation d'un objet unique. Hérite de la classe Objet. Implémente en plus un indicateur pour savoir
 * si l'objet a déjà été pris dans la pièce où il se trouvait, et une méthode pour l'empêcher d'apparaître s'il
 * a déjà été pris.
 * Implémente aussi une méthode pour le ranger dans la liste des objets utilisés de l'inventaire quand il
 * est utilisé (même sans avoir été auparavant rangé dans l'inventaire), ainsi qu'une méthode pour le déséquiper
 * (annuler ses effets).
 */
public class ObjetUnique extends Objet {

    private boolean dejaPris;

    /**
     * Constructeur de l'objet unique. Prend les paramètres à passer à la classe parente. Initialise dejaPris 
     * à faux.
     * @param nom : nom de l'objet
     * @param articleDefini : article défini à utiliser avec l'objet
     * @param articleIndefini : article indéfini à utiliser avec l'objet
     * @param valeurAjoutee : valeur positive ou négative quantifiant l'effet de l'objet sur une stat
     * @param attributTouche : statistique touchée par l'objet (PV, PA, PC)
     * @param utilisation : phrase à afficher lors de l'utilisation
     * @param effet : phrase à afficher pour décrire l'effet de l'objet
     */
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

    /**
     * Détermine si l'objet apparaît en fouillant une salle.
     * @return true si l'objet n'est pas encore pris, sinon false
     */
    public boolean apparaitre(){
        if(!this.dejaPris){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Surcharge la méthode définie dans Objet. Appel à la méthode de la classe parente 
     * et rangement dans la liste des objets uniques utilisés de l'inventaire.
     * @param perso : joueur sur lequel appliquer l'effet
     * @return true si le joueur meurt en utilisant l'objet, sinon false
     */
    public boolean utilisationObjet(Personnage perso){
        boolean mourir;
        mourir = super.utilisationObjet(perso);
        perso.getInventaire().rangerObjetUtilise(this);
        return mourir;
    }


    /**
     * Déséquipe un objet en annulant ses effets et en affichant les messages adéquats pour en informer
     * le joueur.
     * @param perso : le joueur qui déséquipe l'objet
     * @return true si le joueur est mort en déséquipant l'objet, sinon false
     */
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
  
    /**
     * Menu à afficher quand le personnage trouve un objet. Surcharge la méthode de la classe parente pour 
     * enregistré que l'objet est déjà pris si le joueur l'utilise ou le range dans l'inventaire.
     * @return le choix du joueur ("1" pour le laisser par terre, "2" pour l'utiliser tout de suite,
     * "3" pour le ranger dans l'inventaire, "4" pour annuler).
     */
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
