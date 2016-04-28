/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import javax.swing.JTextField;

/**
 *
 * @author varkoi
 */
public class Nollaa implements Komento {
    Sovelluslogiikka sovellus;
    JTextField tuloskentta;
    JTextField syotekentta;
    int edellinenTulos;
    int edellinenSyote;
    
    public Nollaa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta){
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }
    
    public void suorita(){
        try{
        sovellus.nollaa();
        edellinenTulos = Integer.parseInt(tuloskentta.getText());
        this.tuloskentta.setText("" + 0);
        this.syotekentta.setText("");
        }catch(Exception e){
            System.out.println("Error");
            this.tuloskentta.setText("Error");
        }
    }
    
    public void peru(){
        tuloskentta.setText("" + sovellus.edellinen());
        syotekentta.setText("");
    }
}
