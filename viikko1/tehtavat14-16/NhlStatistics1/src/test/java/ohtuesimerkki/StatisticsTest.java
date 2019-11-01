package ohtuesimerkki;

import java.util.*;
import static org.junit.Assert.assertEquals;

import org.junit.*;

public class StatisticsTest {
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void constructorTest () {
        assertEquals(stats.team("EDM").size(), 3);

    }
    @Test
    public void playerSearchTest1 () {
        assertEquals(stats.search("Kurri").getGoals(), 37);
    }
    @Test
    public void playerSearchTest2 () {
        assertEquals(stats.search("Crosby"), null);
    }
    @Test
    public void getTeamTest () {
        assertEquals(stats.team("EDM").size(), 3);

    }
    @Test
    public void topScorersTest () {
        assertEquals(stats.topScorers(2).size(), 3);

    }


}