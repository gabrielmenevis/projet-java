package Objets;

public class Objet {
    
    private String nom;
    private boolean objet_bonus;
    private boolean objet_malus;
    
    public Objet(String nom, boolean objet_bonus, boolean objet_malus) {
        this.nom = nom;
        this.objet_bonus = objet_bonus;
        this.objet_malus = objet_malus;
    }
    
    public String getNom() {
        return nom;
    }
    
    public boolean objet_bonus() {
        return objet_bonus;
    }
    
    public boolean objet_malus() {
        return objet_malus;
    }
}
    


