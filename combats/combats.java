package combats;

import Personnages.Personnage;
import Personnages.PNJ;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.*;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

public class combats {

    private Personnage combatant1;
    private PNJ combatant2;
    private List<String[]> catchphrasesEtReponses;

    public combats(Personnage combatant1, PNJ combatant2) throws IOException {
        this.combatant1 = combatant1;
        this.combatant2 = combatant2;
        catchphrasesEtReponses = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get("files/catchphrases.csv"));
        for (String line : lines.subList(1, lines.size())) {  // Nous sautons la ligne d'en-tête
            catchphrasesEtReponses.add(line.split(";"));
        }
    }


    public boolean lancerCombat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Le combat commence entre " + combatant1.getNom() + " et " + combatant2.getNom());
        System.out.println("Vous êtes " + combatant1.getNom());
        while (combatant1.getPV() > 0 && combatant2.getPV() > 0 && !combatant2.isCharme()) {
            // Affiche les PV actuels de chaque combattant
            System.out.println(combatant1.getNom() + " " + afficherBarreSante(combatant1.getPV(), combatant1.getMaxPV()) +
                    "\n" + combatant2.getNom() + " " + afficherBarreSante(combatant2.getPV(), combatant2.getMaxPV()));

            String action = joueurChoix(scanner);
            if ("attaquer".equals(action)) {
                System.out.println("Vous lancez une attaque rapide et tranchante contre " + combatant2.getNom() + " ! *Slash!*");
                attaquer(combatant1, combatant2);
                if (combatant2.getPV() > 0) {
                    System.out.println(combatant2.getNom() + " riposte avec fureur ! *Pow!*");
                    attaquer(combatant2, combatant1);
                }
            } else if ("flirter".equals(action)) {
                System.out.println("Vous essayez de charmer " + combatant2.getNom() + " avec vos mots doux...");
                flirt(combatant1, combatant2);
                if (!combatant2.isCharme() && combatant2.getPV() > 0) {
                    System.out.println(combatant2.getNom() + " n'est pas impressionné et lance une contre-attaque !");
                    attaquer(combatant2, combatant1);
                    combatant2.resetPAttaque();
                }
            } else { // "fuir"
                System.out.println(combatant1.getNom() + " décide que le combat n'en vaut pas la peine et bat en retraite. " + combatant2.getNom() + " est le vainqueur !");
                return false;
            }
        }
        System.out.println("Le combat est terminé. " + (combatant1.getPV() > 0 ? combatant1.getNom() : combatant2.getNom()) + " est le vainqueur !");
        if (combatant1.getPV() <= 0) {
            return true;
        } else if (combatant2.getPV() <= 0) {
            combatant2.setVaincu(true);
        }
        return false;
    }

    private String joueurChoix(Scanner scanner) {
        String[] actions = {"attaquer", "flirter", "fuir"};
        System.out.println("Quelle action souhaitez-vous entreprendre ?");
        for (int i = 0; i < actions.length; i++) {
            System.out.println((i + 1) + ". " + actions[i]);
        }
        int choix = scanner.nextInt();
        scanner.nextLine(); // consomme la ligne restante
        while (choix < 1 || choix > actions.length) {
            System.out.println("Choix invalide. Veuillez choisir une action valide.");
            choix = scanner.nextInt();
            scanner.nextLine(); // consomme la ligne restante
        }
        return actions[choix - 1];
    }



    private void attaquer(Personnage attaquant, Personnage adversaire) {
        Random random = new Random();
        int chance = random.nextInt(100); // Un nombre aléatoire entre 0 et 99

        if (chance < 75) { // 75% de chance d'une attaque normale
            int Degats = attaquant.getPAttaque();
            System.out.println(attaquant.getNom() + " passe à l'attaque !");
            System.out.println(adversaire.getNom() + " perd " + Degats + " PVs.");
            adversaire.perdrePV(Degats);
            System.out.println(adversaire.getNom() + " " + afficherBarreSante(adversaire.getPV(), adversaire.getMaxPV()));
        } else if (chance < 90) { // 15% de chance d'un coup raté
            System.out.println(attaquant.getNom() + " a raté son attaque !");
            if (random.nextInt(2) == 0) { // 50% de chance de glisser sur une banane
                System.out.println(attaquant.getNom() + " glisse sur une peau de banane, se blesse et perd 5 PVs.");
                attaquant.perdrePV(5);
                System.out.println(attaquant.getNom() + " " + afficherBarreSante(attaquant.getPV(), attaquant.getMaxPV()));
            }
        } else { // 10% de chance d'un coup critique
            int Degats = attaquant.getPAttaque() * 2; // Les dégâts sont multipliés par 2 si coup critique
            System.out.println(attaquant.getNom() + " réussit un coup critique !");
            System.out.println(adversaire.getNom() + " perd " + Degats + " PVs.");
            adversaire.perdrePV(Degats);
            System.out.println(adversaire.getNom() + " " + afficherBarreSante(adversaire.getPV(), adversaire.getMaxPV()));
        }

        if (adversaire.getPV() <= 0) {
            System.out.println("L'adversaire " + adversaire.getNom() + " a été vaincu.");
        }
    }

    private void flirt(Personnage charmeur, Personnage adversaire) {
        Random random = new Random();
        int baseChance = 25;
        int chance = random.nextInt(80) + charmeur.getPCharisme() - adversaire.getPCharisme();

        int randomIndex = random.nextInt(catchphrasesEtReponses.size());
        String[] chosenLine = catchphrasesEtReponses.get(randomIndex);
        String catchphrase = chosenLine[0];
        System.out.println(charmeur.getNom() + " tente de charmer " + adversaire.getNom() + " en disant : " + catchphrase);

        if (chance > baseChance + 15) { // Grand succès
            System.out.println(adversaire.getNom() + " répond : " + chosenLine[3]);
            adversaire.setCharme(true);
        } else if (chance >= baseChance) { // Succès partiel
            System.out.println(adversaire.getNom() + " répond : " + chosenLine[2]);
            adversaire.gagnerPA(-5);  // on ajoute le malus d'attaque
        } else { // Échec
            System.out.println(adversaire.getNom() + " répond : " + chosenLine[1]);
            adversaire.gagnerPA(5); // bonus d'attaque
        }
    }


    public String afficherBarreSante(int pvActuel, int pvMax) {
        int longueurBarre = 20;
        double pourcentageSante = (double) pvActuel / pvMax;
        int nbCaracteresSante = (int) (pourcentageSante * longueurBarre);

        String barre = "";
        for (int i = 0; i < nbCaracteresSante; i++) {
            barre += "█";
        }
        for (int i = nbCaracteresSante; i < longueurBarre; i++) {
            barre += "-";
        }

        return "[" + barre + "] " + pvActuel + "/" + pvMax + " PVs";
    }



}

