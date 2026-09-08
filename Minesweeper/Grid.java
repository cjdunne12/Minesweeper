import java.awt.*;
import java.util.*;

public class Grid
{
    // Instantiates variables
    private Square[][] grid;
    private int width;
    private int height;
    private int numMines;
    private int topMargin;
    private int leftMargin;
    private int squareSize;
    private int numFlaggedSquares;
    private boolean mineUncovered;

    /*
     * Creates a grid with a width, height, number of mines,
     * top margin, and left margin.
     * All mines start covered.
     *
     * @param w width of grid
     * @param h height of grid
     * @param n number of mines
     * @param top top margin
     * @param left left margin
     */
    public Grid(int w, int h, int n, int top, int left)
    {
        width = w;
        height = h;
        numMines = n;
        topMargin = top;
        leftMargin = left;
        squareSize = 30;
        numFlaggedSquares = 0;
        mineUncovered = false;

        grid = new Square[h][w];

        createGrid();
    }

    public void flag(int c, int r)
    {
        if (!grid[r][c].isUncovered())
        {
            grid[r][c].toggleFlag();

            if (grid[r][c].isFlagged())
            {
                numFlaggedSquares++;
            }
            else
            {
                numFlaggedSquares--;
            }
        }
    }

    public int uncoverSquare(int c, int r)
    {
        Square s = grid[r][c];

        if (s.isFlagged() || s.isUncovered())
        {
            return s.getNeighbors();
        }

        s.uncover();

        if (s.isMine())
        {
            mineUncovered = true;

            for (int row = 0; row < height; row++)
            {
                for (int col = 0; col < width; col++)
                {
                    grid[row][col].uncover();
                }
            }

            return -1;
        }

        return s.getNeighbors();
    }

    /*
     * Creates the grid, adds mines to random placements on the grid,
     * and creates number squares with a label of the neighboring mines.
     */
    public void createGrid()
    {
        Random random = new Random();

        int placed = 0;

        boolean[][] mines = new boolean[height][width];

        while (placed < numMines)
        {
            int r = random.nextInt(height);
            int c = random.nextInt(width);

            if (!mines[r][c])
            {
                mines[r][c] = true;
                placed++;
            }
        }

        for (int r = 0; r < height; r++)
        {
            for (int c = 0; c < width; c++)
            {
                if (mines[r][c])
                {
                    grid[r][c] = new MineSquare(c, r);
                }
                else
                {
                    int count = 0;

                    for (int dr = -1; dr <= 1; dr++)
                    {
                        for (int dc = -1; dc <= 1; dc++)
                        {
                            int nr = r + dr;
                            int nc = c + dc;

                            if (nr >= 0 && nr < height &&
                                nc >= 0 && nc < width &&
                                mines[nr][nc])
                            {
                                count++;
                            }
                        }
                    }

                    grid[r][c] = new NumberSquare(c, r, count);
                }
            }
        }
    }

    /*
     * Takes the pixel location of a mouse click inside the grid
     * to either uncover or flag a square.
     *
     * @param mouseX x position of mouse
     * @param mouseY y position of mouse
     * @param action action to perform
     * @param started whether the game has started
     * @return whether the game has started
     */
    public boolean userMove(int mouseX, int mouseY, String action, boolean started)
    {
        int lc = (mouseX - leftMargin) / squareSize;
        int rc = (mouseY - topMargin) / squareSize;

        if (lc >= 0 && lc < width && rc >= 0 && rc < height)
        {
            if ("flag".equals(action))
            {
                flag(lc, rc);
            }
            else if ("uncover".equals(action))
            {
                if (!started)
                {
                    started = true;
                    createGrid();
                }

                int result = uncoverSquare(lc, rc);

                if (result == -1)
                {
                    flag(lc, rc);
                }
            }
        }

        return started;
    }

    //@return status of winning, true or false
    public boolean hasWon()
    {
        for (int r = 0; r < height; r++)
        {
            for (int c = 0; c < width; c++)
            {
                Square s = grid[r][c];

                if (s.isMine())
                {
                    if (!s.isFlagged())
                    {
                        return false;
                    }
                }
                else if (!s.isUncovered())
                {
                    return false;
                }
            }
        }

        return true;
    }

    /*
     * Draws each square in the grid.
     *
     * @param g2 Graphics2D object used for drawing
     */
    public void draw(Graphics2D g2)
    {
        for (int r = 0; r < height; r++)
        {
            for (int c = 0; c < width; c++)
            {
                grid[r][c].draw(
                    g2,
                    squareSize,
                    leftMargin,
                    topMargin
                );
            }
        }
    }

    //@return numFlaggedSquares
    public int getNumFlaggedSquares()
    {
        return numFlaggedSquares;
    }

    //@return mineUncovered
    public boolean isMineUncovered()
    {
        return mineUncovered;
    }

    //@return numMines
    public int getNumMines()
    {
        return numMines;
    }
}