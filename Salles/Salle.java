package Salles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Personnages.*;
import Objets.Objet;

/**
 * Représentation d'une salle dans le jeu. Elle possède un nom, un article, une description, ainsi qu'une
 * liste de PNJ présents, d'objets présents et de salles adjacentes.
 * Un booléen indique si elle a été fouillée par le joueur.
 */
public class Salle {

    private String nom;
    private String article;
    private String description;
    private ArrayList<Salle> sallesAdjacentes;
    private ArrayList<PNJ> listePNJ;
    private ArrayList<Objet> listeObjets;
    private boolean fouille;

    /**
     * Constructeur de la salle. Initialise des listes vides pour les PNJ, les salles adjacentes et
     * les objets. Initialise l'indicateur fouille à false.
     * @param nom : nom de la salle
     * @param article : article à afficher devant le nom
     * @param description : description de la salle
     */
    public Salle(String nom, String article, String description){
        this.nom = nom;
        this.article = article;
        this.description = description;
        this.sallesAdjacentes = new ArrayList<Salle>();
        this.listePNJ = new ArrayList<PNJ>();
        this.listeObjets = new ArrayList<Objet>();
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
    
    public void setFouille(boolean fouille){
        this.fouille = fouille;
    }

    /**
     * Ajoute une salle à la liste des salles adjacentes de la salle.
     * @param salleAdjacente : salle à ajouter à la liste
     */
    public void ajouterSalleAdjacente(Salle salleAdjacente){
        if(this.sallesAdjacentes.contains(salleAdjacente) == false){
            this.sallesAdjacentes.add(salleAdjacente);
        }
    }

    /**
     * Ajoute un objet à la liste des objets de la salle.
     * @param objet : objet à ajouter à la liste
     */
    public void ajouterObjet(Objet objet){
        if(this.listeObjets.contains(objet) == false){
            this.listeObjets.add(objet);
        }
    }

    /**
     * Ajoute un PNJ à la liste des PNJ de la salle.
     * @param pnj : pnj à ajouter à la liste
     */
    public void ajouterPNJ(PNJ pnj){
        if(this.listePNJ.contains(pnj) == false){
            this.listePNJ.add(pnj);
        }
    }

    /**
     * Affiche une description courte de la salle (son nom, essentiellement).
     */
    public void descriptionCourte(){
        System.out.println("Vous êtes dans " + this.article + " " + this.nom);
    }

    /**
     * Affiche une description longue de la salle avec la description, les PNJ présents et les salles
     * adjacentes.
     */
    public void descriptionLongue(){

        // commence par afficher la description courte et la phrase de description
        this.descriptionCourte();
        System.out.println(this.description);

        // s'il y a des PNJ dans la salle
        if(this.listePNJ.size() > 0){
            System.out.println("Vous regardez autour de vous. Vous voyez : ");
            for(PNJ p: this.listePNJ){
                System.out.println(p.getNom() + " " + p.getArticle() + " " + p.getType());
            }
        }
        // sinon
        else{
            System.out.println("Vous regardez autour de vous. Vous ne voyez personne.");
        }

        // s'il y a des salles adjacentes
        if(this.sallesAdjacentes.size() > 0){
            System.out.println("Vous pouvez vous rendre : ");
            for(Salle s: this.sallesAdjacentes){
                System.out.println("Dans " + s.getArticle() + " " + s.getNom());
            }
        }
        // sinon
        else{
            System.out.println("Vous ne pouvez aller nulle part. Ce tombeau sera votre tombeau !");
        }
    }

    /**
     * Génère de 0 à 3 PNJ aléatoirement (nom, type et réplique piochés dans un fichier csv).
     * Ajoute ces PNJ à la liste de PNJ de la salle.
     * @throws IOException
     */
    public void genererPNJ() throws IOException {

        Random r = new Random();
        int tirage, tirage2, pv, pa, pc, totalPoints;
        List<String> donnees;
        String nom, article, type, replique;
        PNJ pnj;

        // 3/4 de générer au moins un PNJ, 2/4 d'en générer au moins deux, 1/4 d'en générer 3
        tirage = r.nextInt(4);
        for(int i = 0 ; i < tirage ; i++){

            // tirage du nom et article
            donnees = Files.readAllLines(Paths.get("./files/pnj_hasard/nom_pnj.csv"));
            tirage2 = r.nextInt(donnees.size());
            nom = donnees.get(tirage2).split(";")[0];
            article = donnees.get(tirage2).split(";")[1];

            // tirage du type
            donnees = Files.readAllLines(Paths.get("./files/pnj_hasard/type_pnj.csv"));
            tirage2 = r.nextInt(donnees.size());
            type = donnees.get(tirage2);

            // tirage de la réplique
            donnees = Files.readAllLines(Paths.get("./files/pnj_hasard/phrase_pnj.csv"));
            tirage2 = r.nextInt(donnees.size());
            replique = donnees.get(tirage2);

            // génération des stats au hasard en tenant compte de contraintes
            totalPoints = 180; // total de points à 180
            pv = 80 + (r.nextInt(5) * 10); // les PV vont de 80 à 120
            totalPoints -= pv;
            pa = (totalPoints / 2) - 10 + (r.nextInt(3) * 10); // les PA vont de -10 à +10 la moitié des points restants
            pc = totalPoints - pa; // les PC prennent les points restants

            // génération du PNJ
            pnj = new PNJ(nom, type, article, replique, pv, pa, pc);
            this.listePNJ.add(pnj);
        }
    }

    /**
     * Vide la liste des PNJ de la salle, sauf les PNJ spéciaux et le boss qui doivent être tout le temps
     * présents.
     */
    public void viderPNJ(){
        this.listePNJ.removeIf(pnj -> !(pnj instanceof PNJSpecial || pnj instanceof Boss));
    }

    /**
     * Menu pour afficher les objets présents dans la pièce (objets uniques et objets générés aléatoirement
     * parmi les objets possibles dans la pièce).
     * Le joueur peut se faire attraper en train de fouiller si la pièce a déjà été fouillé et que des PNJ sont
     * présents. Les PNJ spéciaux ou le boss n'attrapent pas le joueur en train de fouiller, les PNJ aléatoires
     * déjà vaincus non plus.
     * @param perso : le personnage qui fouille la pièce
     * @return l'objet récupéré, ou null si le joueur ne récupère aucun objet
     * @throws IOException
     */
    public Objet fouiller(Personnage perso) throws IOException {

        int i;
        Objet objetChoisi = null;
        Scanner sc = new Scanner(System.in);
        String choix;
        boolean continuer = true, attrape;
        HashMap<String, Objet> choixObjet = new HashMap<String, Objet>();
        ArrayList<Objet> listeObjetsTemp = new ArrayList<Objet>();
        ArrayList<PNJ> listePNJTemp = new ArrayList<PNJ>();
        Random r = new Random();
        int tirage;
        PNJ pnjAttrape;

        System.out.println("Vous fouillez " + this.article + " " + this.nom + " dans ses moindres recoins.");

        // constitution de la liste des PNJ aléatoires qui n'ont pas encore été vaincus par le joueur
        // seuls eux peuvent attraper le joueur en train de fouiller
        for(PNJ pnj: this.listePNJ){
            if(!(pnj instanceof PNJSpecial) && !(pnj instanceof Boss) && !(pnj.getVaincu())){
                listePNJTemp.add(pnj);
            }
        }

        // tirage pour voir si vous échappez à la vigilance des PNJ
        // si la pièce n'a jamais été fouillée, ils ne le feront pas remarquer, sinon ils interceptent
        tirage = r.nextInt(4);
        if(tirage >= listePNJTemp.size()){
            attrape = false;
        }
        else{
            attrape = true;
        }

        // la pièce n'a jamais été fouillée ou il n'y a aucun PNJ pour vous attraper
        if((!this.fouille) || (!attrape)){

            // constitution de la liste des objets apparus
            for(Objet o: this.listeObjets){
                if(o.apparaitre()){
                    listeObjetsTemp.add(o);
                }
            }

            // des objets sont apparus dans la pièce
            if(!listeObjetsTemp.isEmpty()){

                System.out.println("Hourra ! Ce que vous avez trouvé pourrait bien vous être utile...");

                // boucle pour le choix de l'objet
                while(continuer){

                    System.out.println("Que voulez-vous examiner ?");

                    i = 1;
                    // liste des objets
                    for(Objet o: listeObjetsTemp){
                        choixObjet.put(String.valueOf(i), o); // dictionnaire de choix possibles
                        System.out.println((i++) + " - " + o.getArticleDefini() + " " + o.getNom());
                    }
                    // choix "Annuler" pour retourner null
                    choixObjet.put(String.valueOf(i), null);
                    System.out.println(i + " - Annuler");

                    choix = sc.nextLine();
                    // si le choix est valide
                    if(choixObjet.containsKey(choix)){
                        continuer = false;
                        objetChoisi = choixObjet.get(choix);
                    }
                }
            }

            // la pièce est vide
            else{
                System.out.println("Vous ne trouvez rien. Était-ce vraiment nécessaire de passer tout ce temps à fouiller une pièce... vide ?");
                objetChoisi = null;
            }

            // la pièce est maintenant fouillée
            this.fouille = true;

            return objetChoisi;
        }

        // la pièce a déjà été fouillée et les PNJ vous attrapent
        else{

            pnjAttrape = listePNJTemp.get(tirage);

            System.out.println("Vous pensez que fouiller " + this.article + " " + this.nom + " de fond en comble est la meilleure manière de passer inaperçu ??!");
            pnjAttrape.attraper(perso);

            return null;
        }
    }

    /**
     * Menu pour choisir le PNJ avec qui interagir parmi la liste de PNJ de la salle.
     * @return le pnj choisi ou null si le joueur annule
     */
    public PNJ choisirPNJ(){

        // s'il y a des PNJ dans la salle
        if(!this.listePNJ.isEmpty()){

            int i;
            boolean continuer = true;
            HashMap<String, PNJ> choixPNJ = new HashMap<String, PNJ>();
            PNJ PNJChoisi = null;
            String choix;
            Scanner sc = new Scanner(System.in);

            System.out.println("Attention, vous n'êtes pas seul(e)... Vous pensez qu'ils vous observent ?");
            while(continuer){

                i = 1;
                System.out.println("À qui voulez-vous parler ?");
                for(PNJ pnj: this.listePNJ){
                    choixPNJ.put(String.valueOf(i), pnj); // dictionnaire de choix possibles
                    System.out.println((i++) + " - " + pnj.getNom() + " " + pnj.getArticle() + " " + pnj.getType());
                }
                // choix "Annuler" pour retourner null
                choixPNJ.put(String.valueOf(i), null);
                System.out.println(i + " - Annuler");

                choix = sc.nextLine();
                // si le choix est valide
                if(choixPNJ.containsKey(choix)){
                    continuer = false;
                    PNJChoisi = choixPNJ.get(choix);
                }
                
            }

            return PNJChoisi;
        }

        else{ // personne dans la pièce
            System.out.println("C'est calme, très calme. En fait vous êtes seul(e). Très seul(e).");
            return null;
        }
    }

    /**
     * Menu pour choisir la salle où se rendre parmi les salles adjacentes.
     * @return la salle choisie ou null si le joueur annule
     */
    public Salle choisirSalle(){

        Scanner sc = new Scanner(System.in);
        Salle prochaineSalle = null;
        String choix;
        int i;
        boolean continuer = true;
        HashMap<String, Salle> choixSalle = new HashMap<String, Salle>();

        if(!this.sallesAdjacentes.isEmpty()){

            System.out.println("Où ça ?");
            while(continuer){
                // liste des salles adjacentes
                i = 1;
                for(Salle s: this.sallesAdjacentes){
                    choixSalle.put(String.valueOf(i), s); // dictionnaire des choix possibles
                    System.out.println((i++) + " - Dans " + s.getArticle() + " " + s.getNom());
                }
                // choix "Annuler" retourne null
                choixSalle.put(String.valueOf(i), null);
                System.out.println(i + " - Annuler");

                choix = sc.nextLine();
                // si le choix est valide
                if(choixSalle.containsKey(choix)){
                    continuer = false;
                    prochaineSalle = choixSalle.get(choix);
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
