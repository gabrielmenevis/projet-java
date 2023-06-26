public class combat {

    public static void main(String[] args) {
        Combatant C1 = new Combatant("Loulou", 50, 30);
        Combatant A2 = new Combatant("Mauvais ", 100, 23);

        while(C1.estEncoreVivant() && A2.estEncoreVivant()){
            C1.attaquer(A2);
            if (A2.estEncoreVivant()){
                A2.attaquer(C1);
            }
        }
    }
    public void combat(){
    }

}

class Combatant {
    private String nom;
    private int PV;
    private int PA; // Points d'attaque
    private boolean Fuite = false;


    public Combatant(String nom, int pv, int pa) {
        this.nom = nom;
        this.PV = pv;
        this.PA = pa;
    }

    public void attaquer(Combatant adversaire){
        int Degats = this.PA;
        adversaire.PV -= Degats;
        if (adversaire.PV <= 0){
            adversaire.PV = 0; // correction ici : changer PV en adversaire.PV
            System.out.println("L'adversaire " + adversaire.getNom() + " a été vaincu.");
        }
    }

    public String getNom() {
        return nom;
    }

    public boolean estEncoreVivant(){
        if (this.PV > 0){
            return true;
        }
        return false;
    }
}

