package Responses.Persistence;

import junit.framework.TestCase;
import org.junit.After;

public class GamesTest extends TestCase {

    @After
    public void tearDown() throws Exception {
        Games.clearGames();
    }

    public void testGames() throws Exception {
        Games.games.add(new TicTacToe());
        assert Games.games.size() == 1;
    }

    public void testDeleteLogs() throws Exception {
        Games.games.add(new TicTacToe());
        assert Games.games.size() == 1;
        Games.clearGames();
        assert Games.games.size() == 0;
    }
}