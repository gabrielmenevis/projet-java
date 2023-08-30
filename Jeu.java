import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Objets.Objet;
import Salles.Salle;
import Personnages.*;


/**
 * Classe principale contenant la fonction main du jeu. Contient aussi des fonctions permettant d'afficher
 * des informations, et le menu principal affiché à chaque tour.
 * Possède trois variables accessibles depuis toutes les méthodes de la classe : la liste des salles,
 * la salle dans laquelle se trouve le joueur, et le joueur lui-même.
 */
public class Jeu {

    public static ArrayList<Salle> listeSalles;
    public static Salle salleActuelle;
    public static Personnage joueur;
    public static boolean gagner = false;
    

    /**
     * Fonction principale qui charge les salles, appelle les fonctions de création de personnage
     * et de début de jeu, puis lance la boucle principale
     * @param args
     * @throws IOException : fait appel à des méthodes qui lisent des fichiers
     */
    public static void main(String[] args) throws IOException {

        listeSalles = Chargement.chargerSalles();
        Chargement.chargerObjets(listeSalles);
        Chargement.chargerPNJ(listeSalles);
        salleActuelle = listeSalles.get(0);

        joueur = creationPersonnage();
        joueur.presentation();
        debutJeu();
    

        // lancement de la boucle principale
        while(menuAction());
    }

    /**
     * Affiche le texte introductif du jeu
     */
    public static void debutJeu(){
        System.out.println();
        System.out.println("''Comment allez-vous depuis hier soir? Nous sommes heureux de vous revoir parmi nous, et en vie!");
        System.out.println("Je ne sais pas si vous vous souvenez mais hier vous étiez en train de chercher quelque chose quand vous avez reçu un coup à la tête. C'etait quoi deja?");
        System.out.println("Ah oui ! Votre passeport ahah. Quel dommage...Je m'en souviens maintenant, vous vous êtes pris un sacré coup de branche sur la tête pendant que vous cherchiez votre passeport pour quitter ce club !");
        System.out.println("Quelle histoire tout de même...Je suis désolé que l'accident ait mis pause à votre projet, surtout quand on sait que ce Club Med a le don de nous faire égarer nos affares. ");
        System.out.println("Remarque, voyez le verre à moitié plein, en cherchant votre passeport vous tomberez peut-être sur d'autres objets interessants, qui sait?");
        System.out.println("Néanmoins, dans le cas où votre passeport ne réapparaittrait pas, nos équipes ont fait les démarches necessaires pour que vous en receviez un nouveau au plus vite !");
        System.out.println("C'est normal, vraiment, ça nous fais plaisir. Et evidemment le Club Med s'engage à prolonger votre séjour gratuitement tant que votre nouveau passeport n'est pas arrivé...ou que vous n'ayez retrouvé l'ancien.");
        System.out.println("Bonne suite de séjour ! ");
        System.out.println();
        System.out.println("Cordialement,");
        System.out.println();
        System.out.println("Le Staff''");
        System.out.println();
        System.out.println("......................");
        System.out.println();
        System.out.println("''J'ai mal au crâne...Mes souvenirs sont flous...Il faut que je retrouve ce foutu passeport...Vérifions une dernière fois qu'il n'est pas dans poche...Tiens, qu'est ce que c'est que ça?''");
        System.out.println();
        System.out.println("Félicitation "+joueur.getNom()+ ",vous venez de trouvé la carte n°1, d'autres sont dispérsées par-ci par-là dans le club et pourrons vous aider dans votre quête. Voyons ce qu'elle a à vous dire :");
        System.out.println();
        System.out.println("Carte 1 : ''Si de l'objet perdu vous voulez vous rapprocher, un petit creux vous guiderais vers une de celle qui peut vous aider...''");
        System.out.println("..........................................");
    }

