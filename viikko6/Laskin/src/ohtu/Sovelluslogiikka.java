package ohtu;

public class Sovelluslogiikka {
 
    private int tulos;
    private int edellinen;

    public Sovelluslogiikka(){
        this.tulos = 0;
    }
    
    public void plus(int luku) {
        edellinen = tulos;
        tulos += luku;
    }
     
    public void miinus(int luku) {
        edellinen = tulos;
        tulos -= luku;
    }
 
    public void nollaa() {
        edellinen = tulos;
        tulos = 0;
    }
 
    public int tulos() {
        return tulos;
    }
    
    public int edellinen() {
        tulos = edellinen;
        return tulos;
    }
}