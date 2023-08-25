import java.util.Scanner;

public class combat {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Combatant C1 = new Combatant("Loulou", 500, 20);
        Combatant A2 = new Combatant("Mauvais ", 1000, 20);

        while(C1.estEncoreVivant() && A2.estEncoreVivant()){
            System.out.println("PV de l'adversaire : " + A2.getPV() + " PV");
            System.out.println("Que voulez vous faire ? ");
            System.out.println("-1- Attaquer");
            int choix = scanner.nextInt();
            if (choix == 1){
                C1.attaquer(A2);
            }
            if (A2.estEncoreVivant()){
                A2.attaquer(C1);
                System.out.println("Vos PVs : " + C1.getPV());
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
        System.out.println(this.getNom() + " passe à l'attaque !");
        System.out.println(adversaire.getNom() + " perd " + Degats + " PVs.");
        adversaire.PV -= Degats;
        if (adversaire.PV <= 0){
            adversaire.PV = 0; // correction ici : changer PV en adversaire.PV
            System.out.println("L'adversaire " + adversaire.getNom() + " a été vaincu.");
        }
    }

    public String getNom() {
        return this.nom;
    }

    public boolean estEncoreVivant(){
        if (this.PV > 0){
            return true;
        }
        return false;
    }

    public int getPV(){
        return this.PV;
    }
}

