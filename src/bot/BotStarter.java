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

package bot;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

import moves.Move;
import moves.MoveType;
import field.Cell;
import field.Field;
import field.FieldManipulator;
import field.Shape;
import field.ShapeType;

/**
 * BotStarter class
 * 
 * This class is where the main logic should be. Implement getMoves() to return
 * something better than random moves.
 * 
 * @author Jim van Eeden <jim@starapple.nl>
 */

public class BotStarter {
    FieldManipulator fm;
    HashMap<ShapeType, Integer> numberOfRotations;

    public BotStarter() {
	fm = new FieldManipulator();
	numberOfRotations = new HashMap<ShapeType, Integer>();
	numberOfRotations.put(ShapeType.I, 1);
	numberOfRotations.put(ShapeType.J, 3);
	numberOfRotations.put(ShapeType.L, 3);
	numberOfRotations.put(ShapeType.O, 0);
	numberOfRotations.put(ShapeType.S, 1);
	numberOfRotations.put(ShapeType.T, 3);
	numberOfRotations.put(ShapeType.Z, 1);

    }

    public ArrayList<MoveType> getMoves(BotState state, long timeout) {
	fm.checkIfActiveShapeOnField(state.getMyField());
	ArrayList<Move> moves = new ArrayList<Move>();
	getAllPossibleMoves(state, moves);

	for (Move m : moves) {
	    m.score = evaluateMove(state, m);
	}

	// System.out.println(s.getLocation() + "\n"+s.getFieldWithShape());

	Shape s = new Shape(ShapeType.T, new Field(state.getMyField()),	new Point(state.getShapeLocation()));
	s.performMoves(moves.get(0).moves);

	moves.sort(new Comparator<Move>() {

	    @Override
	    public int compare(Move o1, Move o2) {
		return o1.score < o2.score ? +1 : o1.score > o2.score ? -1 : 0;
	    }
	});

	System.err
		.println("Target coordinate: " + moves.get(0).targetCoordinate
			+ " Shapetype: " + state.getCurrentShape()
			+ " Current round: " + state.getRound()
			+ " with a score of : " + moves.get(0).score);

	return moves.get(0).moves;
    }

    private void getAllPossibleMoves(BotState state, ArrayList<Move> moves) {
	int rotated = numberOfRotations.get(state.getCurrentShape());
	do {
	    {
		Shape shape = new Shape(state.getCurrentShape(), new Field(
			state.getMyField()),
			new Point(state.getShapeLocation()));
		for (int i = rotated; i < numberOfRotations.get(state
			.getCurrentShape()); i++)
		    shape.turnRight();

		ArrayList<Shape> points = getPossiblePositionsForPiece(shape, new Field(state.getMyField()));
		for (Shape p : points) {
		    Optional<Move> move = getPathToShapesPosition(p,
			    state.getShapeLocation());
		    if (move.isPresent()) {
			move.get().targetCoordinate = new Point(p.getLocation());
			moves.add(move.get());
		    }
		}
		
		points = null;

		rotated--;
	    }
	} while (rotated >= 0);
    }

    private void removeAllPointsAffectingTetrisColumn(ArrayList<Shape> points) {
	ArrayList<Shape> pointsToRemove = new ArrayList<Shape>();
	for(Shape s : points)
	{
	    for(Cell cell : s.getBlocks()){
	    if(cell.isShape() && cell.getLocation().x == 9)
		pointsToRemove.add(s);
	    }
	}
	points.removeAll(pointsToRemove);
    }

    public int evaluateMove(BotState state, Move move) {
	Field field = new Field(state.getMyField());
	Shape shape = new Shape(state.getCurrentShape(), field, new Point(
		state.getShapeLocation()));
	shape.performMoves(move.moves);
	fm.insertShape(shape, field);
	return field.evaluate();
    }

    public static void main(String[] args) {

	BotParser parser = new BotParser(new BotStarter());
	parser.run();
    }

    // Tries the positions available FOR THE CURRENT ROTATION.
    public ArrayList<Shape> getPossiblePositionsForPiece(Shape shape,
	    Field field) {
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	shape = new Shape(shape);
	//Initial is -2 since some shapes will have an empty column if rotated.
	int colStart = -2;
	
	for (int row = field.getHeight() - 1; row > 0
		&& row >= getRandHeight(field, shape); row--) {
	    for (int col = colStart; col <= field.getWidth(); col++) {
		shape.setLocation(col, row);
		if (shape.checkIfAllCellsInboundsAndEmpty()
			&& !shape.isFloating()) {
		    shapes.add(new Shape(shape));
		}
	    }
	}
	return shapes;
    }

    private int getRandHeight(Field field, Shape shape) {
	return field.getHeight()
		- (field.getHeightOfBoard() + shape.getHeightOfRotationMatrix());
    }

    public Optional<Move> getPathToShapesPosition(Shape shape,
	    Point startPosition) {
	return shape.getPathToCurrentPosition(startPosition);
    }
}
