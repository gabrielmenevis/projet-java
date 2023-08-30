package Personnages;
import java.util.Scanner;
import Objets.Objet;
import Salles.Salle;
import java.io.IOException;


/**
 * Classe qui représente le boss du jeu. Il possède en attribut un mot de passe (que le joueur doit lui
 * donner), une réponse positive et une réponse négative.
 * Surcharge certaines méthodes de PNJ.
 */
public class Boss extends PNJ {
    
    private String mdp;
    private String reponseN;
    private String reponseY;

    /**
     * Constructeur du Boss. Prend moins de paramètres que PNJ dont certains attributs (type, article
     * et statistiques) ne sont pas utilisés par cette classe et initialisés dans PNJ() à la valeur 0 ou "".
     * Prend également un mot de passe, une réponse positive et une réponse négative.
     * @param nom : le nom du boss
     * @param mdp : le mot de passe à donner au boss
     * @param demande : la phrase par laquelle le boss demande le mot de passe
     * @param reponseN : réponse à un mauvais mot de passe
     * @param reponseY : réponse au bon mot de passe
     */
    public Boss (String nom, String mdp,String demande, String reponseN,String reponseY){
        super(nom, "","",demande,0,0,0);
        this.mdp = mdp;
        this.reponseN = reponseN;
        this.reponseY = reponseY;
    }

    /**
     * Ajoute le boss à la liste des PNJ d'une salle
     * @param salle : salle où placer le boss
     */
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

    /**
     * Surcharge la méthode de la classe parente. Les items affichés sont différents.
     * @return la réponse saisie par le joueur ("1" pour donner sa réponse, "2" pour donner un objet,
     * "3" pour se battre, "4" pour annuler)
     */
    public String menuPNJ(){
        Scanner sc = new Scanner(System.in);
        String rep = "";
        System.out.println(this.getTextePNJ());
        System.out.println("1 - Donner sa réponse");
        System.out.println("2 - C'est pour toi !");
        System.out.println("3 - Tu veux te battre c'est ça?");
        System.out.println("4 - Annuler");
        rep = sc.nextLine();

        return rep; 
    }
    
    /**
     * Surcharge la méthode de la classe parente. Permet de vérifier si la réponse saisie par le joueur 
     * correspond ou non au mot de passe attendu.
     * @return true si la réponse correspond au mot de passe, false sinon
     */
    public boolean parler(){
        Scanner sc = new Scanner(System.in);
        String rep = "";
        System.out.println("votre réponse : ");
        rep = sc.nextLine();  
        if (rep.equals(this.mdp)){
            System.out.println(this.reponseY);
            System.out.println();
            return true;
        }
        else {
            System.out.println(this.reponseN);
            return false;
        }
    }    

    /**
     * Surcharge la méthode de la classe parente. Le joueur ne peut pas se battre avec le boss.
     * @return false car aucun combat n'est lancé donc le joueur ne peut pas mourir
     */
    public boolean combattre (Personnage perso) throws IOException{
        System.out.println("Non, tu ne te battras pas contre moi jeune padawan, contente-toi de trouver la réponse à ma question...");
        return false;

    }

    /**
     * Surcharge la méthode de la classe parente. Le joueur ne peut pas offrir un objet au boss.
     * @return false car le boss n'accepte aucun objet
     */
    public boolean recevoirObjet(Objet o){
        System.out.println("Non, aucun de tes objets ne m'intéresse... Par contre je sais que toi tu recherches un objet depuis un moment. Peut-être que répondre à ma question t'aidera dans ta quête.");
        return false;
    }

}
