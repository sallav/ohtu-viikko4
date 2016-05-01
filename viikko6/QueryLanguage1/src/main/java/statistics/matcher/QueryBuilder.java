/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

/**
 *
 * @author varkoi
 */
public class QueryBuilder {
    Matcher matcher;
    
    public QueryBuilder(){
        this.matcher = new And();
    }
   
    public Matcher build(){
        return this.matcher;
    }
            
    public QueryBuilder playsIn(String team){
        this.matcher = new And(new PlaysIn(team), matcher);
        return this;
    }        
    
    public QueryBuilder HasAtLeast(int value, String category){
        this.matcher = new And(new HasAtLeast(value, category), matcher);
        return this;
    }
    
    public QueryBuilder HasFewerThan(int value, String category){
        this.matcher = new And(new HasFewerThan(value, category), matcher);
        return this;
    }
    
    public QueryBuilder Not(Matcher... matchers){
        this.matcher = new Not(matchers);
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... matchers){
        this.matcher = new Or(matchers);
        return this;
    }
}
