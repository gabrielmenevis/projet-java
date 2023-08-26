package Personnages;

import java.util.Scanner;

public class PNJ extends Personnage {

    private String textePNJ;
    private String article;
    private String type;
    private int base_p_attaque;

    public PNJ(String nom, String type, String article, String textePNJ, int max_pv, int p_attaque, int p_charisme) {
        super(nom, max_pv, max_pv, p_attaque, p_charisme);  // Ici, max_pv est passé deux fois à cause du constructeur de Personnage
        this.type = type;
        this.article = article;
        this.textePNJ = textePNJ;
        this.base_p_attaque = p_attaque;
    }

    public String getType(){
        return this.type;
    }

    public String getTextePNJ() {
        return textePNJ;
    }

    public String getArticle(){
        return this.article;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setTextePNJ(String textePNJ){
        this.textePNJ = textePNJ;
    }

    public void setArticle(String article){
        this.article = article;
    }

    public void presentation() {
        System.out.println("Je suis " + this.getNom() + " " + this.article + " " + this.type + ". " + this.textePNJ);
    }

    public String menuPNJ(){

        String choix = "";
        Scanner sc = new Scanner(System.in);

        System.out.println(this.textePNJ);
        System.out.println("Non mais ! " + this.getNom() + " vous provoque ou quoi ?");

        while(!choix.equals("1") && !choix.equals("2") && !choix.equals("3")){
            System.out.println("À vous de réagir :");
            System.out.println("1 - Parle moi encore...");
            System.out.println("2 - Baston !");
            System.out.println("3 - Annuler");
            choix = sc.nextLine();
        }
        
        return choix;
    }

    // public static void main(String[] args) {

    //     PNJ pnj1 = new PNJ("Margerite la cuisinière. ", "à définir", "La cuisine est fermée au public, si vous voulez y entrer, il faudra me passer sur le corps", 100, 120, 34);
    //     pnj1.presentation();

    //     PNJ pnj2 = new PNJ("Hervé le plongeur. ", "à définir", "idk yet", 12, 12, 12);
    //     pnj2.presentation();

    //     PNJ pnj3 = new PNJ("le Chien méchant.", "à définir", "Ouaf Ouaf", 12, 12, 12);
    //     pnj3.presentation();

    //     PNJ pnj4 = new PNJ("l'homme nu du hammam? ", " Pas d'indice pour vous..", "Quelle est votre réponse?", 12, 12, 12);
    //     pnj4.presentation();

    //     PNJ pnj5 = new PNJ("Moe. ", "à définir", "Une bière?", 12, 12, 12);
    //     pnj5.presentation();

    //     PNJ pnj6 = new PNJ("Igor."," idk", " idk", 12, 12, 12);
    //     pnj6.presentation();

    // }

    public void resetPAttaque(){
        setP_attaque(this.base_p_attaque);
    }
}
