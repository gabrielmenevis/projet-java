package Personnages;

import Objets.Objet;

public class PNJSpecial extends PNJ {

    private String indice;
    private String nomObjetDemande;

    public PNJSpecial(String nom, String type, String article, String indice, String textePNJ, int max_pv, int p_attaque, int p_charisme, String nomObjetDemande) {
        super(nom, type, article, textePNJ, max_pv, p_attaque, p_charisme);  // Ici, max_pv est passé deux fois à cause du constructeur de Personnage
        this.indice = indice;
        this.nomObjetDemande = nomObjetDemande;
    }

    public String getIndice() {
        return indice;
    }

    public String getNomObjetDemande(){
        return this.nomObjetDemande;
    }

    public void setIndice(String indice){
        this.indice = indice;
    }

    public void setNomObjetDemande(String nomObjetDemande){
        this.nomObjetDemande = nomObjetDemande;
    }

    // TODO: accepte l'objet unique si c'est celui qu'il demande
    public boolean recevoirObjet(Objet o){
        
        boolean accepte;

        if(o.getNom().equals(this.nomObjetDemande)){
            accepte = true;
            System.out.println();
            System.out.println("trop cool merci mon reuf");
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
    
}
