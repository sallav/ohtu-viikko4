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
public abstract class KaksiparametrinenOperaatio implements Komento {
    Sovelluslogiikka sovellus;
    JTextField tuloskentta;
    JTextField syotekentta;
    protected int luku1;
    protected int luku2;
    
    public KaksiparametrinenOperaatio(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta){
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }
    
    @Override
    public void suorita(){
        try{
            luku1 = Integer.parseInt(syotekentta.getText());
            laske();
            tuloskentta.setText("" + sovellus.tulos());
            syotekentta.setText("");
        }catch(Exception e){
            System.out.println("Error");
//            tuloskentta.setText("Error");
        }
    }
    
    @Override
    public void peru(){
        tuloskentta.setText("" + sovellus.edellinen());
        syotekentta.setText("");
    }
    
    protected abstract void laske();
}
