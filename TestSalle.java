import java.io.IOException;
import java.util.ArrayList;

import Salles.Salle;

public class TestSalle {

    public static void main(String[] args) throws IOException {

        ArrayList<Salle> listeSalles = new ArrayList<Salle>();
        listeSalles = Chargement.chargerSalles();

        for(Salle s: listeSalles){
            s.descriptionLongue();
            System.out.println("----------------------");
        }
    }
}
