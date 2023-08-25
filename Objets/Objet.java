package Objets;

import java.util.Scanner;

import Personnages.Personnage;

public class Objet {
    
    private String nom;
    private int valeurAjoutee;
    private String attributTouche;
    private String articleDefini;
    private String articleIndefini;
    private String utilisation;
    private String effet;

    
    public Objet(String nom, String articleDefini, String articleIndefini, int valeurAjoutee, String attributTouche, String utilisation, String effet) {
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

    public String attributTouche(){
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

    public void utilisationObjet(Personnage perso){
        // switch (this.attributTouche) {
        //     case "-pv" : perso.perdrePV(this.valeurAjoute);
        //     break;
        //     case "p_attaque" : perso.gagnerPA(this.valeurAjoute);
        //     break;
        //     case "p_charisme" : perso.gagnerPC(this.valeurAjoute);
        //     break;
        //     case "+pv" : perso.gagnerPV(this.valeurAjoute);
        //     break;

            //rajouter méthode gagnerPA, gagnerPV, gagnerPC dans personnage
            // rajouter aussi "mourir" ou "se reveiller à l'infirmerie" --> se reveiller avec les pv redevenus normaux, dans la salle de l'infirmerie
        }


    public void laisserObjet(){
        // si dans le menuObjet on fait le choix de laisser l'objet, on revient au menu de la salle

    }
    public void rangerInventaire(){
        // ajouter à l'inventaire du perso et retirer de la liste objet de la salle
    }

    public void menuObjet(){
        Scanner s = new Scanner(System.in);
        String rep;

        System.out.println("vous avez trouve" +this.articleIndefini+ " " + this.nom + "Que voulez vous en faire ? ");
        System.out.println ("1 - " + this.articleDefini + " laisser par terre.");
        System.out.println("2 - "+ this.articleDefini +" " + this.utilisation);// 
        System.out.println("3 - " + this.articleDefini + " ranger dans l'inventaire");
        
        rep=s.nextLine();
        
    }



    

    
  
    }
    // methode : 1. Laisser tomber 
  //  2. Manger
    //3. Ranger dans inventaire
    //4. Porter 

    // menu : 

    


