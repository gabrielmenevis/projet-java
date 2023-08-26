import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Objets.Objet;
import Objets.ObjetConsommable;
import Objets.ObjetUnique;
import Personnages.PNJSpecial;
import Salles.Salle;

public class Chargement {

    public static ArrayList<Salle> chargerSalles() throws IOException {

        String fichier = ".\\files\\salles.csv";
        FileReader f = new FileReader(fichier);
        BufferedReader r = new BufferedReader(f);
        String ligne;
        ArrayList<Salle> listeSalles = new ArrayList<Salle>();
        Salle s, sAdjacente;
        int noSalleAdjacente;

        try{

            // on compte le nombre de colonnes
            ligne = r.readLine();
            int nombreColonnes = ligne.split(";").length;

            // on parcourt les lignes
            ligne = r.readLine();
            while(ligne != null){

                // on éclate chaque ligne avec un séparateur ';'
                String[] donnees = ligne.split(";");

                // s'il a trop ou pas assez de champs sur une ligne
                if(donnees.length != nombreColonnes){
                    throw new IOException();
                }

                // appel au constructeur avec nom, article, description
                String nom = donnees[1];
                String article = donnees[2];
                String description = donnees[4];
                s = new Salle(nom, article, description);

                if(donnees[3].compareTo("") != 0){
                    String[] numeros = donnees[3].split(",");
                    for(String numero: numeros){
                        noSalleAdjacente = Integer.parseInt(numero);
                        sAdjacente = listeSalles.get(noSalleAdjacente);
                        s.ajouterSalleAdjacente(sAdjacente);
                        sAdjacente.ajouterSalleAdjacente(s);
                    }
                }

                // on ajoute la nouvelle salle à la liste
                listeSalles.add(s);

                // ligne suivante dans le fichier
                ligne = r.readLine();
            }

        } catch(IOException e){
            System.out.println("Erreur dans le chargement des salles.");
        }

        return listeSalles;
    }


    public static void chargerObjets(ArrayList<Salle> listeSalles) throws IOException{

        ArrayList<Objet> listeObjets = new ArrayList<Objet>();
        Objet o;
        String fichier = ".\\files\\objets.csv";
        FileReader f = new FileReader(fichier);
        BufferedReader r = new BufferedReader(f);
        String ligne;

        // variables pour stocker les données des objets lus
        String nom, articleDefini, articleIndefini, attributTouche, utilisation, effet;
        int valeurAjoutee, indexSalle, probaSpawn;


        try {

            // on compte le nombre de colonnes
            ligne = r.readLine();
            int nombreColonnes = ligne.split(";").length;

            // on parcourt les lignes
            ligne = r.readLine();
            while(ligne != null){

                // on éclate chaque ligne avec un séparateur ';'
                String[] donnees = ligne.split(";");

                // s'il a trop ou pas assez de champs sur une ligne
                if(donnees.length != nombreColonnes){
                    throw new IOException();
                }

                // appel au constructeur avec tous les arguments requis
                nom = donnees[0];
                articleDefini = donnees[1];
                articleIndefini = donnees[2];
                valeurAjoutee = Integer.parseInt(donnees[3]);
                attributTouche = donnees[4];
                utilisation = donnees[5];
                effet = donnees[6];
                probaSpawn = Integer.parseInt(donnees[9]);
                if(donnees[8].equals("1")){ // créer un objet consommable
                    o = new ObjetConsommable(nom, articleDefini, articleIndefini, valeurAjoutee, attributTouche, utilisation, effet, probaSpawn);
                }
                else{ // créer un objet unique
                    o = new ObjetUnique(nom, articleDefini, articleIndefini, valeurAjoutee, attributTouche, utilisation, effet);
                }

                // ajout à la liste des objets de la salle
                indexSalle = Integer.parseInt(donnees[7]);
                if((indexSalle >= listeSalles.size()) || (indexSalle < 0)){ // index invalide
                    System.out.println("Problème au chargement des objets. Salle introuvable pour " + o.getNom());
                }
                else{
                    listeSalles.get(indexSalle).ajouterObjet(o);
                }

                // ligne suivante
                ligne = r.readLine();
            }

        } catch(IOException e){
            System.out.println("Erreur dans le chargement des objets.");
        }
    }


    public static void chargerPNJ(ArrayList<Salle> listeSalles) throws IOException{

        ArrayList<PNJSpecial> listePNJ = new ArrayList<PNJSpecial>();
        PNJSpecial pnj;
        String fichier = ".\\files\\pnj.csv";
        FileReader f = new FileReader(fichier);
        BufferedReader r = new BufferedReader(f);
        String ligne;

        // variables pour stocker les données des objets lus
        String nom, type, replique, indice;
        int max_pv, pa, pc, indexSalle;

        try{

            // on compte le nombre de colonnes
            ligne = r.readLine();
            int nombreColonnes = ligne.split(";").length;

            // on parcourt les lignes
            ligne = r.readLine();

            while(ligne != null){

                // on éclate chaque ligne avec un séparateur ';'
                String[] donnees = ligne.split(";");

                // s'il a trop ou pas assez de champs sur une ligne
                if(donnees.length != nombreColonnes){
                    throw new IOException();
                }

                // appel au constructeur avec tous les arguments requis
                nom = donnees[0];
                type = donnees[1];
                max_pv = Integer.parseInt(donnees[2]);
                pa = Integer.parseInt(donnees[3]);
                pc = Integer.parseInt(donnees[4]);
                replique = donnees[5];
                indice = donnees[6];
                pnj = new PNJSpecial(nom, type, indice, replique, max_pv, pa, pc);

                // ajout à la liste des PNJ de la salle
                indexSalle = Integer.parseInt(donnees[7]);
                if((indexSalle >= listeSalles.size()) || (indexSalle < 0)){ // index invalide
                    System.out.println("Problème au chargement des PNJ. Salle introuvable pour " + pnj.getNom());
                }
                else{
                    listeSalles.get(indexSalle).ajouterPNJ(pnj);
                }

                // ligne suivante
                ligne = r.readLine();
            }

        } catch(IOException e){
            System.out.println("Erreur dans le chargement des objets");
        }

    }
    
}
