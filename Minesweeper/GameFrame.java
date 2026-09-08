import javax.swing.*;
import java.awt.*;

/**
 * This is the GameFrame class which extends the JFrame class from Java Swing.
 * This class is used to initialize the window for the game.
 */
public class GameFrame extends JFrame
{
    private final JPanel panel;

    private GameComponent game;

    /**
     * Creates a new GameFrame object, calling the
     * JFrame super class and naming it "Minesweeper".
     * It also creates a new JPanel and initializes the game.
     */
    public GameFrame()
    {
        super("Minesweeper");

        panel = new JPanel();

        game = new GameComponent();
        panel.add(game);
        panel.setBackground(Color.BLACK);
        this.add(panel);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Calls the start method of the GameComponent instance.
     */
    public void run()
    {
        game.start();
    }
}