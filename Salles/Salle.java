package Salles;

import java.util.ArrayList;
import java.util.Scanner;

import Personnages.*;
import Objets.Objet;

public class Salle {

    private String nom;
    private String article;
    private String description;
    private ArrayList<Salle> sallesAdjacentes;
    private ArrayList<PNJ> listePNJ;
    private ArrayList<Objet> listeObjets;
    private boolean fouille;

    public Salle(String nom, String article){
        this.nom = nom;
        this.article = article;
        this.description = "Rien de particulier à noter sur cette salle";
        this.listePNJ = new ArrayList<PNJ>();
        this.sallesAdjacentes = new ArrayList<Salle>();
        this.listeObjets = new ArrayList<>();
        this.fouille = false;
    }

    public Salle(String nom, String article, String description){
        this.nom = nom;
        this.article = article;
        this.description = description;
        this.sallesAdjacentes = new ArrayList<Salle>();
        this.listePNJ = new ArrayList<PNJ>();
        this.listeObjets = new ArrayList<>();
        this.fouille = false;
    }
    
    public Salle(String nom, String article, String description, ArrayList<Salle> sallesAdjacentes, ArrayList<PNJ> listePNJ){
        this.nom = nom;
        this.article = article;
        this.description = description;
        this.sallesAdjacentes = sallesAdjacentes;
        this.listePNJ = listePNJ;
        this.listeObjets = new ArrayList<>();
        this.fouille = false;
    }

    public String getNom(){
        return this.nom;
    }

    public String getArticle(){
        return this.article;
    }

    public String getDescription(){
        return this.description;
    }

    public ArrayList<Salle> getSallesAdjacentes(){
        return this.sallesAdjacentes;
    }

    public ArrayList<PNJ> getListePNJ(){
        return this.listePNJ;
    }

    public ArrayList<Objet> getListeObjets(){
        return this.listeObjets;
    }

