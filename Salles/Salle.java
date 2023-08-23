package Salles;

import java.util.ArrayList;
import Personnages.Personnage;

public class Salle {

    private String nom;
    private String article;
    private ArrayList<Salle> sallesAdjacentes;
    private ArrayList<Personnage> listePNJ; // à remplacer éventuellement par une liste d'objets PNJ
    // liste d'objets

    public Salle(String nom, String article){
        this.nom = nom;
        this.article = article;
        this.listePNJ = new ArrayList<Personnage>();
        this.sallesAdjacentes = new ArrayList<Salle>();
    }

    public Salle(String nom, String article, ArrayList<Salle> sallesAdjacentes){
        this.nom = nom;
        this.article = article;
        this.sallesAdjacentes = sallesAdjacentes;
        this.listePNJ = new ArrayList<Personnage>();
    }
    
    public Salle(String nom, String article, ArrayList<Salle> sallesAdjacentes, ArrayList<Personnage> listePNJ){
        this.nom = nom;
        this.article = article;
        this.sallesAdjacentes = sallesAdjacentes;
        this.listePNJ = listePNJ;
    }

    public String getNom(){
        return this.nom;
    }

    public String getArticle(){
        return this.article;
    }

    public ArrayList<Salle> getSallesAdjacentes(){
        return this.sallesAdjacentes;
    }

    public ArrayList<Personnage> getListePNJ(){
        return this.listePNJ;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setArticle(String article){
        this.article = article;
    }

    public void setSallesAdjacentes(ArrayList<Salle> sallesAdjacentes){
        this.sallesAdjacentes = sallesAdjacentes;
    }

    public void setListePNJ(ArrayList<Personnage> listePNJ){
        this.listePNJ = listePNJ;
    }

    public void ajouterSalleAdjacente(Salle salleAdjacente){
        if(this.sallesAdjacentes.contains(salleAdjacente) == false){
            this.sallesAdjacentes.add(salleAdjacente);
        }
    }

    public void descriptionCourte(){
        System.out.println("Vous êtes dans " + this.article + " " + this.nom);
    }

    public void descriptionLongue(){

        this.descriptionCourte();

        if(this.listePNJ.size() > 0){
            System.out.println("Vous regardez autour de vous. Vous voyez : ");
            for(Personnage p: this.listePNJ){
                System.out.println(p.getNom());
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
}
