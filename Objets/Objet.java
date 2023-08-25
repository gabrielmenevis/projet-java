package Objets;

import java.util.Scanner;

import Personnages.Personnage;

public class Objet {
    
    private String nom;
    private int valeurAjoute;
    private String attributTouche;
    private String article;
    private String utilisation;

    
    public Objet(String nom, String article, int valeurAjoute, String utilisation, String attributTouche) {
        this.nom = nom;
        this.article = article;
        this.valeurAjoute = valeurAjoute;
        this.utilisation = utilisation;
        this.attributTouche = attributTouche;

    }
    
    
    public String getNom() {
        return nom;
    }


    public int getValeurAjoute() {
        return valeurAjoute;
    }
    public String getArticle(){
        return article;

    }
    public String getUtilisation(){
        return utilisation;
    }
    public String attributTouche(){
        return attributTouche;
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

        System.out.println("vous avez trouve" +this.article+ " " + this.nom + "Que voulez vous en faire ? ");
        System.out.println ("1 - " + this.article + " laisser par terre.");
        System.out.println("2 - "+ this.article +" " + this.utilisation);// 
        System.out.println("3 - " + this.article+ " ranger dans l'inventaire");
        
        rep=s.nextLine();
        
    }



    

    
  
    }
    // methode : 1. Laisser tomber 
  //  2. Manger
    //3. Ranger dans inventaire
    //4. Porter 

    // menu : 

    


