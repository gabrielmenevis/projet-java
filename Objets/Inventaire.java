package Objets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Inventaire {

    private HashMap<Objet, Integer> listeObjets;
    private ArrayList<ObjetUnique> objetsUniquesUtilises;

    public Inventaire(){
        listeObjets = new HashMap<Objet, Integer>();
        objetsUniquesUtilises = new ArrayList<ObjetUnique>();
    }

    public HashMap<Objet, Integer> getListeObjets(){
        return this.listeObjets;
    }

    public ArrayList<ObjetUnique> getObjetsUniquesUtilises(){
        return this.objetsUniquesUtilises;
    }

    public void setListeObjets(HashMap<Objet, Integer> listeObjets){
        this.listeObjets = listeObjets;
    }

    public void setObjetsUniquesUtilises(ArrayList<ObjetUnique> objetsUniquesUtilises){
        this.objetsUniquesUtilises = objetsUniquesUtilises;
    }

    public void rangerObjet(Objet objet){

        // si l'objet est consommable on peut en avoir de 0 à n
        if(objet instanceof ObjetConsommable){

            int quantiteObjet;

            // l'inventaire contient déjà l'objet, on incrémente la quantité
            if(this.listeObjets.containsKey(objet)){
                quantiteObjet = this.listeObjets.get(objet) + 1;
                this.listeObjets.replace(objet, quantiteObjet);
                System.out.println();
                System.out.println("Et hop ! " + objet.getArticleIndefini() + " " + objet.getNom() + " de plus !");
                System.out.println("Vous en avez maintenant " + quantiteObjet);
                System.out.println();
            }
            // l'inventaire ne contient pas l'objet, la quantité est 1
            else{
                quantiteObjet = 1;
                this.listeObjets.put(objet, quantiteObjet);
                System.out.println();
                System.out.println("Vous rangez " + objet.getArticleDefini() + " " + objet.getNom() + " dans votre inventaire.");
                System.out.println("Le début d'une longue série ?");
                System.out.println();
            }
        }

        // l'objet est unique, si on le range dans l'inventaire c'est qu'il n'y est pas déjà 
        else{
            this.listeObjets.put(objet, 1);
            System.out.println();
            System.out.println("Vous rangez " + objet.getArticleDefini() + " " + objet.getNom() + " dans votre inventaire.");
            System.out.println("Gardez " + objet.getArticleDefini() + " précieusement, ça ne court pas les rues...");
            System.out.println();
            // on retient que l'objet a déjà été pris pour qu'il n'apparaisse pas à nouveau dans la salle
            ((ObjetUnique) objet).setDejaPris(true);
        }
    }

    // décrémente l'objet de l'inventaire
    public boolean enleverObjet(Objet objet){

        int quantiteObjet;

        // on s'assure que l'objet est bien présent dans la liste d'objets
        if(this.listeObjets.containsKey(objet)){
            // s'il y a plus d'un objet de ce type en stock
            if(this.listeObjets.get(objet) > 1){
                quantiteObjet = this.listeObjets.get(objet) - 1;
                this.listeObjets.replace(objet, quantiteObjet);
                return true; // retourne vrai s'il reste un stock de l'objet
            }
            else{
                this.listeObjets.remove(objet);
                return false; // retourne faux si c'était le dernier dans l'inventaire
            }
        }

        // sinon si c'est un objet unique déjà utilisé, on l'enlève de la liste
        else if(objetsUniquesUtilises.contains(objet)){
            objetsUniquesUtilises.remove(objet);
            return false;
        }

        else{
            return false; // retourne faux si l'objet n'était pas présent
        }
    }


    // ranger un objet unique dans la liste des objets utilisés
    public void rangerObjetUtilise(ObjetUnique objetUtilise){
        this.objetsUniquesUtilises.add(objetUtilise);
        if(this.listeObjets.containsKey(objetUtilise)){
            this.listeObjets.remove(objetUtilise);
        }
    }
    

    // laisse le joueur choisir un objet dans son inventaire et l'utiliser
    // retourne l'objet choisi si le joueur souhaite l'utiliser
    // retourne null si l'inventaire est vide ou si le joueur annule
    public Objet menuInventaire(){

        HashMap<String, Objet> choixObjet = new HashMap<String, Objet>();
        int i;
        String choix, action;
        Scanner sc = new Scanner(System.in);
        boolean continuer = true, continuer2 = true;
        Objet objetChoisi = null;

        // objets dans l'inventaire
        if(!this.listeObjets.isEmpty() || !this.objetsUniquesUtilises.isEmpty()){

            System.out.println();
            System.out.println("On dirait que vous ne voyagez pas les poches vides...");

            // boucle pour le choix de l'objet
            while(continuer){

                System.out.println("Voici ce que vous avez amassé :");

                i = 1;

                if(!this.objetsUniquesUtilises.isEmpty()){
                    System.out.println("Déjà utilisés :");
                    for(ObjetUnique objet: this.objetsUniquesUtilises){
                        choixObjet.put(String.valueOf(i), objet); // dictionnaire des choix possibles
                        System.out.println((i++) + " - " + objet.getNom());
                    }
                }

                if(!this.objetsUniquesUtilises.isEmpty() && !this.listeObjets.isEmpty()){
                    System.out.println("------------------------");
                }

                if(!this.listeObjets.isEmpty()){
                    System.out.println("Utilisation possible :");
                    for(HashMap.Entry<Objet, Integer> objet: this.listeObjets.entrySet()){
                        choixObjet.put(String.valueOf(i), objet.getKey()); // dictionnaire des choix possibles
                        System.out.println((i++) + " - " + objet.getKey().getNom() + " (" + objet.getValue() + ")");
                    }
                }

                choixObjet.put(String.valueOf(i), null); // choix Annuler
                System.out.println(i + " - Annuler");

                // saisie du choix
                choix = sc.nextLine();
                if(choixObjet.containsKey(choix)){ // choix valide
                    continuer = false;
                    objetChoisi = choixObjet.get(choix);

                    if(objetChoisi != null){ // l'utilisateur n'a pas choisi Annuler

                        if(this.objetsUniquesUtilises.contains(objetChoisi)){ // si objet unique utilisé, on affiche juste l'effet
                            System.out.println();
                            System.out.println(objetChoisi.getEffet());
                            System.out.println("Vous avez déjà utilisé " + objetChoisi.getArticleDefini() + " " + objetChoisi.getNom());
                            System.out.println("Effet : " + objetChoisi.getValeurAjoutee() + " " + objetChoisi.getAttributTouche());
                            System.out.println();
                            objetChoisi = null; // on ne retourne rien car il ne faut pas réutiliser l'objet
                        }

                        else{
                            // boucle pour le choix de l'action à effectuer
                            while(continuer2){
                                System.out.println();
                                System.out.println(objetChoisi.getUtilisation() + " " + objetChoisi.getArticleDefini() + " " + objetChoisi.getNom() + " ?");
                                System.out.println("1 - Oui");
                                System.out.println("2 - Non");

                                action = sc.nextLine();
                                switch(action){

                                    // utiliser l'objet, on retournera l'objet choisi
                                    case "1": continuer2 = false;
                                    break;

                                    // ne pas utiliser l'objet, on retournera null
                                    case "2":
                                    System.out.println();
                                    System.out.println("À ta guise...");
                                    System.out.println();
                                    objetChoisi = null;
                                    continuer2 = false;
                                    break;
                                }
                            }
                        }
                    }
                }

                System.out.println();
            }
        }

        // inventaire vide, on retournera null
        else{
            System.out.println();
            System.out.println("Vous n'avez rien sur vous. Nada. Nothing. Nichts.");
            System.out.println("...");
            System.out.println("........");
            System.out.println("...............");
            System.out.println("Vous n'êtes pas nu(e) au moins ?!");
            System.out.println();
            objetChoisi = null;
        }

        return objetChoisi;
    }


    public Objet menuDonnerObjet(){

        Objet objetChoisi = null;
        HashMap<String, Objet> choixObjet = new HashMap<String, Objet>();
        int i;
        boolean continuer = true;
        String choix;
        Scanner sc = new Scanner(System.in);

        if(this.listeObjets.isEmpty() && this.objetsUniquesUtilises.isEmpty()){
            System.out.println();
            System.out.println("Qu'est-ce qui vous prend ? Vous n'avez rien à lui donner.");
            objetChoisi = null;
        }

        else{

            while(continuer){

                System.out.println();
                System.out.println("Vous fouillez dans votre inventaire pour trouver ce que vous pourriez lui donner.");

                i = 1;
                for(HashMap.Entry<Objet, Integer> objet: this.listeObjets.entrySet()){
                    choixObjet.put(String.valueOf(i), objet.getKey()); // dictionnaire des choix possibles
                    System.out.println((i++) + " - " + objet.getKey().getNom() + " (" + objet.getValue() + ")");
                }
                for(ObjetUnique objet: this.objetsUniquesUtilises){
                    choixObjet.put(String.valueOf(i), objet);
                    System.out.println((i++) + " - " + objet.getNom() + " (1)");
                }
                choixObjet.put(String.valueOf(i), null); // choix "Annuler"
                System.out.println(i + " - Annuler");

                choix = sc.nextLine();
                if(choixObjet.containsKey(choix)){
                    continuer = false;
                    objetChoisi = choixObjet.get(choix);
                }

            }

        }

        return objetChoisi;
    }
    
}
