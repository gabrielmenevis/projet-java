package Objets;

import java.util.HashMap;
import java.util.Scanner;

public class Inventaire {

    private HashMap<Objet, Integer> listeObjets;

    public Inventaire(){
        listeObjets = new HashMap<Objet, Integer>();
    }

    public HashMap<Objet, Integer> getListeObjets(){
        return this.listeObjets;
    }

    public void setListeObjets(HashMap<Objet, Integer> listeObjets){
        this.listeObjets = listeObjets;
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
            ((ObjetUnique) objet).setDejaPris(true); // on retient que l'objet a été pris
        }
    }

    public boolean enleverObjet(Objet objet){
        int quantiteObjet;
        // on s'assure que l'objet est bien présent
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
        else{
            return false; // retourne faux si l'objet n'était pas présent
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
        if(!this.listeObjets.isEmpty()){

            System.out.println();
            System.out.println("On dirait que vous ne voyagez pas les poches vides...");

            // boucle pour le choix de l'objet
            while(continuer){

                System.out.println("Voici ce que vous avez amassé :");

                i = 1;
                for(HashMap.Entry<Objet, Integer> objet: listeObjets.entrySet()){
                    choixObjet.put(String.valueOf(i), objet.getKey()); // dictionnaire des choix possibles
                    System.out.println((i++) + " - " + objet.getKey().getNom() + " (" + objet.getValue() + ")");
                }
                choixObjet.put(String.valueOf(i), null); // choix Annuler
                System.out.println(i + " - Annuler");

                // saisie du choix
                choix = sc.nextLine();
                if(choixObjet.containsKey(choix)){ // choix valide
                    continuer = false;
                    objetChoisi = choixObjet.get(choix);

                    if(objetChoisi != null){ // l'utilisateur n'a pas choisi Annuler

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
    
}
