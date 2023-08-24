package Personnages;

    

public class PNJ extends Personnage {
    private String indice;
    
    public PNJ(String nom, String indice) {        
        super(nom, 0, 0, 0, 0);
        this.indice = indice;
    }

    public String getIndice() {
        return indice;
    }

    public void presentation() {
        System.out.println("");
        
    }
    
}
