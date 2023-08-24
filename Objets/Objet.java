package Objets;

public class Objet {
    
    private String nom;
    private boolean estBonus;
    
    public Objet(String nom, boolean estBonus) {
        this.nom = nom;
        this.estBonus = estBonus;
    }
    
    public String getNom() {
        return nom;
    }
    
    public boolean estBonus() {
        return estBonus;
    }
    
    public boolean estMalus() {
        return !estBonus;
    }
}
    

