import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

            // on ignore la première ligne
            ligne = r.readLine();

            // on parcourt les lignes
            ligne = r.readLine();
            while(ligne != null){

                // on éclate chaque ligne avec un séparateur ';'
                String[] donnees = ligne.split(";");

                // s'il a trop ou pas assez de champs sur une ligne
                if(donnees.length != 4){
                    throw new IOException("Erreur dans le chargement des salles.");
                }

                s = new Salle(donnees[1], donnees[2]);

                if(donnees[3].compareTo(" ") != 0){
                    String[] numeros = donnees[3].split(",");
                    for(String numero: numeros){
                        noSalleAdjacente = Integer.parseInt(numero);
                        sAdjacente = listeSalles.get(noSalleAdjacente);
                        s.ajouterSalleAdjacente(sAdjacente);
                        sAdjacente.ajouterSalleAdjacente(s);
                    }
                }

                listeSalles.add(s);

                ligne = r.readLine();
            }

        } catch(IOException e){
            System.out.println(e);
        }

        return listeSalles;
    }

    public static void main(String args[]) throws IOException{

        ArrayList<Salle> listeSalles;

        listeSalles = chargerSalles();

        for(Salle s: listeSalles){
            s.descriptionLongue();
            System.out.println("----------------------");
        }
    }
    
}
