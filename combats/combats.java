package combats;

import Personnages.Personnage;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.*;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
public class combats {

    private Personnage combatant1;
    private Personnage combatant2;
    private List<String> catchphrases;

    public combats(Personnage combatant1, Personnage combatant2) throws IOException {
        this.combatant1 = combatant1;
        this.combatant2 = combatant2;
        //
        catchphrases = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("catchphrases.csv"));
        catchphrases.addAll(lines);
    }

    public void lancerCombat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Le combat commence entre " + combatant1.getNom() + " et " + combatant2.getNom());
        System.out.println("Vous êtes " + combatant1.getNom());
        while (combatant1.getPV() > 0 && combatant2.getPV() > 0 && !combatant2.isCharme()) {
            // Affiche les PV actuels de chaque combattant
            System.out.println(combatant1.getNom() + " a " + combatant1.getPV() + "/" + combatant1.getMaxPV() + " PVs. " +
                    combatant2.getNom() + " a " + combatant2.getPV() + "/" + combatant2.getMaxPV() + " PVs.");

            String action = joueurChoix(scanner);
            if ("attaquer".equals(action)) {
                attaquer(combatant1, combatant2);
                if (combatant2.getPV() > 0) {
                    attaquer(combatant2, combatant1);
                }
            } else if ("flirter".equals(action)) {
                flirt(combatant1, combatant2);
                if (!combatant2.isCharme()) {
                    attaquer(combatant2, combatant1);
                }
            } else { // "fuir"
                System.out.println(combatant1.getNom() + " a fui le combat. " + combatant2.getNom() + " est le vainqueur!");
                return;
            }
        }
        System.out.println("Le combat est terminé. " + (combatant1.getPV() > 0 ? combatant1.getNom() : combatant2.getNom()) + " est le vainqueur!");
    }



    private String joueurChoix(Scanner scanner) {
        System.out.println("Choisissez une action : 1 pour attaquer, 2 pour flirter, 3 pour fuir");
        String choix = scanner.nextLine();
        while (!choix.equals("1") && !choix.equals("2") && !choix.equals("3")) {
            System.out.println("Choix invalide. Entrez 1 pour attaquer, 2 pour flirter, ou 3 pour fuir");
            choix = scanner.nextLine();
        }
        if (choix.equals("1")) {
            return "attaquer";
        } else if (choix.equals("2")) {
            return "flirter";
        } else {
            return "fuir";
        }
    }


    private void attaquer(Personnage attaquant, Personnage adversaire) {
        Random random = new Random();
        int chance = random.nextInt(100); // Un nombre aléatoire entre 0 et 99

        if (chance < 75) { // 75% de chance d'une attaque normale
            int Degats = attaquant.getPAttaque();
            System.out.println(attaquant.getNom() + " passe à l'attaque !");
            System.out.println(adversaire.getNom() + " perd " + Degats + " PVs.");
            adversaire.perdrePV(Degats);
            System.out.println(adversaire.getNom() + " : " + adversaire.getPV() + "/" + adversaire.getMaxPV() + "PVs.");
        } else if (chance < 90) { // 15% de chance d'un coup raté
            System.out.println(attaquant.getNom() + " a raté son attaque !");
            if (random.nextInt(2) == 0) { // 50% de chance de glisser sur une banane
                System.out.println(attaquant.getNom() + " glisse sur une peau de banane, se blesse et perd 5 PVs.");
                attaquant.perdrePV(5);
                System.out.println(attaquant.getNom() + " : " + attaquant.getPV() + "/" + attaquant.getMaxPV() + "PVs.");
            }
        } else { // 10% de chance d'un coup critique
            int Degats = attaquant.getPAttaque() * 2; // Les dégâts sont multipliés par 2 si coup critique
            System.out.println(attaquant.getNom() + " réussit un coup critique !");
            System.out.println(adversaire.getNom() + " perd " + Degats + " PVs.");
            adversaire.perdrePV(Degats);
            System.out.println(adversaire.getNom() + " : " + adversaire.getPV() + "/" + adversaire.getMaxPV() + "PVs.");
        }

        if (adversaire.getPV() <= 0) {
            System.out.println("L'adversaire " + adversaire.getNom() + " a été vaincu.");
        }
    }

    private void flirt(Personnage charmeur, Personnage adversaire) {
        Random random = new Random();
        int chance = random.nextInt(100) + charmeur.getPCharisme() - adversaire.getPCharisme();

        if (chance > 90) {
            String catchphrase = catchphrases.get(random.nextInt(catchphrases.size()));
            System.out.println(charmeur.getNom() + " utilise son charme sur " + adversaire.getNom() + " et dit : " + catchphrase);
            adversaire.setCharme(true);
        } else {
            String catchphrase = catchphrases.get(random.nextInt(catchphrases.size()));
            System.out.println(charmeur.getNom() + " dit à " + adversaire.getNom() + " : " + catchphrase);
            System.out.println(adversaire.getNom() + " n'est guère convaincu..");
        }

        if (adversaire.isCharme() == true){
            System.out.println((adversaire.getNom() + " est envouté par votre charme et abandonne le combat !"));
        }
    }

}

