package Personnages;

import java.util.HashMap;

import Objets.Objet;

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
        int quantiteObjet;
        // l'inventaire contient déjà l'objet, on incrémente la quantité
        if(this.listeObjets.containsKey(objet)){
            quantiteObjet = this.listeObjets.get(objet) + 1;
            this.listeObjets.replace(objet, quantiteObjet);
        }
        // l'inventaire ne contient pas l'objet, la quantité est 1
        else{
            quantiteObjet = 1;
            this.listeObjets.put(objet, quantiteObjet);
        }
    }

    public void menuInventaire(){
        for(HashMap.Entry<Objet, Integer> objet: listeObjets.entrySet()){
            System.out.println("objet " + objet.getKey().getNom() + " / quantité " + objet.getValue());
        }
    }
    
}
