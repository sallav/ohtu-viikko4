/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ohtuesimerkki.*;
import java.util.List;
import java.util.ArrayList;
import junit.framework.Assert;

/**
 *
 * @author Käyttäjä
 */
public class StatisticsTest {
    
    Statistics stats;
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };
    
    public StatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
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
    public void searchTest(){
        String playername = stats.search("Kurri").getName();
        Assert.assertEquals("Kurri", playername);
    }
    
    @Test
    public void searchTest2(){
        Assert.assertNotNull(stats.search("Lemieux"));
    }
    
    @Test
    public void teamTest(){
        Assert.assertNotNull(stats.team("PIT"));
    }
    
    @Test
    public void teamTest2(){
        List<Player> team = stats.team("EDM");
        Assert.assertEquals("Semenko", team.get(0).getName());
        Assert.assertEquals(35, team.get(2).getGoals());
    }
    
    @Test
    public void topScorersTest(){
        List<Player> top = stats.topScorers(3);
        Assert.assertEquals("Gretzky", top.get(0).getName());
    }
    
    @Test
    public void topScorersTest2(){
        List<Player> top = stats.topScorers(3);
        Assert.assertTrue(top.get(0).getPoints()>top.get(2).getPoints());
    }
}
