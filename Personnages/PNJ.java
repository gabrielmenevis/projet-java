package Personnages;

public class PNJ extends Personnage {

    private String textePNJ;
    private String type;
    private int base_p_attaque;

    public PNJ(String nom, String type, String textePNJ, int max_pv, int p_attaque, int p_charisme) {
        super(nom, max_pv, max_pv, p_attaque, p_charisme);  // Ici, max_pv est passé deux fois à cause du constructeur de Personnage
        this.type = type;
        this.textePNJ = textePNJ;
        this.base_p_attaque = p_attaque;
    }

    public String getType(){
        return this.type;
    }

    public String getTextePNJ() {
        return textePNJ;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setTextePNJ(String textePNJ){
        this.textePNJ = textePNJ;
    }

    public void presentation() {
        System.out.println("Je suis " + getNom() + ". " + getTextePNJ());
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
