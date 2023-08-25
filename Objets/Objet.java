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
    private boolean consommable;
    private int probaSpawn;

    
    public Objet(String nom, String articleDefini, String articleIndefini, int valeurAjoutee, String attributTouche, String utilisation, String effet, boolean consommable, int probaSpawn) {
        this.nom = nom;
        this.articleDefini = articleDefini;
        this.articleIndefini = articleIndefini;
        this.valeurAjoutee = valeurAjoutee;
        this.attributTouche = attributTouche;
        this.utilisation = utilisation;
        this.effet = effet;
        this.consommable = consommable;
        this.probaSpawn = probaSpawn;
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

    public boolean getConsommable(){
        return this.consommable;
    }

    public int getProbaSpawn(){
        return this.probaSpawn;
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

    public void setConsommable(boolean consommable){
        this.consommable = consommable;
    }

    public void setProbaSpawn(int probaSpawn){
        this.probaSpawn = probaSpawn;
    }

    public void utilisationObjet(Personnage perso){

        System.out.println(this.effet);
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
        else {
            switch (this.attributTouche){

                case "PV" : perso.gagnerPV(this.valeurAjoutee);
                System.out.println("Bravo ! Vous avez gagné "+ this.valeurAjoutee + " PV ");
                System.out.println("vous êtes maintenant à "+ perso.getPV()+ " PV");
                break;
                case "PA" : perso.gagnerPA(this.valeurAjoutee);
                System.out.println("Bravo ! Vous avez gagné "+ this.valeurAjoutee + " point(s) d'attaque ");
                System.out.println("vous êtes maintenant à "+ perso.getPAttaque()+ " point(s) d'attaque");
                break;
                case "PC" : perso.gagnerPC(this.valeurAjoutee);
                System.out.println("Bravo ! Vous avez gagné "+ this.valeurAjoutee + " point(s) de charisme ");
                System.out.println("vous êtes maintenant à "+ perso.getPCharisme()+ " point(s) de charisme");
                break;

            }

        } 
       
        //     

            //rajouter méthode gagnerPA, gagnerPV, gagnerPC dans personnage
            // rajouter aussi "mourir" ou "se reveiller à l'infirmerie" --> se reveiller avec les pv redevenus normaux, dans la salle de l'infirmerie
        }


    public void laisserObjet(){
        // si dans le menuObjet on fait le choix de laisser l'objet, on revient au menu de la salle

    }
    public void rangerInventaire(){
        // ajouter à l'inventaire du perso et retirer de la liste objet de la salle
    }

    public String menuObjet(){
        Scanner s = new Scanner(System.in);
        String rep;

        System.out.println("Vous avez trouvé " + this.articleIndefini + " " + this.nom + ". Que voulez vous en faire ? ");
        System.out.println ("1 - " + this.articleDefini + " laisser par terre.");
        System.out.println("2 - "+ this.articleDefini +" " + this.utilisation);// 
        System.out.println("3 - " + this.articleDefini + " ranger dans l'inventaire");
        System.out.println("4 - Annuler");
        
        rep=s.nextLine();
        return rep;
        
    }



    

    
  
    }
    // methode : 1. Laisser tomber 
  //  2. Manger
    //3. Ranger dans inventaire
    //4. Porter 

    // menu : 

    


