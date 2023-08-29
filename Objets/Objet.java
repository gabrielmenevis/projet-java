package Objets;


import java.util.Scanner;

import Personnages.Personnage;

/**
 * Représentation d'un objet. Classe abstraite qui possède deux sous-classes : ObjetUnique et ObjetConsommable.
 * Un objet a un nom, une valeur ajoutée, un attribut touché (PV, PA ou PC), un article indéfini et un article 
 * défini pour fluidifier l'affichage. Il a également un message à afficher lors de son utilisation
 * et un message à afficher après utilisation (effet de l'objet).
 */
public abstract class Objet {
    
    private String nom;
    private int valeurAjoutee;
    private String attributTouche;
    private String articleDefini;
    private String articleIndefini;
    private String utilisation;
    private String effet;


    /**
     * Constructeur de l'objet. Prend tous ses attributs en paramètre.
     * @param nom : nom de l'objet
     * @param articleDefini : article défini à utiliser avec l'objet
     * @param articleIndefini : article indéfini à utiliser avec l'objet
     * @param valeurAjoutee : valeur positive ou négative quantifiant l'effet de l'objet sur une stat
     * @param attributTouche : statistique touchée par l'objet (PV, PA, PC)
     * @param utilisation : phrase à afficher lors de l'utilisation
     * @param effet : phrase à afficher pour décrire l'effet de l'objet
     */
    public Objet(String nom, String articleDefini, String articleIndefini, int valeurAjoutee, String attributTouche, String utilisation, String effet){
        this.nom = nom;
        this.articleDefini = articleDefini;
        this.articleIndefini = articleIndefini;
        this.valeurAjoutee = valeurAjoutee;
        this.attributTouche = attributTouche;
        this.utilisation = utilisation;
        this.effet = effet;
    }
    
    public String getNom() {
        return this.nom;
    }

    public String getArticleDefini(){
        return this.articleDefini;
    }

    public String getArticleIndefini(){
        return this.articleIndefini;
    }

    public int getValeurAjoutee() {
        return this.valeurAjoutee;
    }

    public String getAttributTouche(){
        return this.attributTouche;
    }

    public String getUtilisation(){
        return this.utilisation;
    }

    public String getEffet(){
        return this.effet;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setArticleDefini(String articleDefini){
        this.articleDefini = articleDefini;
    }

    public void setArticleIndefini(String articleIndefini){
        this.articleIndefini = articleIndefini;
    }

    public void setValeurAjoutee(int valeurAjoutee){
        this.valeurAjoutee = valeurAjoutee;
    }

    public void setAttributTouche(String attributTouche){
        this.attributTouche = attributTouche;
    }

    public void setUtilisation(String utilisation){
        this.utilisation = utilisation;
    }

    public void setEffet(String effet){
        this.effet = effet;
    }

    /**
     * Utilise l'objet sur le personnage. Appelle les méthodes de Personnage permettant de modifier 
     * son nombre de PV, PA ou PC en fonction de l'attribut touché par l'objet.
     * Affiche un message indiquant l'utilisation et l'effet de l'objet.
     * @param perso : joueur qui utilise l'objet
     * @return vrai si le personnage meurt en utilisant l'objet, sinon faux
     */
    public boolean utilisationObjet(Personnage perso){

        System.out.println(this.effet);

        // si l'objet fait perdre des points de statistiques
        if (this.valeurAjoutee < 0 ){
        
            switch (this.attributTouche) {
            
                case "PV" : perso.perdrePV(this.valeurAjoutee*-1);
                System.out.println("Oups ! Vous avez perdu "+ this.valeurAjoutee*-1 + " PV ");
                System.out.println("vous êtes maintenant à "+ perso.getPV() + " PV");
               
                break;
                case "PA" : perso.perdrePA(this.valeurAjoutee*-1);
                System.out.println("Oups ! Vous avez perdu "+ this.valeurAjoutee*-1 + " point(s) d'attaque ");
                System.out.println("vous êtes maintenant à "+ perso.getPAttaque()+ " point(s) d'attaque");
                break;
                case "PC" : perso.perdrePC(this.valeurAjoutee*-1);
                System.out.println("Oups ! Vous avez perdu "+ this.valeurAjoutee*-1 + " point(s) de charisme ");
                System.out.println("vous êtes maintenant à "+ perso.getPCharisme()+ " point(s) de charisme");
                break;
            

            } 

        }

        // sinon l'objet fait gagner des points de statistiques
        else {
            switch (this.attributTouche){

                case "PV" : perso.gagnerPV(this.valeurAjoutee);
                System.out.println("Bravo ! Vous avez gagné "+ this.valeurAjoutee + " PV ");
                System.out.println("Vous êtes maintenant à "+ perso.getPV()+ " PV");
                break;
                case "PA" : perso.gagnerPA(this.valeurAjoutee);
                System.out.println("Bravo ! Vous avez gagné "+ this.valeurAjoutee + " point(s) d'attaque ");
                System.out.println("Vous êtes maintenant à "+ perso.getPAttaque()+ " point(s) d'attaque");
                break;
                case "PC" : perso.gagnerPC(this.valeurAjoutee);
                System.out.println("Bravo ! Vous avez gagné "+ this.valeurAjoutee + " point(s) de charisme ");
                System.out.println("Vous êtes maintenant à "+ perso.getPCharisme()+ " point(s) de charisme");
                break;

            }

        }

        // on vérifie si le personnage est mort, si oui on retourne vrai
        if (perso.getPV() <= 0) {
            return true;
        }
        else{ // sinon on retourne faux
            return false;
        }
    }


    /**
     * Menu à afficher quand le personnage trouve un objet.
     * @return le choix du joueur ("1" pour le laisser par terre, "2" pour l'utiliser tout de suite,
     * "3" pour le ranger dans l'inventaire, "4" pour annuler).
     */
    public String menuObjetTrouve(){

        Scanner s = new Scanner(System.in);
        String rep = "";

        while(!(rep.equals("1")) && !(rep.equals("2")) && !(rep.equals("3")) && !(rep.equals("4"))){
            System.out.println("Vous avez trouvé " + this.articleIndefini + " " + this.nom + ". Que voulez vous en faire ? ");
            System.out.println ("1 - " + this.articleDefini + " laisser par terre.");
            System.out.println("2 - "+ this.articleDefini +" " + this.utilisation);// 
            System.out.println("3 - " + this.articleDefini + " ranger dans l'inventaire");
            System.out.println("4 - Annuler");
        }
        
        rep=s.nextLine();
        s.close();
        return rep;
    }

    /**
     * Méthode abstraite pour l'apparition de l'objet. Définie par ObjetConsommable et ObjetUnique.
     * @return vrai si l'objet apparaît, sinon faux
     */
    public abstract boolean apparaitre();

}
    


