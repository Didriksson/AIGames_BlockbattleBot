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

import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import moves.Move;
import moves.MoveType;
import field.Cell;
import field.Field;
import field.ShapeType;

/**
 * Shape class
 * 
 * Represents the shapes that appear in the field. Some basic methods have
 * already been implemented, but actual move actions, etc. should still be
 * created.
 * 
 * @author Jim van Eeden <jim@starapple.nl>
 */

public class Shape {

    public ShapeType type;
    private Cell[][] shape; // 2-dimensional bounding box: a matrix that
			    // contains the block-cells of the shape
    private Cell[] blocks; // array that contains only the block-cells of the
			   // shape
    private int size;
    private Point location;
    private Field field;
    private int matrixOffsetX;
    private int matrixOffsetY;
    public int rotation = 0;

    public Shape(ShapeType type, Field field, Point location) {
	this.type = type;
	this.field = field;
	this.blocks = new Cell[4];
	this.location = location;

	setShape();
	setBlockLocations();
    }

    public Shape(Shape shape) {
	this.type = shape.type;
	this.field = new Field(shape.field);
	this.blocks = new Cell[4];
	this.location = new Point(shape.getLocation());

	setShape();
	setBlockLocations();

	for (int i = 0; i < shape.rotation; i++) {
	    turnRight();
	}
    }

    public void performMoves(List<MoveType> moves) {
	for (MoveType move : moves) {
	    switch (move) {
	    case LEFT:
		oneLeft();
		break;
	    case RIGHT:
		oneRight();
		break;
	    case TURNLEFT:
		turnLeft();
		break;
	    case TURNRIGHT:
		turnRight();
		break;
	    case DOWN:
		oneDown();
	    default:
		break;
	    }
	}
    }

    public String getFieldWithShape() {
	Field copyOfField = new Field(field);
	for (Cell cell : blocks) {
	    copyOfField.setCell(cell);
	}
	return copyOfField.toString();
    }

    /**
     * Rotates the shape counter-clockwise
     */
    public void turnLeft() {
	rotation--;
	if (rotation < 0)
	    rotation = 4 - rotation;

	Cell[][] temp = this.transposeShape();
	for (int y = 0; y < size; y++) {
	    for (int x = 0; x < size; x++) {
		this.shape[x][y] = temp[x][size - y - 1];
	    }
	}

	this.setBlockLocations();
    }

    /**
     * Rotates the shape clockwise
     */
    public boolean turnRight() {
	rotation = (rotation + 1) % 4;
	Cell[][] temp = this.transposeShape();
	for (int x = 0; x < size; x++) {
	    this.shape[x] = temp[size - x - 1];
	}
	this.setBlockLocations();
	return checkIfAllCellsInboundsAndEmpty();

    }

    public void oneDown() {

	this.location.y++;
	this.setBlockLocations();

    }

    public void oneUp() {
	this.location.y--;
	this.setBlockLocations();
    }

    public void oneRight() {
	this.location.x++;
	this.setBlockLocations();
    }

    public void oneLeft() {
	this.location.x--;
	this.setBlockLocations();
    }

    public boolean checkIfAllCellsInboundsAndEmpty() {
	boolean inboundsAndEmpty = true;
	for (Cell cell : getBlocks()) {
	    if (cell.hasCollision(field) || cell.isOutOfBoundaries(field)) {
		inboundsAndEmpty = false;
		break;
	    }
	}
	return inboundsAndEmpty;
    }

    public boolean isFloating() {
	oneDown();
	boolean floating = checkIfAllCellsInboundsAndEmpty();
	oneUp();
	return floating;

    }

    // Returns the height of the matrix. This is used because we do not want to
    // test cases
    // where the board cant hold a piece. For example in the case where we need
    // the available positions for a piece.
    public int getHeightOfRotationMatrix() {
	return size;
    }

    /**
     * Used for rotations
     * 
     * @return transposed matrix of current shape box
     */
    private Cell[][] transposeShape() {
	Cell[][] temp = new Cell[size][size];
	for (int y = 0; y < size; y++) {
	    for (int x = 0; x < size; x++) {
		temp[y][x] = shape[x][y];
	    }
	}
	return temp;
    }

    /**
     * This function will correct the empty rows that exists due to rotation
     * etc. We want a line piece to always have a location to the top left of
     * the shape e.g.
     */
    // private void setMatrixOffset() {
    // setMatrixOffSetX();
    // setMatrixOffSetY();
    // }
    //
    // private void setMatrixOffSetY() {
    // matrixOffsetY = 0;
    // boolean emptyRow = true;
    // for (int row = 0; row < size && emptyRow; row++) {
    // for (int col = 0; col < size; col++) {
    // if (shape[col][row].isShape()) {
    // emptyRow = false;
    // }
    // }
    // if (emptyRow)
    // matrixOffsetY--;
    //
    // }
    // }

    // private void setMatrixOffSetX() {
    // matrixOffsetX = 0;
    // boolean emptyRow = true;
    // for (int col = 0; col < size && emptyRow; col++) {
    // for (Cell row : shape[col]) {
    // if (row.isShape()) {
    // emptyRow = false;
    // break;
    // }
    // }
    // if (emptyRow)
    // matrixOffsetX--;
    // }
    // }

    /**
     * Uses the shape's current orientation and position to set the actual
     * location of the block-type cells on the field
     */
    private void setBlockLocations() {
	for (int y = 0; y < size; y++) {
	    for (int x = 0; x < size; x++) {
		if (shape[x][y].isShape()) {
		    shape[x][y].setLocation((location.x + x) + matrixOffsetX,
			    (location.y + y) + matrixOffsetY);
		}
	    }
	}
    }

