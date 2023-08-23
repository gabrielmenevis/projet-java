import Personnages.Personnage;
public class testcombat {
    public static void main(String[] args) {
        // Création de deux personnages
        Personnages.Artiste artiste = new Personnages.Artiste("Leonardo", 100, 100, 20, 15);
        Personnages.Courgette courgette = new Personnages.Courgette("Cory", 80, 80, 25, 10);

        // Présentation des personnages
        artiste.presentation();
        courgette.presentation();

        // Création du combat
        combats.combats combat = new combats.combats(artiste, courgette);

        // Lancement du combat
        combat.lancerCombat();
    }
}
