package Personnages;

import java.io.IOException;
import java.util.Scanner;

import Objets.Objet;
import Objets.ObjetUnique;
import combats.combats;

public class PNJ extends Personnage {

    private String textePNJ;
    private String article;
    private String type;
    private int base_p_attaque;
    private boolean vaincu;

    public PNJ(String nom, String type, String article, String textePNJ, int max_pv, int p_attaque, int p_charisme) {
        super(nom, max_pv, max_pv, p_attaque, p_charisme);  // Ici, max_pv est passé deux fois à cause du constructeur de Personnage
        this.type = type;
        this.article = article;
        this.textePNJ = textePNJ;
        this.base_p_attaque = p_attaque;
        this.vaincu = false;
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

    public boolean getVaincu(){
        return this.vaincu;
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
    
    public void setVaincu(boolean vaincu){
        this.vaincu = vaincu;
    }

    public void presentation() {
        System.out.println("Je suis " + this.getNom() + " " + this.article + " " + this.type + ". " + this.textePNJ);
    }

    public String menuPNJ(){

        String choix = "";
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println(this.decrire() + " vous dit : '" + this.textePNJ + "'");
        System.out.println("Non mais ! " + this.getNom() + " vous provoque ou quoi ?");

        while(!choix.equals("1") && !choix.equals("2") && !choix.equals("3") && !choix.equals("4")){
            System.out.println("À vous de réagir :");
            System.out.println("1 - Répète un peu pour voir...");
            System.out.println("2 - Tiens, c'est pour toi !");
            System.out.println("3 - Tu veux te battre c'est ça ??!");
            System.out.println("4 - Annuler");
            choix = sc.nextLine();
        }
        
        return choix;
    }

    public void parler(){
        System.out.println();
        System.out.println(this.getNom() + " vous dit : '" + this.textePNJ + "'");
        System.out.println();
    }

    public boolean combattre(Personnage perso) throws IOException{

        combats combat = new combats(perso, this);
        boolean mourir;

        if(this.vaincu){ // le joueur a déjà vaincu le PNJ
            System.out.println();
            System.out.println("Vous avez déjà vaincu " + this.getNom() + ". Il vous implore : 'Pitié... Laisse-moi tranquille...");
            mourir = false;
        }
        else{ // sinon le combat se lance
            mourir = combat.lancerCombat();
        }

        return mourir;
    }

    // TODO: mettre une proba d'accepter le consommable
    public boolean recevoirObjet(Objet o){

        boolean accepte;

        System.out.println();
        if(o instanceof ObjetUnique){
            System.out.println(this.getNom() + " vous dit : 'Peuh ! ça ne m'intéresse pas.'");
            accepte = false;
        }
        else{
            System.out.println(this.getNom() + " vous dit : '" + o.getArticleIndefini() + " " + o.getNom() + " ? Merci, il ne fallait pas...'");
            accepte = true;
        }

        return accepte;
    }

    public String decrire(){
        return this.getNom() + " " + this.article + " " + this.type;
    }

    public void resetPAttaque(){
        setP_attaque(this.base_p_attaque);
    }
}
