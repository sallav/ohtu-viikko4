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
public class Erotus extends KaksiparametrinenOperaatio {
        
    public Erotus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta){
        super(sovellus, tuloskentta, syotekentta);
    }
    
    @Override
    public void laske(){
        sovellus.miinus(luku1);
    }
}
