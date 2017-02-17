package canfield;

import ucb.gui.TopLevel;
import ucb.gui.LayoutSpec;

import java.awt.event.MouseEvent;

/** A top-level GUI for Canfield solitaire.
 *  @author Zixuan Ge
 */
class CanfieldGUI extends TopLevel {

    private static final int leftBoundary = 50;

    /** A new window with given TITLE and displaying GAME. */
    CanfieldGUI(String title, Game game) {
        super(title, true);
        _game = game;
        addMenuButton("Data->New Game", "newgame");
        addMenuButton("Data->Undo", "undo");
        addMenuButton("Data->Quit", "quit");
//        addLabel("Sorry, no graphical interface yet",
//                 new LayoutSpec("y", 0, "x", 0));
//        addButton("Quit", "quit", new LayoutSpec("y", 0, "x", 1));

        _display = new GameDisplay(game);
        add(_display, new LayoutSpec("y", 2, "width", 2));
        _display.setMouseHandler("click", this, "mouseClicked");
        _display.setMouseHandler("release", this, "mouseReleased");
        _display.setMouseHandler("drag", this, "mouseDragged");
        _display.setMouseHandler("press", this, "mousePressed");

        display(true);
    }

    /** Respond to "Quit" button. */
    public void quit(String dummy) {
        System.exit(1);
    }

    /**Respond to "New Game" button. */
    public void newgame(String dummy) {
        _game.deal();
        _display.repaint();
    }

    /** Respond to "Undo" button. */
    public void undo(String dummy) {
        _game.undo();
        _display.repaint();
    }

    /** Action in response to mouse-clicking event EVENT. */
    public synchronized void mouseClicked(MouseEvent event) {
        // FIXME
        System.out.println("clicked");
        int x = event.getX(), y = event.getY();
        if ((x >= 50 && x <= 140) && (y >= 420 && y <= 545)) {
             _game.stockToWaste();
        }
        _display.repaint();
    }


    static boolean wastepile;
    static boolean reservepile;
//    static boolean tableaupiles;
    static boolean foundationpile1;
    static boolean foundationpile2;
    static boolean foundationpile3;
    static boolean foundationpile4;
    static boolean tableaupile1;
    static boolean tableaupile2;
    static boolean tableaupile3;
    static boolean tableaupile4;




    /** Action in response to mouse-pressed event EVENT. */
    public synchronized void mousePressed(MouseEvent event) {
        int x = event.getX(), y = event.getY();
        wastepile = checkInRange(x, y);
        reservepile = checkInReserve(x, y);
//        tableaupiles = checkInTableau(x, y);
        foundationpile1 = checkInFoundation1(x, y);
        foundationpile2 = checkInFoundation2(x, y);
        foundationpile3 = checkInFoundation3(x, y);
        foundationpile4 = checkInFoundation4(x, y);
        tableaupile1 = checkInTableau1(x, y);
        tableaupile2 = checkInTableau2(x, y);
        tableaupile3 = checkInTableau3(x, y);
        tableaupile4 = checkInTableau4(x, y);
        System.out.println("pressed");
        _display.repaint();
    }

//    public boolean checkInRange(MouseEvent event){
//        int x = event.getX(), y = event.getY();
//        boolean result = (x >= 190 && x <= 280) && (y >= 420 && y <= 545);
//        return result;
//    }

    /** check if mouse is in wastepile. */
    public boolean checkInRange(int a, int b){
        boolean result = (a >= 190 && a <= 280) && (b >= 420 && b <= 545);
        return result;
    }

    /** check if mouse is in reservepile. */
    public boolean checkInReserve(int a, int b) {
        boolean result = (a >= 50 && a <= 140) && (b >= 225 && b <= 450);
        return result;
    }

//    /** check if mouse is in tableaupiles. */
//    public boolean checkInTableau(int a, int b) {
//        boolean result =  ((a >= 340 && a <= 430) || (a >= 480 && a <= 570) || (a >= 620 && a <= 710) || (a >= 760) && (a <= 850)) && ((b >= 225 && b <= 650));
//        return result;
//        }