    /**
     * Vérifie si le joueur a gagné la partie. Si oui, le texte de fin s'affiche
     * @return true (pour rester dans la boucle) si le joueur n'a pas gagné, sinon false
     */
    public static boolean finDuJeu(){
        if(gagner == false){
            System.out.println("Vous ne pouvez pas partir, vous n'avez pas votre passeport");
            return true;
        }
        else {

            System.out.println("Félicitation, Jupiter vous a rendu votre passeport. Ce coquin est lui même bloqué depuis des années. Et avec le temps il a même oublié pourquoi");
            System.out.println("Ainsi il a pris l'habitude depuis quelques années d'empecher certains autres habitants de partir. Cette année c'est tombez sur vous, voyez nous excusez ce désagrément");
            System.out.println("Le pauvre vieux n'a pas trouvé mieux comme occupation que de brutaliser nos clients avec ses énigmes à la noix...Mais comprenez, à cause du fait qu'il est bloqué depuis des années dans ce club med");
            System.out.println("Jupiter est l'un de nos plus ancien client, ainsi nous ne voulons pas lui faire de la peine en l'empechant de saboter le départ des autres... Je suis sur que vous nous comprenez.");
            System.out.println("En tout cas, on peut dire que vous avez réussi une mission impossible. Vous êtes le seul a avoir réussi a tenir bon et ne pas perdre la tête. Profitons de cet instant pour diriger nos pensées vers ceux qui ne sortiront peut-être jamais plus.");
            System.out.println("Vous avez du faire la connaissance de quelques uns d'ailleurs ! Armindo le peintre, Marinette la garagiste, ou encore Nagui le garagiste... ou alors est-il podologue? Je ne suis plus très sur... ");
            System.out.println("Pour vous féliciter d'avoir réussi a vaincre Jupiter, nous vous offrons 3 semaines suplémentaires au club ! Appelez nous et nous vous reserverons une chambre aussitôt.");
            System.out.println();
            System.out.println("Au plaisir de vous revoir,");
            System.out.println();
            System.out.println("Le Staff");
            System.out.println();
            System.out.println("PS : Evitez de passer près du grand chêne en partant...Une branche pourrait vous tomber sur la tête ! haha.");
            System.out.println();
            return false;
        }
    }
    
    /**
     * Gère le réveil du joueur à l'infirmerie après un game over. Vide la liste des PNJ de la dernière salle
     * où se trouvait le joueur, actualise la salle actuelle avec l'infirmerie, réinitialise les PV du joueur
     * au maximum, oublie que les salles ont été fouillées.
     */
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

    /**
     * Affiche la carte du jeu dans la console.
     */
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

    /**
     * Menu de création du personnage. Choix d'une classe de personnage et d'un nom.
     * @return : le personnage créé
     */
    public static Personnage creationPersonnage(){
        String rep = " ";
        String prenom;
        Personnage perso = null;
        Scanner sc = new Scanner(System.in);

        while((!rep.equals("1")) && (!rep.equals("2")) && (!rep.equals("3")) && (!rep.equals("4"))) {
            System.out.println();
            System.out.println("Bonjour, quel personnage souhaitez-vous incarner durant le jeu ? ");
            System.out.println();
            System.out.println("1 - Un sportif, fort en attaque mais très peu de charisme");
            System.out.println("2 - Un artiste, on vous trouve un certain charme mais vous n'êtes clairement pas le premier qu'on choisirait dans son equipe de sport");
            System.out.println("3 - Un employé de bureau, ni le plus charismatique ni le plus fort, vous excellez dans l'art d'être moyen");
            System.out.println("4 - Une courgette, laissez-vous suprendre par la courgette");
            rep = sc.nextLine();
        }

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
        }

