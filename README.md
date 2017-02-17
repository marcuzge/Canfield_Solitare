# Canfield_Solitare
This is a working program that plays Canfield solitaire (see Rules of Canfield, and augment it in two ways. 
The first problem is to provide an "undo" feature, allowing the player to take back a move. 
The second problem is to do something about the clunky text interface and to provide an alternative spiffy graphical user interface (GUI).

To run the awkward textual interface, use

java -ea canfield.Main --text
(-ea just tells the Java interpreter to check any assert statements you may have added to your code; by default it ignores assertions.)

The command to run with a GUI (currently just a non-working stub) is simply

java -ea canfield.Main
