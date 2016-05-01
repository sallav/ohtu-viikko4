package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
          
        Matcher m = new And( new HasAtLeast(10, "goals"),
                             new HasAtLeast(10, "assists"),
                             new PlaysIn("PHI")
        );
        Matcher m2 = new And(new PlaysIn("PHI"), new Or(new HasFewerThan(10, "goals"), new HasFewerThan(10, "assists")));
        Matcher m3 = new And(new PlaysIn("COL"), new Not(new PlaysIn("PHI")), new Or(new HasFewerThan(6, "goals"), new HasFewerThan(20, "assists")));
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
        System.out.println("....");
        for (Player player : stats.matches(m2)) {
            System.out.println( player );
        }
        System.out.println("....");
        for (Player player : stats.matches(m3)) {
            System.out.println( player );
        }
        
        QueryBuilder query = new QueryBuilder();
        
        Matcher m4  = query.oneOf(new QueryBuilder().HasFewerThan(6, "goals").build(), new QueryBuilder().HasFewerThan(20, "assists").build()).playsIn("COL").build();
        
        System.out.println("....");
        for (Player player : stats.matches(m4)){
            System.out.println(player);
        }
        
        Matcher m5 = query.Not(new QueryBuilder().playsIn("PHI").build()).playsIn("COL").HasAtLeast(4, "goals").build();
        System.out.println("....");
        for (Player player : stats.matches(m5)){
            System.out.println(player);
        }
    }
}