    public boolean getFouille(){
        return this.fouille;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setArticle(String article){
        this.article = article;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setSallesAdjacentes(ArrayList<Salle> sallesAdjacentes){
        this.sallesAdjacentes = sallesAdjacentes;
    }

    public void setListePNJ(ArrayList<PNJ> listePNJ){
        this.listePNJ = listePNJ;
    }

    public void setListeObjets(ArrayList<Objet> listeObjets){
        this.listeObjets = listeObjets;
    }

    public void ajouterSalleAdjacente(Salle salleAdjacente){
        if(this.sallesAdjacentes.contains(salleAdjacente) == false){
            this.sallesAdjacentes.add(salleAdjacente);
        }
    }

    public void ajouterObjet(Objet objet){
        if(this.listeObjets.contains(objet) == false){
            this.listeObjets.add(objet);
        }
    }

    public void descriptionCourte(){
        System.out.println("Vous êtes dans " + this.article + " " + this.nom);
    }

    public void descriptionLongue(){

        this.descriptionCourte();
        System.out.println(this.description);

        if(this.listePNJ.size() > 0){
            System.out.println("Vous regardez autour de vous. Vous voyez : ");
            for(PNJ p: this.listePNJ){ // TODO: attendre la classe PNJ
                // System.out.println(p.getNom());
            }
        }

        else{
            System.out.println("Vous regardez autour de vous. Vous ne voyez personne.");
        }

        if(this.sallesAdjacentes.size() > 0){
            System.out.println("Vous pouvez vous rendre : ");
            for(Salle s: this.sallesAdjacentes){
                System.out.println("Dans " + s.getArticle() + " " + s.getNom());
            }
        }

        else{
            System.out.println("Vous ne pouvez aller nulle part. Ce tombeau sera votre tombeau !");
        }
    }

    public Objet fouiller(Personnage perso){

        int i, indexObjetChoisi = -1;
        Objet objetChoisi = null;
        Scanner sc = new Scanner(System.in);
        String choix;
        boolean continuer = true;

        System.out.println("Vous fouillez " + this.article + " " + this.nom + " dans ses moindres recoins.");

        // la pièce n'a jamais été fouillée ou il n'y a aucun PNJ pour vous attraper
        if((!this.fouille) || this.listePNJ.isEmpty()){

            // il y a des objets dans la pièce
            if(!this.listeObjets.isEmpty()){

                System.out.println("Hourra ! Ce que vous avez trouvé pourrait bien vous être utile...");

                // boucle pour le choix de l'objet
                while(continuer){

                    System.out.println("Que voulez-vous examiner ?");

                    i = 1;
                    // liste des objets
                    for(Objet o: this.listeObjets){
                        System.out.println((i++) + " - " + o.getNom());
                    }
                    System.out.println(i + " - Annuler");

                    choix = sc.nextLine();
                    // si le joueur a choisi annuler
                    if(Integer.parseInt(choix) == (this.listeObjets.size() + 1)){
                        this.fouille = true;
                        return null;
                    }
                    else{
                        for(i = 1 ; i <= this.listeObjets.size() ; i++){
                            if(Integer.parseInt(choix) == i){
                                indexObjetChoisi = i - 1;
                            }
                        }
                    }

                    // si le choix n'est pas valide
                    if(indexObjetChoisi == -1){
                        continuer = true;
                    }
                    else{
                        objetChoisi = this.listeObjets.get(indexObjetChoisi);
                        continuer = false;
                    }
                }
            }

            // la pièce est vide
            else{
                // et elle a déjà été fouillée
                if(fouille){
                    System.out.println("À quoi vous attendiez-vous ? Il n'y avait rien la première fois, ça n'a pas changé.");
                }
                // ou pas
                else{
                    System.out.println("Vous ne trouvez rien. Était-ce vraiment nécessaire de passer tout ce temps à fouiller une pièce... vide ?");
                }

                objetChoisi = null;
            }

            // la pièce est maintenant fouillée
            this.fouille = true;

            return objetChoisi;
        }

        // la pièce a été fouillée et il y a au moins un PNJ
        else{
            System.out.println("malheur ! machin vous a attrapé");
            return null;
        }
    }

    public PNJ choisirPNJ(){
        if(!this.listePNJ.isEmpty()){
            // TODO: afficher la liste de PNJ et retourner le PNJ choisi
            return null;
        }
        else{
            System.out.println("C'est calme, très calme. En fait vous êtes seul(e). Très seul(e).");
            return null;
        }
    }

    public Salle choisirSalle(){

        Scanner sc = new Scanner(System.in);
        Salle prochaineSalle = null;
        String choix;
        int i, indexProchaineSalle = -1;
        boolean continuer = true;

        if(!this.sallesAdjacentes.isEmpty()){

            System.out.println("Où ça ?");
            while(continuer){
                // liste des salles adjacentes
                i = 1;
                for(Salle s: this.sallesAdjacentes){
                    System.out.println((i++) + " - Dans " + s.getArticle() + " " + s.getNom());
                }
                System.out.println(i + " - Annuler");

                choix = sc.nextLine();
                // si le joueur a choisi annuler
                if(Integer.parseInt(choix) == (this.sallesAdjacentes.size() + 1)){
                    return null;
                }
                else{
                    for(i = 1 ; i <= this.sallesAdjacentes.size() ; i++){
                        if(Integer.parseInt(choix) == i){
                            indexProchaineSalle = i - 1;
                        }
                    }
                }

                // si le choix n'est pas valide
                if(indexProchaineSalle == -1){
                    continuer = true;
                }
                else{
                    prochaineSalle = this.sallesAdjacentes.get(indexProchaineSalle);
                    continuer = false;
                }
            }

            return prochaineSalle;
        }

        // pas de salles adjacentes (normalement ça n'arrive pas)
        else{
            System.out.println("On dirait que vous êtes bloqué(e) dans " + this.article + " " + this.nom + ".");
            return null;
        }
    }
}
