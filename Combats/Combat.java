package Combats;

import Personnages.Personnage;
import Personnages.PNJ;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.*;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

/**
 * La classe combats gère un combat entre un Personnage et un PNJ (Personnage Non-Joueur).
 * Elle offre la possibilité d'attaquer, flirter ou fuir pendant le combat.
 * Les catchphrases pour charmer un adversaire sont récupérées à partir d'un fichier CSV.
 */
public class Combat {

    private Personnage combattant1; // Le combattant 1 est le joueur
    private PNJ combattant2; // le combattant 2 est le PNJ
    private List<String[]> catchphrasesEtReponses; // Liste des catchphrases (chargées depuis le CSV)

    /**
     * Constructeur de la classe combats.
     * Initialise les combattants et lit les catchphrases du fichier CSV.
     *
     * @param combattant1 Le personnage principal (joueur).
     * @param combattant2 Le PNJ adversaire.
     * @throws IOException Si une erreur se produit lors de la lecture du fichier.
     */
    public Combat(Personnage combattant1, PNJ combattant2) throws IOException {
        this.combattant1 = combattant1;
        this.combattant2 = combattant2;
        catchphrasesEtReponses = new ArrayList<>(); // Initialisation de la liste des catchphrases
        List<String> lines = Files.readAllLines(Paths.get("files/catchphrases.csv"));
        for (String line : lines.subList(1, lines.size())) {  // Nous sautons la ligne d'en-tête pour lire le csv
            catchphrasesEtReponses.add(line.split(";"));
        }
    }

    /**
     * Lance un combat entre les deux combattants.
     * Le combat continu tant que les deux combattants ont des PVs ou que le PNJ n'est pas charmé.
     *
     * @return true si le joueur est vaincu, sinon false.
     */
    public boolean lancerCombat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Le combat commence contre " + combattant2.getNom());
        while (combattant1.getPV() > 0 && combattant2.getPV() > 0 && !combattant2.isCharme()) {
            // Tant que les deux combattants ont des PV et que le PNJ n'est pas charmé
            // Affiche les PV actuels de chaque combattant
            System.out.println(combattant1.getNom() + " " + afficherBarreSante(combattant1.getPV(), combattant1.getMaxPV()) +
                    "\n" + combattant2.getNom() + " " + afficherBarreSante(combattant2.getPV(), combattant2.getMaxPV()));

            String action = joueurChoix(scanner);
            if ("attaquer".equals(action)) {
                System.out.println("Vous lancez une attaque rapide et tranchante contre " + combattant2.getNom() + " ! *Slash!*");
                attaquer(combattant1, combattant2);
                if (combattant2.getPV() > 0) { // Si le PNJ n'est pas vaincu, il riposte
                    System.out.println(combattant2.getNom() + " riposte avec fureur ! *Pow!*");
                    attaquer(combattant2, combattant1);
                }
            } else if ("flirter".equals(action)) {
                System.out.println("Vous essayez de charmer " + combattant2.getNom() + " avec vos mots doux...");
                flirt(combattant1, combattant2);
                if (!combattant2.isCharme() && combattant2.getPV() > 0) { // Si le PNJ n'est pas charmé, il riposte
                    System.out.println(combattant2.getNom() + " n'est pas impressionné et lance une contre-attaque !");
                    attaquer(combattant2, combattant1);
                    combattant2.resetPAttaque(); // Le PNJ retrouve ses PA après avoir attaqué
                    // car ils peuvent avoir été modifiés suite à un flirt partiellement réussi
                }
                else{
                    combattant2.setVaincu(true);
                }
            } else { // "fuir"
                System.out.println("Vous décidez que le combat n'en vaut pas la peine et battez en retraite. " + combattant2.getNom() + " est le vainqueur !");
                return false; // Le PNJ ne peut pas fuir, seul le joueur peut fuir
            }
        }
        System.out.println("Le combat est terminé. " + (combattant1.getPV() > 0 ? combattant1.getNom() : combattant2.getNom()) + " est le vainqueur !");
        if (combattant1.getPV() <= 0) {
            return true; // Le joueur est vaincu
        } else if (combattant2.getPV() <= 0) {
            combattant2.setVaincu(true); // Le PNJ est vaincu
        }
        
