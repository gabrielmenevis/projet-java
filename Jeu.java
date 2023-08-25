import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.sound.midi.Soundbank;

import Objets.Objet;
import Salles.Salle;
import Personnages.*;

public class Jeu {

    public static ArrayList<Salle> listeSalles;
    public static Salle salleActuelle;
    public static Personnage joueur;

    public static void main(String[] args) throws IOException {

        Random r = new Random();
        ArrayList<Objet> listeObjets = new ArrayList<Objet>();
        Objet champi;

        joueur = new Courgette("josé", 0, 0, 0, 0);
        listeSalles = Chargement.chargerSalles();
        listeObjets = Chargement.chargerObjets(listeSalles);
        salleActuelle = listeSalles.get(r.nextInt(listeSalles.size()));

        // TODO: changer l'initialisation des objets
        // listeObjets.add(new Objet("steack", false));
        // listeObjets.add(new Objet("clé", false));

        // for(Salle s: listeSalles){
        //     s.setListeObjets(listeObjets);
        // }
        // System.out.println(joueur.getPV());

        // String rep;
        // listeObjets = Chargement.chargerObjets(listeSalles);
        // champi = listeObjets.get(1);
        // rep = champi.menuObjet();
        // switch (rep){
        //     case "2" : champi.utilisationObjet(joueur);
        //     break;
        // }


        while(menuAction());
    }

    public static boolean menuAction(){

        Scanner sc = new Scanner(System.in);
        String choix = "";
        PNJ pnj;
        Salle prochaineSalle;
        Objet o;

        salleActuelle.descriptionCourte();

        while(!choix.equals("1") && !choix.equals("2") && !choix.equals("3") && !choix.equals("4") && !choix.equals("5") && !choix.equals("10")){

            System.out.println("Que faire ?");
            System.out.println("1 - Regarder autour de vous");
            System.out.println("2 - Fouiller la pièce");
            System.out.println("3 - Parler à un personnage");
            System.out.println("4 - Se déplacer");
            System.out.println("5 - Attendre");
            System.out.println("10 - Arrêter");
            choix = sc.nextLine();

            // TODO: ajouter des événements quand trop d'erreurs d'affilée
            if(!choix.equals("1") && !choix.equals("2") && !choix.equals("3") && !choix.equals("4") && !choix.equals("5") && !choix.equals("10")){
                System.out.println("à venir");
            }

        }

        switch(choix){

            // décrire la salle
            case "1":
                System.out.println();
                salleActuelle.descriptionLongue();
                System.out.println();
                break;

            // fouiller la salle pour dénicher des objets
            case "2":
                System.out.println();
                o = salleActuelle.fouiller(joueur);
                // menu d'action de l'objet choisi
                if(o != null){
                    choix = o.menuObjet();
                    switch (choix){
                        case "2" : o.utilisationObjet(joueur);
                        break;
                    }
                }
                break;

            // interagir avec les PNJ
            case "3":
                pnj = salleActuelle.choisirPNJ();
                if(pnj != null){
                    // interagir
                }
                break;

            // changer de salle
            case "4":
                prochaineSalle = salleActuelle.choisirSalle();
                if(prochaineSalle != null){
                    salleActuelle = prochaineSalle;
                }
                break;

            // attendre
            case "5":
                System.out.println("à venir");
                break;

            // arrêter le jeu (temporaire)
            case "10":
                return false;
        }

        return true;

    }
    
}