    /**
     * Set shape in square box. Creates new Cells that can be checked against
     * the actual playing field.
     * */
    private void setShape() {
	switch (this.type) {
	case I:
	    this.size = 4;
	    this.shape = initializeShape();
	    this.blocks[0] = this.shape[0][1];
	    this.blocks[1] = this.shape[1][1];
	    this.blocks[2] = this.shape[2][1];
	    this.blocks[3] = this.shape[3][1];
	    break;
	case J:
	    this.size = 3;
	    this.shape = initializeShape();
	    this.blocks[0] = this.shape[0][0];
	    this.blocks[1] = this.shape[0][1];
	    this.blocks[2] = this.shape[1][1];
	    this.blocks[3] = this.shape[2][1];
	    break;
	case L:
	    this.size = 3;
	    this.shape = initializeShape();
	    this.blocks[0] = this.shape[2][0];
	    this.blocks[1] = this.shape[0][1];
	    this.blocks[2] = this.shape[1][1];
	    this.blocks[3] = this.shape[2][1];
	    break;
	case O:
	    this.size = 2;
	    this.shape = initializeShape();
	    this.blocks[0] = this.shape[0][0];
	    this.blocks[1] = this.shape[1][0];
	    this.blocks[2] = this.shape[0][1];
	    this.blocks[3] = this.shape[1][1];
	    break;
	case S:
	    this.size = 3;
	    this.shape = initializeShape();
	    this.blocks[0] = this.shape[1][0];
	    this.blocks[1] = this.shape[2][0];
	    this.blocks[2] = this.shape[0][1];
	    this.blocks[3] = this.shape[1][1];
	    break;
	case T:
	    this.size = 3;
	    this.shape = initializeShape();
	    this.blocks[0] = this.shape[1][0];
	    this.blocks[1] = this.shape[0][1];
	    this.blocks[2] = this.shape[1][1];
	    this.blocks[3] = this.shape[2][1];
	    break;
	case Z:
	    this.size = 3;
	    this.shape = initializeShape();
	    this.blocks[0] = this.shape[0][0];
	    this.blocks[1] = this.shape[1][0];
	    this.blocks[2] = this.shape[1][1];
	    this.blocks[3] = this.shape[2][1];
	    break;
	}

	// set type to SHAPE
	for (int i = 0; i < blocks.length; i++) {
	    this.blocks[i].setShape();
	}
    }

    /**
     * Creates the matrix for the shape
     * 
     * @return
     */
    private Cell[][] initializeShape() {
	Cell[][] newShape = new Cell[size][size];
	for (int y = 0; y < this.size; y++) {
	    for (int x = 0; x < this.size; x++) {
		newShape[x][y] = new Cell();
	    }
	}
	return newShape;
    }

    public void setLocation(int x, int y) {
	this.location = new Point(x, y);
	setBlockLocations();
    }

    public Cell[] getBlocks() {
	return this.blocks;
    }

    public Point getLocation() {
	return this.location;
    }

    public ShapeType getType() {
	return this.type;
    }

    public Optional<Move> getPathToCurrentPosition(Point startPosition) {
	Move move = new Move();
	Point originalPosition = new Point(getLocation());
	boolean inbounds = true;
	boolean triedLeft = false;
	boolean triedRight = false;

	// Adjusting the path up or down.
	while (inbounds) {
	    if (getLocation().y < 0) {
		inbounds = false;
		break;
	    }
	    
	    //So now we know that the path UP was not possible. Let's try it left or right!
	    if (shapeHasCollision()) {
		if(rightAndDownEmpty() && !triedRight)
		{
		    triedRight = true;
		    oneDown();
		    oneRight();
		    move.moves.add(MoveType.LEFT);
		}
		else if(leftAndDownEmpty() && !triedLeft){
		    triedLeft = true;
		    oneDown();
		    oneLeft();
		    move.moves.add(MoveType.RIGHT);
		}
		else{
		    return Optional.empty();
		}
	    }
	    
	    else
	    {
		move.moves.add(MoveType.DOWN);
		oneUp();
	    }
	}

	// Adjusting the path left/right in the beginning to get to the initial
	// position.
	while (getLocation().x != startPosition.x) {
	    if (getLocation().x - startPosition.x > 0) {
		oneLeft();
		move.moves.add(MoveType.RIGHT);
	    } else {
		oneRight();
		move.moves.add(MoveType.LEFT);
	    }
	}

	// Making sure that we rotate into correct position.
	for (int i = 0; i < rotation; i++)
	    move.moves.add(MoveType.TURNRIGHT);

	Collections.reverse(move.moves);

	setLocation(originalPosition.x, originalPosition.y);
	return Optional.of(move);
    }

    private boolean leftAndDownEmpty() {
	oneDown();
	oneLeft();
	boolean empty = !shapeHasCollision();
	oneUp();
	oneRight();
	return empty;
    }

    private boolean rightAndDownEmpty() {
	oneDown();
	oneRight();
	boolean empty = !shapeHasCollision();
	oneUp();
	oneLeft();
	return empty;
    }

    private boolean shapeHasCollision() {
	return Arrays.asList(getBlocks()).parallelStream().filter(e-> e.hasCollision(field)).findAny().isPresent();
    }



    public Field getField() {
	return field;
    }
}
