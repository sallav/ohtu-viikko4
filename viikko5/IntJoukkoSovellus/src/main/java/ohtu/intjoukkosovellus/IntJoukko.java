
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] luvut;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        luvut = new int[KAPASITEETTI];
        alusta();
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0)   kapasiteetti = KAPASITEETTI; 
        luvut = new int[kapasiteetti];
        alusta();
        this.kasvatuskoko = OLETUSKASVATUS;
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        luvut = new int[kapasiteetti];
        alusta();
        this.kasvatuskoko = kasvatuskoko;

    }
    
    private void alusta(){
        for (int i = 0; i < luvut.length; i++) {
            luvut[i] = 0;
        }
        alkioidenLkm = 0;
    }

    public boolean lisaa(int luku) {
        if(alkioidenLkm==0 || !onJoukossa(luku)) {
            luvut[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm!=0 && alkioidenLkm % luvut.length == 0) kasvataTaulukko();
            return true;
        }
        return false;
    }
    
    public void kasvataTaulukko(){
        int[] vanhataulukko = luvut;
        luvut = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(vanhataulukko, luvut);
    }

    public boolean onJoukossa(int luku) {
        boolean loytyy = false;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                loytyy = true;
            }
        }
        return loytyy;
    }

    public boolean poista(int luku) {
        int i=0;
        boolean poistettu = false;
        while(i < alkioidenLkm) {
            if(luku == luvut[i])    poistettu = true;
            if (poistettu)  luvut[i] = luvut[i+1];
            i++;
        }
        return pienennaJoukko(poistettu);
        }
    
    public boolean pienennaJoukko(boolean pienenna){
        if(pienenna){
            luvut[alkioidenLkm-1] = 0;
            alkioidenLkm--;
            return true;
        }
        else return false;
}

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        String tuotos = "{";
        if(alkioidenLkm > 1){
            for (int i = 0; i < alkioidenLkm - 1; i++) tuotos += luvut[i] + ", ";
        }if(alkioidenLkm>0) tuotos += luvut[alkioidenLkm - 1];
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = luvut[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko joukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        lisaaJoukkoon(joukko, aTaulu);
        lisaaJoukkoon(joukko, bTaulu);
        return joukko;
    }
    
    public static void lisaaJoukkoon(IntJoukko joukko, int[] taulukko){
        for(int i = 0; i<taulukko.length; i++){
            joukko.lisaa(taulukko[i]);
        }
    }
    
    public static void lisaaSamatJoukkoon(IntJoukko joukko, int[] taulukko, int luku){
        for(int i = 0; i<taulukko.length; i++){
            if(taulukko[i]==luku)   joukko.lisaa(taulukko[i]);
        }
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko joukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) lisaaSamatJoukkoon(joukko, bTaulu, aTaulu[i]);
        return joukko;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko joukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        lisaaJoukkoon(joukko, aTaulu);
        for (int i = 0; i < bTaulu.length; i++) {
            joukko.poista(i);
        }
        return joukko;
    }
        
}