    /** check if mouse if in foundationpile1. */
    public boolean checkInFoundation1(int a, int b) {
        boolean result = (a >= 340 && a <= 430) && (b >= 50 && b <= 175);
        return result;
    }

    /** check if mouse if in foundationpile2. */
    public boolean checkInFoundation2(int a, int b) {
        boolean result = (a >= 480 && a <= 570) && (b >= 50 && b <= 175);
        return result;
    }

    /** check if mouse if in foundationpile3. */
    public boolean checkInFoundation3(int a, int b) {
        boolean result = (a >= 620 && a <= 710) && (b >= 50 && b <= 175);
        return result;
    }

    /** check if mouse if in foundationpile4. */
    public boolean checkInFoundation4(int a, int b) {
        boolean result = (a >= 760 && a <= 850) && (b >= 50 && b <= 175);
        return result;
    }

    /** check if mouse if in tableaunpile1. */
    public boolean checkInTableau1(int a, int b) {
        boolean result = (a >= 340 && a <= 430) && (b >= 225 && b <= 450);
        return result;
    }

    /** check if mouse if in tableaunpile2. */
    public boolean checkInTableau2(int a, int b) {
        boolean result = (a >= 480 && a <= 570) && (b >= 225 && b <= 450);
        return result;
    }

    /** check if mouse if in tableaunpile3. */
    public boolean checkInTableau3(int a, int b) {
        boolean result = (a >= 620 && a <= 710) && (b >= 225 && b <= 450);
        return result;
    }

    /** check if mouse if in tableaunpile4. */
    public boolean checkInTableau4(int a, int b) {
        boolean result = (a >= 760 && a <= 850) && (b >= 225 && b <= 450);
        return result;
    }




