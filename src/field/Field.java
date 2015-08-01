// Copyright 2015 theaigames.com (developers@theaigames.com)

//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at

//        http://www.apache.org/licenses/LICENSE-2.0

//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//	
//    For the full copyright and license information, please view the LICENSE
//    file that was distributed with this source code.

package field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Field class
 * 
 * Represents the playing field for one player. Has some basic methods already
 * implemented.
 * 
 * @author Jim van Eeden <jim@starapple.nl>
 */

public class Field {

    private int width;
    private int height;
    private Cell[][] grid;

    public Field(int width, int height, String fieldString) {
	this.width = width;
	this.height = height;
	parse(fieldString);
    }



    public Field(Field field) {
	this.width = field.width;
	this.height = field.height;
	this.grid = field.getGrid();


    }

    public Cell[] getRow(int row) {
	if (row >= 0 && row < height)
	    return grid[row];
	else
	    return null;
    }

    private Cell[][] getGrid() {
	Cell newGrid[][] = new Cell[this.height][width];
	for (int row = 0; row < this.height; row++) {
	    for (int col = 0; col < this.width; col++) {
		newGrid[row][col] = this.grid[row][col].getCopy();
	    }
	}
	return newGrid;
    }

    /**
     * Parses the input string to get a grid with Cell objects
     * 
     * @param fieldString
     *            : input string
     */
    private void parse(String fieldString) {

	this.grid = new Cell[this.height][this.width];

	// get the separate rows
	String[] rows = fieldString.split(";");
	for (int y = 0; y < this.height; y++) {
	    String[] rowCells = rows[y].split(",");
	    // parse each cell of the row
	    for (int x = 0; x < this.width; x++) {
		int cellCode = Integer.parseInt(rowCells[x]);
		grid[y][x] = new Cell(x, y, CellType.values()[cellCode]);
	    }
	}
    }

    public Cell getCell(int x, int y) {
	if (x < 0 || x >= this.width || y < 0 || y >= this.height)
	    return null;
	return grid[y][x];
    }

    public void setCell(Cell cell) {
	if (!cell.isOutOfBoundaries(this) && cell.getLocation().y >= 0)
	    grid[cell.getLocation().y][cell.getLocation().x] = cell;
    }

    public int getNumberOfIsolatedCellsForRow(int y) {
	int isolatedCells = 0;
	if (y <= 0 || y >= this.height)
	    return 0;

	for (int i = 0; i < getWidth(); i++) {
	    if (getCell(i, y).isEmpty() && !getCell(i, y - 1).isEmpty()) {
		isolatedCells++;
	    }
	}
	return isolatedCells;
    }

    public int getHeight() {
	return this.height;
    }

    public int getWidth() {
	return this.width;
    }

    public boolean isRowFull(int y) {
	if (y < 0 || y >= this.height)
	    return false;

	for (int i = 0; i < getWidth(); i++) {
	    if (getCell(i, y).isEmpty())
		return false;
	}
	return true;
    }

    public void clearRow(int y) {
	if (y >= 0 && y < this.height) {
	    List<Cell[]> listGrid = new ArrayList<Cell[]>(Arrays.asList(grid));
	    listGrid.remove(y);
	    Cell[] emptyTopRow = new Cell[this.width];
	    for (int i = 0; i < width; i++)
		emptyTopRow[i] = new Cell(i, 0, CellType.EMPTY);
	    listGrid.add(0, emptyTopRow);
	    this.grid = listGrid.toArray(grid);
	}

    }

    public int evaluate() {
	
	int emptyCells = getNumberOfEmptyCells() * 10;
	int isolatedCells = getNumberOfIsolatedCells() * 10;
	int semiIsolatedCells = getSemiIsolatedCells() * 10;
	int heigthOfBoard = getHeightOfBoard() * 10;
//	int compactParameterHorizontal = horizontalCompactChecker() * 10;
	int compactParameterVertical = verticalCompactChecker() * 10;
	int totalScoreDeduction = compactParameterVertical + isolatedCells + semiIsolatedCells + heigthOfBoard;
	int finalScore = emptyCells - totalScoreDeduction;

	
//	System.err.println("Emptycells: " + emptyCells + "\nIsolated: "
//		+ isolatedCells + "\nHeight: " + heigthOfBoard
//		+ semiIsolatedCells+ "\nSemi iso: " + semiIsolatedCells
//		+ "\nCompact horizontal: " + compactParameterHorizontal
//		+ "\nCompact vertical: " + compactParameterVertical
//		+ "\nFINAL: " + finalScore);
//
//	System.err.println();

	return finalScore;

    }
    
