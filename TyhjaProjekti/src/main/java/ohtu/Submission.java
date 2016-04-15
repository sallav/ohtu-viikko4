package ohtu;

import java.util.ArrayList;
import java.util.List;

public class Submission {
    private String student_number;
    private String student_name;
    private int week;
    private int hours;
    private boolean a1;
    private boolean a2;
    private boolean a3;
    private boolean a4;
    private boolean a5;
    private boolean a6;
    private boolean a7;
    private boolean a8;
    private boolean a9;
    private boolean a10;
    private boolean a11;
    private int tehtmaara; 
    
    public Submission(){
    }

    public boolean isA1() {
        return a1;
    }

    public boolean isA2() {
        return a2;
    }

    public boolean isA3() {
        return a3;
    }

    public boolean isA4() {
        return a4;
    }

    public boolean isA5() {
        return a5;
    }

    public boolean isA6() {
        return a6;
    }

    public boolean isA7() {
        return a7;
    }

    public boolean isA8() {
        return a8;
    }

    public boolean isA9() {
        return a9;
    }

    public boolean isA10() {
        return a10;
    }

    public boolean isA11() {
        return a11;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public void setA1(boolean a1) {
        this.a1 = a1;
    }

    public void setA2(boolean a2) {
        this.a2 = a2;
    }

    public void setA3(boolean a3) {
        this.a3 = a3;
    }

    public void setA4(boolean a4) {
        this.a4 = a4;
    }

    public void setA5(boolean a5) {
        this.a5 = a5;
    }

    public void setA6(boolean a6) {
        this.a6 = a6;
    }

    public void setA7(boolean a7) {
        this.a7 = a7;
    }

    public void setA8(boolean a8) {
        this.a8 = a8;
    }

    public void setA9(boolean a9) {
        this.a9 = a9;
    }

    public void setA10(boolean a10) {
        this.a10 = a10;
    }

    public void setA11(boolean a11) {
        this.a11 = a11;
    }
    
    
    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }
    
    public void setWeek(int week){
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
    
    public String getTehtavat(){
        String teht = "";
        boolean[] tehdyt = {a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11};
        for(int i=0; i<tehdyt.length; i++){
            if(tehdyt[i]==true){
                teht = teht + " " + (i+1);
            }
        }
        return teht;
    }
    
    public int tehtavienMaara(){
        int montako = 0;
        boolean[] tehdyt = {a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11};
        for(int i=0; i<tehdyt.length; i++){
            if(tehdyt[i]==true){
                montako++;
            }
        }
        return montako;
    }

    @Override
    public String toString() {
        return "\n Viikko " + week + ": Tehtyjä tehtäviä yhteensä " + tehtavienMaara() + ", aikaa kului " + hours + " tuntia, tehdyt tehtävät: " + getTehtavat();
    }
    
}