    /** Action in response to mouse-released event EVENT. */
    public synchronized void mouseReleased(MouseEvent event) {
        // FIXME
        int x = event.getX(), y = event.getY();
        System.out.println("released");

        /** waste to foundation */
        if (wastepile && (y >= 50 && y <= 175)) {
            for (int i = 1; i <= 4; i += 1) {
                if (x >= 340 + (i - 1) * 140 && x <= 430 + (i - 1) * 140) {
                    _game.wasteToFoundation();
                }
            }
            wastepile = false;
        }

        /** waste to tableau */
        if (wastepile && (y >= 225 && y <= 650)) {
            for (int i = 1; i <= 4; i += 1) {
                if (x >= 340 + (i - 1) * 140 && x <= 430 + (i - 1) * 140) {
                    _game.wasteToTableau(i);
                }
            }
            wastepile = false;
        }

        /** reserve to foundation. */
        if (reservepile && (y >= 50 && y <= 175)) {
            for (int i = 1; i <= 4; i += 1) {
                if (x >= 340 + (i - 1) * 140 && x <= 430 + (i - 1) * 140) {
                    _game.reserveToFoundation();
                }
            }
            reservepile = false;
        }


        /** reserve to tableau. */
        if (reservepile && (y >= 225 && y <= 450)) {
            for (int i = 1; i <= 4; i += 1) {
                if (x >= 340 + (i - 1) * 140 && x <= 430 + (i - 1) * 140) {
                    _game.reserveToTableau(i);
                }
            }
            reservepile = false;
        }

        /** tableau pile1 to foundation. */
        if (tableaupile1 && (y >= 50 && y <= 175)) {
            for (int i = 1; i <= 4; i += 1) {
                if (x >= 340 + (i - 1) * 140 && x <= 430 + (i - 1) * 140) {
                    _game.tableauToFoundation(1);
                }
            }
            tableaupile1 = false;
        }

        /** tableau pile2 to foundation. */
        if (tableaupile2 && (y >= 50 && y <= 175)) {
            for (int i = 1; i <= 4; i += 1) {
                if (x >= 340 + (i - 1) * 140 && x <= 430 + (i - 1) * 140) {
                    _game.tableauToFoundation(2);
                }
            }
            tableaupile2 = false;
        }

        /** tableau pile3 to foundation. */
        if (tableaupile3 && (y >= 50 && y <= 175)) {
            for (int i = 1; i <= 4; i += 1) {
                if (x >= 340 + (i - 1) * 140 && x <= 430 + (i - 1) * 140) {
                    _game.tableauToFoundation(3);
                }
            }
            tableaupile3 = false;
        }

        /** tableau pile4 to foundation. */
        if (tableaupile4 && (y >= 50 && y <= 175)) {
            for (int i = 1; i <= 4; i += 1) {
                if (x >= 340 + (i - 1) * 140 && x <= 430 + (i - 1) * 140) {
                    _game.tableauToFoundation(4);
                }
            }
            tableaupile4 = false;
        }

        /** foundation pile1 to Tableau. */
        if (foundationpile1 && (y >= 225 && y <= 450)) {
            for (int i = 1; i <= 4; i += 1) {
                if (x >= 340 + (i - 1) * 140 && x <= 430 + (i - 1) * 140) {
                    _game.foundationToTableau(1, i);
                }
            }
        }

        /** foundation pile2 to Tableau. */
        if (foundationpile2 && (y >= 225 && y <= 450)) {
            for (int i = 1; i <= 4; i += 1) {
                if (x >= 340 + (i - 1) * 140 && x <= 430 + (i - 1) * 140) {
                    _game.foundationToTableau(2, i);
                }
            }
        }

        /** foundation pile3 to Tableau. */
        if (foundationpile3 && (y >= 225 && y <= 450)) {
            for (int i = 1; i <= 4; i += 1) {
                if (x >= 340 + (i - 1) * 140 && x <= 430 + (i - 1) * 140) {
                    _game.foundationToTableau(3, i);
                }
            }
        }

        /** foundation pile4 to Tableau. */
        if (foundationpile4 && (y >= 225 && y <= 450)) {
            for (int i = 1; i <= 4; i += 1) {
                if (x >= 340 + (i - 1) * 140 && x <= 430 + (i - 1) * 140) {
                    _game.foundationToTableau(4, i);
                }
            }
        }

        /** tableau pile1 to tableau. */
        if (tableaupile1 && (y >= 225 && y <= 650)) {
            for (int i = 1; i <= 4; i += 1) {
                if (x >= 340 + (i - 1) * 140 && x <= 430 + (i - 1) * 140) {
                    _game.tableauToTableau(1, i);
                }
            }
        }

        /** tableau pile2 to tableau. */
        if (tableaupile2 && (y >= 225 && y <= 650)) {
            for (int i = 1; i <= 4; i += 1) {
                if (x >= 340 + (i - 1) * 140 && x <= 430 + (i - 1) * 140) {
                    _game.tableauToTableau(2, i);
                }
            }
        }

        /** tableau pile3 to tableau. */
        if (tableaupile3 && (y >= 225 && y <= 650)) {
            for (int i = 1; i <= 4; i += 1) {
                if (x >= 340 + (i - 1) * 140 && x <= 430 + (i - 1) * 140) {
                    _game.tableauToTableau(3, i);
                }
            }
        }

        /** tableau pile4 to tableau. */
        if (tableaupile4 && (y >= 225 && y <= 650)) {
            for (int i = 1; i <= 4; i += 1) {
                if (x >= 340 + (i - 1) * 140 && x <= 430 + (i - 1) * 140) {
                    _game.tableauToTableau(4, i);
                }
            }
        }


        _display.repaint();
    }

    /** Action in response to mouse-dragging event EVENT. */
    public synchronized void mouseDragged(MouseEvent event) {
        // FIXME
        int x = event.getX(), y = event.getY();
        System.out.println("dragged");
        _display.repaint();  // Not needed if picture does not change.
    }

    /** The board widget. */
    private final GameDisplay _display;

    /** The game I am consulting. */
    private final Game _game;

}