    public int getSemiIsolatedCells(){
	int sum = 0;
	for (int i = 0; i < getHeight(); i++) {
	    sum += getNumberOfIsolatedCellsForRow(i);
	}
	return sum;
    }

    // This iterates through the board more than necessary however for
    // readability I am keeping the empty rows function - could be baked into
    // this one.
    public int horizontalCompactChecker() {
	int blockChanges = 0;
	for (int row = 0; row < height; row++) {
	    boolean trackingBlock = true;
	    for (int col = 0; col < width; col++) {
		if (grid[row][col].isBlock()) {
		    if (!trackingBlock)
			blockChanges++;
		    trackingBlock = true;
		} else {
		    trackingBlock = false;
		}
	    }
	    if (!trackingBlock)
		blockChanges++;
	}
	return blockChanges - getEmptyRows();
    }

    public int getEmptyRows() {
	int emptyRows = 0;
	boolean foundBlock = false;
	for (int row = 0; row < height; row++) {
	    for (int col = 0; col < width; col++) {
		if (grid[row][col].isBlock()) {
		    foundBlock = true;
		    break;
		}
	    }
	    if (!foundBlock)
		emptyRows++;
	    foundBlock = false;
	}
	return emptyRows;
    }

    // This iterates through the board more than necessary however for
    // readability I am keeping the empty cols function - could be baked into
    // this one.
    public int verticalCompactChecker() {
	int blockChanges = 0;
	for (int col = 0; col < width; col++) {
	    boolean trackingBlock = false;
	    for (int row = 1; row < height - 1; row++) {
		if (grid[row][col].isBlock()) {
		    if (!trackingBlock)
			blockChanges++;
		    trackingBlock = true;
		} else {
		    trackingBlock = false;
		}
	    }

	    if (!trackingBlock)
		blockChanges++;
	}

	return blockChanges - getEmptyCols();
    }

    public int getEmptyCols() {
	int emptyCols = 0;
	boolean foundBlock = false;
	for (int col = 0; col < width; col++) {
	    for (int row = 0; row < height; row++) {
		if (grid[row][col].isBlock()) {
		    foundBlock = true;
		    break;
		}
	    }
	    if (!foundBlock)
		emptyCols++;
	    foundBlock = false;
	}
	return emptyCols;
    }

    public int getHeightOfBoard() {
	for (int i = 0; i < grid.length; i++) {
	    for (Cell cell : getRow(i)) {
		if (cell.getState() != CellType.EMPTY) {
		    return height - i;
		}
	    }
	}
	return 0;
    }

    public int getNumberOfIsolatedCells() {
	int isolatedCells = 0;
	Cell left;
	Cell middle;
	Cell right;
	Cell top;
	for (int row = 1; row < height; row++) {
	    
	    //Not the most elegant solution but the edges of the field is special - these dont need a block on the left hand side to be an isolated cell.
	    	middle = grid[row][0];
		right = grid[row][1];
		top = grid[row-1][0];
		
		if(middle.isEmpty() && !right.isEmpty() && !top.isEmpty())
		    isolatedCells++;
		
	    for (int col = 1; col < width - 1; col++) {
		middle = grid[row][col];
		left = grid[row][col-1];
		right = grid[row][col+1];
		top = grid[row-1][col];
		
		if(middle.isEmpty() && !left.isEmpty() && !right.isEmpty() && !top.isEmpty())
		    isolatedCells++;
	    }
	    
	    	middle = grid[row][width-1];
		left = grid[row][width-2];
		top = grid[row-1][width-1];
		
		if(middle.isEmpty() && !left.isEmpty() && !top.isEmpty())
		    isolatedCells++;
	}
	return isolatedCells;

    }

    public int getNumberOfEmptyCells() {
	int emptyCellsCount = 0;
	for (Cell[] row : grid)
	    emptyCellsCount += Arrays.asList(row).stream()
		    .filter(c -> c.isEmpty()).count();
	return emptyCellsCount;
    }

    public String toString() {
	String output = "      0      1      2      3      4      5      6      7      8      9  \n 0  ";
	int count = 1;
	for (Cell[] row : Arrays.asList(grid)) {
	    for (Cell cell : row) {
		output += cell.getState() + ", ";
	    }
	    if (count < 10)
		output += "\n " + count++ + "  ";
	    else if (count < 20)
		output += "\n " + count++ + " ";

	}

	return output;
    }
    
}