        return perso;
    }
        

    // l'IOException peut venir du combat
    /**
     * Menu principal du jeu. Situé dans une pièce, plusieurs choix s'offrent au joueur. Différentes classes
     * et méthodes sont utilisées en fonction du choix.
     * @return : true si le jeu continue, false sinon (si le joueur a choisi d'arrêter)
     * @throws IOException : le menu fait appel à des méthodes qui lisent des fichiers et peuvent générer une IOException
     */
    public static boolean menuAction() throws IOException{

        Scanner sc = new Scanner(System.in);
        String choix = "";
        PNJ pnj;
        Salle prochaineSalle;
        Objet o;
        boolean mourir;

        // information sur la salle où se trouve le joueur
        System.out.println();
        salleActuelle.descriptionCourte();

        // choix de l'action
        while(!choix.equals("1") && !choix.equals("2") && !choix.equals("3") && !choix.equals("4") && !choix.equals("5") && !choix.equals("6") && !choix.equals("7") && !choix.equals("10")){

            System.out.println("Que faire ?");
            System.out.println("1 - Regarder autour de vous");
            System.out.println("2 - Fouiller la pièce");
            System.out.println("3 - Parler à un personnage");
            System.out.println("4 - Se déplacer");
            System.out.println("5 - Ouvrir l'inventaire");
            System.out.println("6 - Ouvrir la map");
            System.out.println("7 - Quitter le club med");
            System.out.println("10 - Arrêter");
            System.out.println();
            choix = sc.nextLine();

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
                // menu d'action de l'objet choisi (si le joueur a choisi un objet)
                if(o != null){
                    choix = o.menuObjetTrouve();
                    switch (choix){
                        case "2" : mourir = o.utilisationObjet(joueur); // le joueur a choisi d'utiliser l'objet
                            if (mourir == true) reveilInfirmerie(); // le joueur se réveille à l'infirmerie si l'objet le tue
                            break;
                        case "3": joueur.getInventaire().rangerObjet(o); // le joueur a choisi de ranger l'objet
                            break;
                    }
                }
                // sinon si le joueur est mort en combat après s'être fait attraper en fouillant la salle
                else if(joueur.getPV() <= 0){
                    reveilInfirmerie();
                }
                break;

            // interagir avec les PNJ
            case "3":
                boolean ok;
                pnj = salleActuelle.choisirPNJ(); // appel au menu de choix du PNJ
                if(pnj != null){ // si le joueur a choisi un PNJ
                  
                    choix = pnj.menuPNJ(); // choix de l'action
                    switch(choix){
                        case "1": ok = pnj.parler(); // parler au PNJ
                        if (ok == true){
                            gagner = true;
                        }
                        break;
                        
                        case "2": // donner un objet
                        joueur.donnerObjet(pnj);
                        break;

                        case "3": // lancer un combat
                        mourir = pnj.combattre(joueur);
                        System.out.println();
                        if(mourir){ // si le joueur est mort en combat
                            reveilInfirmerie();
                        }
                        break;

                        case "4":
                        System.out.println("Sans vous donner la peine de répondre, vous tournez le dos à " + pnj.decrire());
                        break;
                    }
                }
                break;

            // changer de salle
            case "4":
                prochaineSalle = salleActuelle.choisirSalle(); // choix parmi les salles adjacentes
                if(prochaineSalle != null){ // le joueur a choisi une salle, sinon il ne se passe rien
                    salleActuelle.viderPNJ(); // les PNJ aléatoires disparaissent
                    salleActuelle = prochaineSalle;
                    salleActuelle.genererPNJ(); // les PNJ aléatoires apparaissent dans la nouvelle salle
                }
                break;

            // ouvrir l'inventaire
            case "5":
                mourir = joueur.ouvrirInventaire();
                if(mourir){ // si le joueur utilise un objet qui le tue
                    reveilInfirmerie();
                }
                break;

            // Ouvrir la Map
            case "6": 
                ouvrirMap();
                break;

            // Quitter le club Med
            case "7":
                return finDuJeu();

            // arrêter le jeu
            case "10":
                return false;
        }
        
        return true;

    }
    
}
