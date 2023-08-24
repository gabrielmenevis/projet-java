package Personnages;

//import Objets.Objet;
// import Salle;


public abstract class Personnage {

    private String nom;
    private int pv = 0;
    private int max_pv = 0;
    private int p_attaque = 0;
    private int p_charisme = 0;
    private boolean charme;
    //private Inventaire inventaire = 0;
  
    public Personnage(String nom, int pv, int max_pv, int p_attaque, int p_charisme) {
        super();
        this.nom = nom;
        this.pv = pv;
        this.max_pv = max_pv;
        this.p_attaque = p_attaque;
        this.p_charisme = p_charisme;
        this.charme = false;
        //this.inventaire = new Inventaire();
    }

    public String getNom() {
        return nom;
    }

    public int getPV() {
        return pv;
    }

    public int getMaxPV() {
        return max_pv;
    }

    public int getPAttaque() {
        return p_attaque;
    }

    public int getPCharisme() {
        return p_charisme;
    }

<<<<<<< Updated upstream
=======

    public void prendre_objet(Objet objet) {
        System.out.println(this.nom + " trouve un objet : " + objet.getNom());
        
        if (objet.estBonus()) {
            // Si l'objet est un bonus, le personnage gagne un point de vie.
            this.pv++;
            System.out.println(this.nom + " gagne un point de vie !");
        }
        else if (objet.estMalus()) {
            // Si l'objet est un malus, le personnage meurt et est envoyé à l'infirmerie.
            this.pv = 0;
            System.out.println(this.nom + " meurt !");
        }
        else {
            // Si l'objet n'est ni un bonus ni un malus, il peut être ajouté à l'inventaire du personnage.
            //this.inventaire.ajouterObjet(objet);
            System.out.println(this.nom + " ajoute " + objet.getNom() + " à son inventaire.");
        }


    }
>>>>>>> Stashed changes
    // méthode perte de PV à utiliser dans la fonction combats
    public void perdrePV(int degats) {
        this.pv -= degats;
        if (this.pv < 0) this.pv = 0; // Assure que les PV ne tombent pas en dessous de 0
    }

    public boolean isCharme() {
        return charme;
    }

    public void setCharme(boolean charme) {
        this.charme = charme;
    }


    /* //     Pour verifier dans le terminal
    public void presentation() {
        System.out.println("Personnage " + this.nom + ", " + this.pv + " points de vie, " + this.p_attaque + " points d'attaque et " + this.p_charisme + " points de charisme.");
     */
}


