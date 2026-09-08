import java.awt.*;

public class MineSquare extends Square
{
    /*
     * Creates a square as a mined square.
     *
     * @param col column number
     * @param row row number
     */
    public MineSquare(int col, int row)
    {
        super(col, row);
        setBackgroundColor(Color.RED);
    }

    //@return -1
    public int getNeighbors()
    {
        return -1;
    }

    //@return true
    public boolean isMine()
    {
        return true;
    }

    /*
     * Represents a mined square at a specific position and size.
     * If uncovered it fills with background color.
     * If covered it fills with covered color.
     * If flagged it marks the square with a red rectangle.
     */
    public void draw(Graphics2D g2, int size, int leftMargin, int topMargin)
    {
        g2.setColor(getBorderColor());

        int x = leftMargin + getCol() * size;
        int y = topMargin + getRow() * size;

        int borderWidth = (int)(size * 0.05);

        g2.fillRect(x, y, size, size);

        if (!isUncovered())
        {
            g2.setColor(getCoveredColor());

            g2.fillRect(
                x + borderWidth,
                y + borderWidth,
                size - 2 * borderWidth,
                size - 2 * borderWidth
            );

            if (isFlagged())
            {
                g2.setColor(getFlagColor());

                int flagSize = size / 3;
                int flagX = x + (size - flagSize) / 2;
                int flagY = y + (size - flagSize) / 2;

                g2.fillRect(flagX, flagY, flagSize, flagSize);
            }
        }
        else
        {
            g2.setColor(getBackgroundColor());

            g2.fillRect(
                x + borderWidth,
                y + borderWidth,
                size - 2 * borderWidth,
                size - 2 * borderWidth
            );
        }
    }
}