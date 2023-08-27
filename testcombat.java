
import Personnages.PNJ;

import java.io.IOException;

public class testcombat {
    public static void main(String[] args) throws IOException {
        // Création de deux personnages
        Personnages.Artiste artiste = new Personnages.Artiste("Leonardo", 100, 100, 20, 40);
        PNJ Florian = new PNJ("Florian","pompiste", "le", "Je suis délégué MIMO", 100, 10, 40);

        // Présentation des personnages
        artiste.presentation();
        Florian.presentation();

        // Création du combat
        combats.combats combat = new combats.combats(artiste, Florian);

        // Lancement du combat
        combat.lancerCombat();
    }
}
