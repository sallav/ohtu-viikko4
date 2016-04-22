/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Tuote;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import ohtu.verkkokauppa.Kauppa;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;
import sun.security.x509.CertificateVersion;

/**
 *
 * @author varkoi
 */
public class KauppaTest {
    
    public KauppaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {

    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void pankinMetodiaTilisiirtoKutsutaanOikein(){
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Varasto varasto = mock(Varasto.class);
        
        when(viite.uusi()).thenReturn(13);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Maito", 5));
        
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("Salla", "66223");
        
        verify(pankki).tilisiirto(eq("Salla"), eq(13), eq("66223"), anyString(), eq(5));
    }
    
    @Test
    public void ostetaanKaksiTuotetta(){
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Varasto varasto = mock(Varasto.class);
        
        when(viite.uusi()).thenReturn(13);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "Juusto", 8));
        
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("Salla", "66223");
        
        verify(pankki).tilisiirto(eq("Salla"), eq(13), eq("66223"), anyString(), eq(13));
    }
    
    @Test
    public void ostetaanKaksiSamaa(){
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Varasto varasto = mock(Varasto.class);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "mandariini", 2));
        
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("Meri", "66889");
        
        verify(pankki).tilisiirto(eq("Meri"), anyInt(), eq("66889"), anyString(), eq(4));
    }
    
    @Test
    public void ostetaanYksiKoskaToinenOnLoppu(){
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Varasto varasto = mock(Varasto.class);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "namit", 4));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "purkka", 3));
        
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("Meri", "66889");
        
        verify(pankki).tilisiirto(eq("Meri"), anyInt(), eq("66889"), anyString(), eq(4));
    }
    
    @Test
    public void aloitaAsiointiNollaa(){
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Varasto varasto = mock(Varasto.class);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "banaani", 3));
        
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("Meri", "66889");
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("Meri", "66889");
        
        verify(pankki).tilisiirto(eq("Meri"), anyInt(), eq("66889"), anyString(), eq(3));
    }
    
    @Test
    public void uusiViiteMaksutapahtumalle(){
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Varasto varasto = mock(Varasto.class);
        
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("Joonas", "33770");
        
        verify(viite, times(1)).uusi();
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("Joonas", "33770");
        
        verify(viite, times(2)).uusi();
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("Joonas", "33770");
        
        verify(viite, times(3)).uusi();
    }
    
    @Test
    public void koristaPoistaminen(){
        Pankki pankki = mock(Pankki.class);
        Viitegeneraattori viite = mock(Viitegeneraattori.class);
        Varasto varasto = mock(Varasto.class);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "Salmiakki", 8));
        
        Kauppa kauppa = new Kauppa(varasto, pankki, viite);
        
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Joonas", "33770");
        
        verify(pankki).tilisiirto(eq("Joonas"), anyInt(), eq("33770"), anyString(), eq(8));
    }
}
