package Personnages;

public class TestPersonnage {

    public static void main(String[] args) {
        
        Sportif s1 = new Sportif("xyz", 20, 40, 5, 3);
        s1.presentation();

        Artiste a1 = new Artiste("toto", 5, 20, 10, 3);
        a1.presentation();


    
    }
    
}
