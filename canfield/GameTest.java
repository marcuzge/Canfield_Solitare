package canfield;

import static org.junit.Assert.*;
import org.junit.Test;

/** Tests of the Game class.
 *  @author Zixuan Ge
 */

public class GameTest {

    /** Example. */
    @Test
    public void testInitialScore() {
        Game g = new Game();
        g.deal();
        assertEquals(5, g.getScore());
    }

    // Tests of undo might go here.
    @Test
    public void testundo() {
        Game g = new Game();
        g.deal();
        g.stockToWaste();
        g.stockToWaste();
        g.stockToWaste();
        assertEquals(4, g._gamestate.size());
        g.undo();
        g.undo();
        g.undo();
        assertEquals(1, g._gamestate.size());

    }

}
