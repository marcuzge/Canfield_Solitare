package canfield;

import ucb.gui.Pad;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.imageio.ImageIO;

import java.io.InputStream;
import java.io.IOException;

/** A widget that displays a Pinball playfield.
 *  @author P. N. Hilfinger
 */
class GameDisplay extends Pad {

    /** Color of display field. */
    private static final Color BACKGROUND_COLOR = Color.white;

    /* Coordinates and lengths in pixels unless otherwise stated. */

    /** Preferred dimensions of the playing surface. */
    private static final int BOARD_WIDTH = 900, BOARD_HEIGHT = 650;

    /** Displayed dimensions of a card image. */
    private static final int CARD_HEIGHT = 125, CARD_WIDTH = 90;

    /** A graphical representation of GAME. */
    public GameDisplay(Game game) {
        _game = game;
        setPreferredSize(BOARD_WIDTH, BOARD_HEIGHT);
    }

    /** Return an Image read from the resource named NAME. */
    private Image getImage(String name) {
        InputStream in =
            getClass().getResourceAsStream("/canfield/resources/" + name);
        try {
            return ImageIO.read(in);
        } catch (IOException excp) {
            return null;
        }
    }

    /** Return an Image of CARD. */
    private Image getCardImage(Card card) {
        return getImage("playing-cards/" + card + ".png");
    }

    /** Return an Image of the back of a card. */
    private Image getBackImage() {
        return getImage("playing-cards/blue-back.png");
    }

    /** Draw CARD at X, Y on G. */
    private void paintCard(Graphics2D g, Card card, int x, int y) {
        if (card != null) {
            g.drawImage(getCardImage(card), x, y,
                        CARD_WIDTH, CARD_HEIGHT, null);
        }
    }

    /** Draw card back at X, Y on G. */
    private void paintBack(Graphics2D g, int x, int y) {
        g.drawImage(getBackImage(), x, y, CARD_WIDTH, CARD_HEIGHT, null);
    }

    @Override
    public synchronized void paintComponent(Graphics2D g) {
        g.setColor(BACKGROUND_COLOR);
        Rectangle b = g.getClipBounds();
        g.fillRect(0, 0, b.width, b.height);

        /** draw reserve cards. */
        Card reserve_card = _game.topReserve();
        if (reserve_card == null) {
            /**drawing the reserve pile outline. */
            g.draw3DRect(50, 225, CARD_WIDTH, CARD_HEIGHT, true);
        } else {
            paintCard(g, reserve_card, 50, 225);
        }

        /** draw stock cards. */
        if (!_game.stockEmpty()) {
            g.drawImage(getBackImage(), 50, 420, CARD_WIDTH, CARD_HEIGHT, null);
        } else {
            /**drawing the stock pile outline. */
            g.draw3DRect(50, 420, CARD_WIDTH, CARD_HEIGHT, true);
        }

        /** draw waste cards. */
        if (_game.topWaste() == null) {
            /**drawing the waste pile outline. */
            g.draw3DRect(190, 420, CARD_WIDTH, CARD_HEIGHT, true);
        } else {
            paintCard(g, _game.topWaste(), 190, 420);
        }

        /** draw foundation cards. */
        for (int i = 1; i <= 4; i += 1) {
            Card foundation_card = _game.topFoundation(i);
            if (foundation_card == null) {
                /** drawing the foundation piles outline. */
                g.draw3DRect(340 + 140 * (i - 1), 50, CARD_WIDTH, CARD_HEIGHT, true);
            } else {
                paintCard(g, foundation_card, 340 + 140 * (i - 1), 50);
            }
        }

        int k = 0;
        /** draw tableau cards. */
        for (int i = 1; i <= 4; i += 1) {
            int tableau_size = _game.tableauSize(i);
            if (tableau_size == 0) {
                /**drawing the tableau piles outline. */
                g.draw3DRect(340 + (i - 1) * 140, 225, CARD_WIDTH, CARD_HEIGHT, true);
            } else {
            for (int j = tableau_size - 1; j >= 0; j -= 1) {
                Card tableau_card = _game.getTableau(i, j);
                if (tableau_card != null) {
                    paintCard(g, tableau_card, 340 + (i - 1) * 140, 225 + 25 * k);
                    k += 1;
                    }
                }
                k = 0;
            }
        }






//        paintCard(g, Card.SA, 100, 100);
    }

    /** Game I am displaying. */
    private final Game _game;

}
