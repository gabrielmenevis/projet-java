import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Objets.Objet;
import Salles.Salle;
import Personnages.*;
import combats.combats;

public class Jeu {

    public static ArrayList<Salle> listeSalles;
    public static Salle salleActuelle;
    public static Personnage joueur;

    public static void main(String[] args) throws IOException {

        Random r = new Random();

        listeSalles = Chargement.chargerSalles();
        Chargement.chargerObjets(listeSalles);
        Chargement.chargerPNJ(listeSalles);
        salleActuelle = listeSalles.get(0);

        joueur = creationPersonnage();
        joueur.presentation();
        System.out.println("Bonjour "+ joueur.getNom()+ " bien ou bien ?  ");

        // lancement de la boucle principale
        while(menuAction());
    }

    public static void reveilInfirmerie(){

        // message informatif
        System.out.println("Olala, on vous envoie à l'infirmerie pour réanimation... Ne perdez pas espoir, vous finirez bien par quitter cet endroit.");

        // vider la liste de PNJ de la pièce dans laquelle on est mort
        salleActuelle.viderPNJ();
        
        // retour à l'infirmerie
        salleActuelle = listeSalles.get(0);

        // on oublie que les salles ont été fouillées
        for(Salle s: listeSalles){
            s.setFouille(false);
        }

        joueur.resetPV(); // on redonne au joueur mort tous ses pv de base.
    }

    // affichage de la map
    public static void ouvrirMap(){
        System.out.println("      _____________                                         _______________");
        System.out.println("     |   Salle de  |                                       |   Salle des   |");
        System.out.println("     |    musique  |                                       |    douches    |");
        System.out.println("     |_______  ____|__________        _____________________|_______  ___  _|______");
        System.out.println("         |        Salle       |      |    Bar       |     Salle de    |           |");
        System.out.println("         |    des fêtes       |      |   extérieur         sport         Hammam   |");
        System.out.println(" ________|______  ____________|______|_  ________  _|___________  ____|  _________| ");
        System.out.println("|        |                                |                               |");
        System.out.println("|                   Salle de              |             Piscine           |");
        System.out.println("|Cuisine |          réception                                             |");
        System.out.println("|________|                                |_______________________________|");
        System.out.println("         |________________________________|");
        System.out.println("                          |          |");
        System.out.println("                          |Infirmerie|");
        System.out.println("                          |__________|");
    }

    public static Personnage creationPersonnage(){
        String rep = " ";
        String prenom;
        Personnage perso = null;
        Scanner sc = new Scanner (System.in);

        while((!rep.equals("1")) && (!rep.equals("2")) && (!rep.equals("3")) && (!rep.equals("4"))) {
        System.out.println("Bonjour, quel personnage souhaitez-vous incarner durant le jeu ? ");
        System.out.println("1 - Un sportif, fort en attaque mais très peu de charisme");
        System.out.println("2 - Un artiste, on vous trouve un certain charme mais vous n'êtes clairement pas le premier qu'on choisirais dans son equipe de sport");
        System.out.println("3 - Un employé de bureau, ni le plus charismatique ni le plus fort, vous excellez dans l'art d'être moyen");
        System.out.println("4 - Une courgette, laissez-vous suprendre par la courgette");
        rep = sc.nextLine();
        

            switch(rep){

                case "1": System.out.println("Ok le sportif, et c'est quoi votre petit nom?");
                         prenom = sc.nextLine();
                         perso = new Sportif(prenom);
                break;
                case "2" :System.out.println("Ok l'artiste, et c'est quoi votre petit nom?");
                         prenom = sc.nextLine();
                         perso = new Artiste(prenom);
                break;        
                case "3": System.out.println("Comme c'est original, et c'est quoi votre petit nom?");
                         prenom = sc.nextLine();
                         perso = new  Employe(prenom);
                break;
                case "4": System.out.println("Très bon choix, et quel est le nom de cette jeune Courgette?");
                         prenom = sc.nextLine();
                         perso = new Courgette(prenom);
                break;
                default : System.out.println("ce personnage n'existe pas");   
                break;           
            }
        }

        return perso;
    }
        

    // l'IOException peut venir du combat
    public static boolean menuAction() throws IOException{

        Scanner sc = new Scanner(System.in);
        String choix = "";
        PNJ pnj;
        Salle prochaineSalle;
        Objet o;
        combats combat;
        boolean mourir;

        System.out.println();
        salleActuelle.descriptionCourte();

        while(!choix.equals("1") && !choix.equals("2") && !choix.equals("3") && !choix.equals("4") && !choix.equals("5") && !choix.equals("6") && !choix.equals("7") && !choix.equals("10")){

            System.out.println("Que faire ?");
            System.out.println("1 - Regarder autour de vous");
            System.out.println("2 - Fouiller la pièce");
            System.out.println("3 - Parler à un personnage");
            System.out.println("4 - Se déplacer");
            System.out.println("5 - Ouvrir l'inventaire");
            System.out.println("6 - Attendre");
            System.out.println("7 - Ouvrir la map");
            System.out.println("10 - Arrêter");
            choix = sc.nextLine();

            // TODO: ajouter des événements quand trop d'erreurs d'affilée
            if(!choix.equals("1") && !choix.equals("2") && !choix.equals("3") && !choix.equals("4") && !choix.equals("5") && !choix.equals("6") && !choix.equals("7") && !choix.equals("10")){
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
                    choix = o.menuObjetTrouve();
                    switch (choix){
                        case "2" : mourir = o.utilisationObjet(joueur);
                        if (mourir == true) reveilInfirmerie();
                        break;
                        case "3": joueur.getInventaire().rangerObjet(o);
                        break;
                    }
                }
                break;

            // interagir avec les PNJ
            case "3":
                pnj = salleActuelle.choisirPNJ();
                if(pnj != null){
                  
                    choix = pnj.menuPNJ();
                    switch(choix){
                        // TODO: parler encore au PNJ
                        case "1": System.out.println("deux secondes");
                        break;
                        
                        case "2": // donner un objet
                        joueur.donnerObjet(pnj);
                        break;

                        case "3": // lancer un combat
                        combat = new combats(joueur, pnj);
                        mourir = combat.lancerCombat();
                        System.out.println();
                        if(mourir){
                            reveilInfirmerie();
                        }
                        break;

                        case "4":
                        System.out.println("Sans vous donnez la peine de répondre, vous tournez le dos à " + pnj.decrire());
                        break;
                    }
                }
                break;

            // changer de salle
            case "4":
                prochaineSalle = salleActuelle.choisirSalle();
                if(prochaineSalle != null){ // le joueur a choisi une salle, sinon il ne se passe rien
                    salleActuelle.viderPNJ();
                    salleActuelle = prochaineSalle;
                    salleActuelle.genererPNJ();
                }
                break;

            // ouvrir l'inventaire
            case "5":
                mourir = joueur.ouvrirInventaire();
                if(mourir){
                    reveilInfirmerie();
                }
                break;

            case "6":
                System.out.println("à venir");
                break;
                // Ouvrir la Map
            case "7": 
                ouvrirMap();
                break;



            // arrêter le jeu (temporaire)
            case "10":
                return false;
        }

        return true;

    }
    
}
