# Minesweeper

A Java Swing implementation of the game Minesweeper. The game uses a randomly generated minefield and allows the player to uncover tiles and flag suspected mines.

## Features

- 16 × 16 game board
- 40 randomly placed mines
- Numbered tiles showing the number of adjacent mines
- Left-click to uncover tiles
- Right-click to flag and unflag tiles
- Win and loss detection
- Mines are revealed when a mine is uncovered

## Classes

### Minesweeper

Creates a `GameFrame` object and starts the game.

### GameFrame

Creates and initializes the main game window using Java Swing. Creates the `GameComponent`, adds it to the window, and starts the game.

### GameComponent

Controls the main game and handles user input. Responsible for drawing the game and responding to mouse clicks for uncovering and flagging tiles. Also displays the current game status and mine flagging progress.

### Grid

Manages the Minesweeper game board. It creates the grid, randomly places the mines, calculates the number of neighboring mines for each tile, processes player moves, and checks whether the player has won.

### Square

An abstract class that provides the basic properties and functionality shared by all tiles. It keeps track of a tile's position, whether it is uncovered or flagged, and its colors. It also defines methods that the different types of squares must implement.

### MineSquare

Represents a tile containing a mine. It extends `Square` and handles drawing a mine tile when it is uncovered, as well as its flagged and covered states.

### NumberSquare

Represents a tile that does not contain a mine. It stores the number of neighboring mines and displays that number when the tile is uncovered.
