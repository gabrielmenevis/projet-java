package Personnages;

import Objets.Objet;

public class PNJSpecial extends PNJ {

    private String indice;
    private String nomObjetDemande;
    private boolean satisfait;

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
        System.out.println(this.decrire() + " vous dit :");
        System.out.println("Écoute attentivement ce que j'ai à te dire... Tu en auras besoin pour retrouver ce que tu cherches.");
        System.out.println(this.indice);
        System.out.println();
    }
    
}
