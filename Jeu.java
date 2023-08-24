import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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

        joueur = new Courgette("josé", 0, 0, 0, 0);
        listeSalles = Chargement.chargerSalles();
        salleActuelle = listeSalles.get(r.nextInt(listeSalles.size()));

        listeObjets.add(new Objet("steack", false));
        listeObjets.add(new Objet("clé", false));

        for(Salle s: listeSalles){
            s.setListeObjets(listeObjets);
        }

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

            // ajouter des événements quand trop d'erreurs d'affilée
            if(!choix.equals("1") && !choix.equals("2") && !choix.equals("3") && !choix.equals("4") && !choix.equals("5") && !choix.equals("10")){
                System.out.println("à venir");
            }

        }

        switch(choix){

            case "1":
                System.out.println();
                salleActuelle.descriptionLongue();
                System.out.println();
                break;

            case "2":
                o = salleActuelle.fouiller(joueur); // c'est ici qu'on pourra dénicher des objets
                if(o != null){
                    System.out.println(o.getNom());
                }
                break;

            case "3":
                pnj = salleActuelle.choisirPNJ();
                if(pnj != null){
                    // interagir
                }
                break;

            case "4":
                prochaineSalle = salleActuelle.choisirSalle();
                if(prochaineSalle != null){
                    salleActuelle = prochaineSalle;
                }
                break;

            case "5":
                System.out.println("à venir");
                break;

            case "10":
                return false;
        }

        return true;

    }
    
}