        return false;
    }

    /**
     * Demande au joueur de choisir une action à effectuer (attaquer, flirter, fuir).
     *
     * @param scanner L'objet scanner pour lire l'entrée de l'utilisateur.
     * @return L'action choisie par le joueur.
     */
    private String joueurChoix(Scanner scanner) {
        String[] actions = {"attaquer", "flirter", "fuir"};
        System.out.println("Quelle action souhaitez-vous entreprendre ?");
        for (int i = 0; i < actions.length; i++) { // Affiche les actions possibles
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


    /**
     * Gère une attaque d'un combattant contre un adversaire.
     * Prend en compte les chances de réussite, les coups ratés et les coups critiques.
     *
     * @param attaquant Celui qui attaque.
     * @param adversaire Celui qui est attaqué.
     */
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

        if (adversaire.getPV() <= 0) { // Si l'adversaire est vaincu
            System.out.println("L'adversaire " + adversaire.getNom() + " a été vaincu.");
        }
    }

    /**
     * Tente de charmer l'adversaire avec une catchphrase.
     * Prend en compte le charisme des deux combattants pour déterminer le succès de la tentative.
     *
     * @param charmeur Celui qui tente de charmer.
     * @param adversaire Celui qui est ciblé par la tentative.
     */
    private void flirt(Personnage charmeur, Personnage adversaire) {
        Random random = new Random();
        int baseChance = 35; // Chance de base de charmer l'adversaire
        int chance = random.nextInt(80) + charmeur.getPCharisme() - adversaire.getPCharisme();

        int randomIndex = random.nextInt(catchphrasesEtReponses.size()); // Choix aléatoire d'une catchphrase
        String[] chosenLine = catchphrasesEtReponses.get(randomIndex);
        String catchphrase = chosenLine[0]; // La catchphrase est la première colonne du CSV
        System.out.println("Vous essayez de charmer " + adversaire.getNom() + " en lui disant : " + catchphrase);

        if (chance > baseChance + 15) { // Grand succès
            System.out.println(adversaire.getNom() + " répond : " + chosenLine[3]);
            adversaire.setCharme(true);
        } else if (chance >= baseChance) { // Succès partiel
            System.out.println(adversaire.getNom() + " répond : " + chosenLine[2]);
            adversaire.perdrePA(5);  // on ajoute le malus d'attaque
        } else { // Échec
            System.out.println(adversaire.getNom() + " répond : " + chosenLine[1]);
            adversaire.gagnerPA(5); // bonus d'attaque
        }
    }

    /**
     * Affiche une barre de santé basée sur les PV actuels et les PV max d'un combattant.
     *
     * @param pvActuel Les points de vie actuels du combattant.
     * @param pvMax Les points de vie maximum du combattant.
     * @return La barre de santé sous forme de chaîne de caractères.
     */
    public String afficherBarreSante(int pvActuel, int pvMax) {
        int longueurBarre = 20; // Longueur de la barre de santé
        double pourcentageSante = (double) pvActuel / pvMax; // Pourcentage de PV actuels par rapport aux PV max
        int nbCaracteresSante = (int) (pourcentageSante * longueurBarre); // Nombre de caractères à afficher pour les PV actuels

        String barre = "";
        for (int i = 0; i < nbCaracteresSante; i++) {
            barre += "█";
        } // Ajoute des caractères pleins pour les PV actuels
        for (int i = nbCaracteresSante; i < longueurBarre; i++) {
            barre += "-";
        } // Ajoute des caractères vides pour les PV manquants

        return "[" + barre + "] " + pvActuel + "/" + pvMax + " PVs";
        // Exemple : [████████████████████] 100/100 PVs
    }



}

