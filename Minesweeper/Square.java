import java.awt.*;

public abstract class Square
{
    // Instantiates variables
    private int col;
    private int row;
    private boolean uncovered;
    private boolean flagged;
    private Color backgroundColor;
    private Color coveredColor;
    private Color borderColor;
    private Color flagColor;

    /*
     * Creates a Square object, with a column and row number,
     * as well as a status of being uncovered or flagged and colors.
     *
     * @param col column number
     * @param row row number
     */
    public Square(int col, int row)
    {
        this.col = col;
        this.row = row;
        this.uncovered = false;
        this.flagged = false;
        this.coveredColor = Color.GREEN;
        this.borderColor = Color.WHITE;
        this.flagColor = Color.RED;
    }

    //@return col
    public int getCol()
    {
        return col;
    }

    //@return row
    public int getRow()
    {
        return row;
    }

    //@return flagged
    public boolean isFlagged()
    {
        return flagged;
    }

    //@return uncovered
    public boolean isUncovered()
    {
        return uncovered;
    }

    //@return backgroundColor
    public Color getBackgroundColor()
    {
        return backgroundColor;
    }

    //@param backgroundColor
    public void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    //@return coveredColor
    public Color getCoveredColor()
    {
        return coveredColor;
    }

    //@param coveredColor
    public void setCoveredColor(Color coveredColor)
    {
        this.coveredColor = coveredColor;
    }

    //@return borderColor
    public Color getBorderColor()
    {
        return borderColor;
    }

    //@param borderColor
    public void setBorderColor(Color borderColor)
    {
        this.borderColor = borderColor;
    }

    //@return flagColor
    public Color getFlagColor()
    {
        return flagColor;
    }

    //@param flagColor
    public void setFlagColor(Color flagColor)
    {
        this.flagColor = flagColor;
    }

    // Swaps boolean status of flagged
    public void toggleFlag()
    {
        this.flagged = !this.flagged;
    }

    // Uncovers if not flagged
    public void uncover()
    {
        if (!flagged)
        {
            this.uncovered = true;
        }
    }

    public abstract int getNeighbors();

    public abstract boolean isMine();

    public abstract void draw(Graphics2D g2, int size, int leftMargin, int topMargin);
}