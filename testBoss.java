import Personnages.Boss;

public class testBoss {
    public static void main(String[] args) {
        Boss b1;
        b1 = new Boss("jupiter", "motdepasse", "Bonjour, quelle est ta réponse?", "Non, dégage", "Bravo, tu as gagné");
        b1.poserQuestion();   
    }
    
}