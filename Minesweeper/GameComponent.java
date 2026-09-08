import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class controls the game component and handles mouse input.
 */
public class GameComponent extends JComponent implements MouseListener
{
    private Grid gameGrid;
    private String message;
    private String userProgress;
    private Color textColor;
    private boolean started;
    private boolean gameOver;

    public GameComponent()
    {
        setPreferredSize(new Dimension(800, 800));
        addMouseListener(this);

        gameGrid = new Grid(16, 16, 40, 150, 150);
        message = "Click any tile to start!";
        userProgress = gameGrid.getNumFlaggedSquares() + "/" + gameGrid.getNumMines();
        textColor = Color.WHITE;
        started = false;
        gameOver = false;
    }
    
    public void checkWin()
    {
        if (gameGrid.hasWon())
        {
            gameOver = true;
            message = "You win!";
        }
    }

    /**
     * Paints this component by rendering the game grid and overlaying
     * the current status message and flagged-mine progress.
     *
     * @param g the Graphics context used for drawing
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        gameGrid.draw(g2);

        g2.setColor(textColor);
        g2.drawString(message, 20, 25);
        g2.drawString(userProgress, 20, 45);
    }

    public void start()
    {
        while(true)
        {
            repaint();

            try
            {
                Thread.sleep((long) (1000 / 60.0));
            }
            catch(InterruptedException e) {}
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        String action;

        if (e.getButton() == MouseEvent.BUTTON1)
        {
            action = "uncover";
        }
        else if (e.getButton() == MouseEvent.BUTTON3)
        {
            action = "flag";
        }
        else
        {
            return;
        }

        started = gameGrid.userMove(e.getX(), e.getY(), action, started);

        if (started && !gameOver)
        {
            message = "Find the mines!";
        }

        if (gameGrid.isMineUncovered())
        {
            gameOver = true;
            message = "You lost!";
        }

        checkWin();

        userProgress = gameGrid.getNumFlaggedSquares() + "/" + gameGrid.getNumMines();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}