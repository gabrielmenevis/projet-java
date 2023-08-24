package Personnages;

public class PNJ extends Personnage {
    private String indice;
    private String texte_pnj;
    
    public PNJ(String nom, String indice, String texte_pnj) {        
        super(nom, 0, 0, 0, 0);
        this.indice = indice;
        this.texte_pnj = texte_pnj;
    }

    public String getIndice() {
        return indice;
    }

    public String getTextePNJ() {
        return texte_pnj;
    }

    public void presentation() {
        System.out.println("Je suis " + getNom() + getTextePNJ());
    }

    public static void main(String[] args) {

        PNJ pnj1 = new PNJ("Margerite la cuisinière. ", "à définir", "La cuisine est fermée au public, si vous voulez y entrer, il faudra me passer sur le corps");
        pnj1.presentation();

        PNJ pnj2 = new PNJ("Hervé le plongeur. ", "à définir", "idk yet");
        pnj2.presentation();

        PNJ pnj3 = new PNJ("le Chien méchant.", "à définir", "Ouaf Ouaf");
        pnj3.presentation();

        PNJ pnj4 = new PNJ("l'homme nu du hammam? ", " Pas d'indice pour vous..", "Quelle est votre réponse?");
        pnj4.presentation();

        PNJ pnj5 = new PNJ("Moe. ", "à définir", "Une bière?");
        pnj5.presentation();

        PNJ pnj6 = new PNJ("Igor."," idk", " idk");
        pnj6.presentation();

    }
}
