package Personnages;

import java.io.IOException;
import java.util.Scanner;

import Combats.Combat;
import Objets.Objet;

public class PNJSpecial extends PNJ {

    private String indice;
    private String nomObjetDemande;
    private boolean satisfait;
    private boolean isSpecial = true;

    public PNJSpecial(String nom, String type, String article, String indice, String textePNJ, int max_pv, int p_attaque, int p_charisme, String nomObjetDemande) {
        super(nom, type, article, textePNJ, max_pv, p_attaque, p_charisme);  // Ici, max_pv est passé deux fois à cause du constructeur de Personnage
        this.indice = indice;
        this.nomObjetDemande = nomObjetDemande;
        this.satisfait = false;
    }

    public String getIndice() {
        return indice;
    }

    public String getNomObjetDemande(){
        return this.nomObjetDemande;
    }

    public boolean getSatisfait(){
        return this.satisfait;
    }

    public void setIndice(String indice){
        this.indice = indice;
    }

    public void setNomObjetDemande(String nomObjetDemande){
        this.nomObjetDemande = nomObjetDemande;
    }

    public void setSatisfait(boolean satisfait){
        this.satisfait = satisfait;
    }
    public boolean isSpecial(){
        return this.isSpecial;
    }


    public String menuPNJ(){

        String choix = "";
        Scanner sc = new Scanner(System.in);

        // le PNJ n'est ni satisfait ni vaincu, l'interaction est la même que pour les PNJ aléatoires
        if(!this.satisfait && !this.getVaincu()){
            choix = super.menuPNJ();
        }

        else{
            System.out.println();
            System.out.println(this.decrire() + " vous dit : 'Ah, c'est toi ! Comment puis-je t'aider ?'");
            while(!choix.equals("1") && !choix.equals("2") && !choix.equals("3") && !choix.equals("4")){
                System.out.println("À vous de réagir :");
                System.out.println("1 - Répète-moi l'indice !");
                System.out.println("2 - Tiens, c'est pour toi !");
                System.out.println("3 - Baston !");
                System.out.println("4 - Annuler");
                choix = sc.nextLine();
            }
        }

        return choix;
    }

    public void parler(){
        if(!this.satisfait && !this.getVaincu()){ // si ni satisfait ni vaincu, répète sa phrase
            super.parler();
        }
        else{ // sinon, répète l'indice
            this.donnerIndice();
        }
    }

    public boolean combattre(Personnage perso) throws IOException{

        boolean mourir;
        Combat combat = new Combat(perso, this);

        if(this.satisfait){ // le joueur a donné l'objet demandé au PNJ
            System.out.println();
            System.out.println(this.decrire() + " vous a donné un indice... Vous n'allez pas lui taper dessus, quand même.");
            System.out.println("Mais peut-être que " + this.getNom() + " peut vous répéter l'indice si vous lui parlez gentiment.");
            System.out.println();
            mourir = false;
        }

        else if(this.getVaincu()){ // le joueur a battu le PNJ
            System.out.println();
            System.out.println(this.decrire() + " vous implore :");
            System.out.println("Pitié... Je t'ai déjà dit ce que tu voulais savoir...");
            System.out.println("Il te suffit de me parler si tu veux que je répète l'indice...");
            System.out.println();
            mourir = false;
        }

        else{ // sinon le combat se lance
            mourir = combat.lancerCombat();
        }

        return mourir;
    }

    public boolean recevoirObjet(Objet o){
        
        boolean accepte;

        if(o.getNom().equals(this.nomObjetDemande)){ // TODO: revoir messages
            accepte = true;
            this.satisfait = true;
            System.out.println();
            System.out.println(o.getArticleIndefini() + " " + o.getNom() + " !!!!! J'en ai toujours rêvé...");
            this.donnerIndice();
            System.out.println();
        }

        else{
            accepte = false;
            System.out.println();
            System.out.println("Tu crois que " + o.getArticleIndefini() + " " + o.getNom() + " de pacotille va m'amadouer ??");
            System.out.println("Non, une seule chose m'intéresse : " + this.nomObjetDemande);
            System.out.println();
        }

        return accepte;
    }


    public void donnerIndice(){
        System.out.println();
        System.out.println(this.decrire() + " vous dit :");
        System.out.println("Écoute attentivement ce que j'ai à te dire... Tu en auras besoin pour retrouver ce que tu cherches.");
        System.out.println(this.indice);
        System.out.println();
    }
    
}
