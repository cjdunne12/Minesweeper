import java.awt.*;

public class NumberSquare extends Square
{
    // Instantiates variables
    private int neighbors;
    private Color numberColor;

    /*
     * Creates a numbered square with a column and row number,
     * with the number being blue and the background being light gray.
     *
     * @param col column number
     * @param row row number
     * @param neighbors number of neighboring mines
     */
    public NumberSquare(int col, int row, int neighbors)
    {
        super(col, row);
        this.neighbors = neighbors;
        this.numberColor = Color.BLUE;
        super.setBackgroundColor(Color.LIGHT_GRAY);
    }

    //@return neighbors
    public int getNeighbors()
    {
        return neighbors;
    }

    //@return false
    public boolean isMine()
    {
        return false;
    }

    /*
     * Represents a numbered square at a specific position and size.
     * If uncovered it fills with background color.
     * If covered it fills with covered color.
     * If flagged it is marked with a red rectangle.
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

            if (neighbors > 0)
            {
                g2.setColor(numberColor);

                String text = String.valueOf(neighbors);

                FontMetrics fm = g2.getFontMetrics();

                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();

                int textX = x + (size - textWidth) / 2;
                int textY = y + (size + textHeight) / 2 - fm.getDescent();

                g2.drawString(text, textX, textY);
            }
        }
    }
}