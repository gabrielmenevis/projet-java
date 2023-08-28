package Personnages;
import java.util.Scanner;
import Objets.Objet;
import Salles.Salle;
import java.io.IOException;



public class Boss extends PNJ {
    
    private String mdp;
    private String reponseN;
    private String reponseY;
   

    public Boss (String nom, String mdp,String demande, String reponseN,String reponseY){
        super(nom, "","",demande,0,0,0);
        this.mdp = mdp;
        this.reponseN = reponseN;
        this.reponseY = reponseY;
    }

    public void placerBoss(Salle salle){
    salle.ajouterPNJ(this);
    }

    public String getMdp(){
        return this.mdp;
    }

    public String getReponseN(){
        return this.reponseN;
    }

    public String getReponseY(){
        return this.reponseY;
    }

    public String menuPNJ(){
        Scanner sc = new Scanner(System.in);
        String rep = "";
        System.out.println(this.getTextePNJ());
        System.out.println("1 - Donner sa réponse");
        System.out.println("2 - c'est pour toi !");
        System.out.println("3 - tu veux te battre c'est ça?");
        System.out.println("4 - Annuler");
        rep = sc.nextLine();

        return rep; 
    }
    
    public void parler(){
        Scanner sc = new Scanner(System.in);
        String rep = "";
        System.out.println("votre réponse : ");
        rep = sc.nextLine();  
            if (rep.equals(this.mdp)){
                
                System.out.println(this.reponseY);
                
            }
            else {
                System.out.println(this.reponseN);
            
            }

        
    }    

    public boolean combattre (Personnage perso) throws IOException{
        System.out.println("Non, tu ne te batteras pas contre moi jeune padawan, contente toi de trouver la réponse à ma question...");
        return false;

    }

    public boolean recevoirObjet(Objet o){
        System.out.println("Non, aucun de tes objets ne m'interesse... Par contre je sais que toi tu recherches un objet depuis un moment. Peut-être que répondre à ma question t'aidera dans ta quête.");
        return false;
    }

    public void finDuJeu(){
        System.out.println("Félicitation, jupiter vous a rendu votre passeport");

    }

}
