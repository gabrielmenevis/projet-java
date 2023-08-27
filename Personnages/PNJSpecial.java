package Personnages;

import Objets.Objet;

public class PNJSpecial extends PNJ {

    private String indice;

    public PNJSpecial(String nom, String type, String article, String indice, String textePNJ, int max_pv, int p_attaque, int p_charisme) {
        super(nom, type, article, textePNJ, max_pv, p_attaque, p_charisme);  // Ici, max_pv est passé deux fois à cause du constructeur de Personnage
        this.indice = indice;
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice){
        this.indice = indice;
    }

    public boolean recevoirObjet(Objet o){
        
        boolean accepte = true;

        // TODO: accepte l'objet unique si c'est celui qu'il demande

        return accepte;
    }
    
}
