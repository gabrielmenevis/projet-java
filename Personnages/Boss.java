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
                finDuJeu();
                
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
        System.out.println("Félicitation, Jupiter vous a rendu votre passeport. Ce coquin est lui même bloqué depuis des années. Et avec le temps il a même oublié pourquoi");
        System.out.println("Ainsi il a pris l'habitude depuis quelques années d'empecher certains autres habitants de partir. Cette année c'est tombez sur vous, voyez nous excusez ce désagrément");
        System.out.println("Le pauvre vieux n'a pas trouvé mieux comme occupation que de brutaliser nos clients avec ses énigmes à la noix...Mais comprenez, à cause du fait qu'il est bloqué depuis des années dans ce club med");
        System.out.println("Jupiter est l'un de nos plus ancien client, ainsi nous ne voulons pas lui faire de la peine en l'empechant de saboter le départ des autres... Je suis sur que vous nous comprenez.");
        System.out.println("En tout cas, on peut dire que vous avez réussi une mission impossible. Vous êtes le seul a avoir réussi a tenir bon et ne pas perdre la tête. Profitons de cet instant pour diriger nos pensées vers ceux qui ne sortiront peut-être jamais plus.");
        System.out.println("Vous avez du faire la connaissance de quelques uns d'ailleurs ! Armindo le peintre, Marinette la garagiste, ou encore Nagui le garagiste... ou alors est-il podologue? Je ne suis plus très sur... ");
        System.out.println("Pour vous féliciter d'avoir réussi a vaincre Jupiter, nous vous offrons 3 semaines suplémentaires au club ! Appelez nous et nous vous reserverons une chambre aussitôt.");
        System.out.println();
        System.out.println("Au plaisir de vous revoir,");
        System.out.println();
        System.out.println("Le Staff");
        System.out.println();
        System.out.println("PS : Evitez de passer près du grand chêne en partant...Une branche pourrait vous tomber sur la tête ! haha.");

    }

}
