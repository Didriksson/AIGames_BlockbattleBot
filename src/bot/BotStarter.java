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

import moves.Move;
import moves.MoveType;
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

    /**
     * Returns a random amount of random moves
     * 
     * @param state
     *            : current state of the bot
     * @param timeout
     *            : time to respond
     * @return : a list of moves to execute
     */
    public ArrayList<MoveType> getMoves(BotState state, long timeout) {
	ArrayList<Move> moves = new ArrayList<Move>();

	moves.add(getBestForLeft(state));
	moves.add(getResultForCurrentBoard(state));
	moves.add(getBestMoveForRight(state));
	moves.sort(new Comparator<Move>() {

	    @Override
	    public int compare(Move o1, Move o2) {
		return o1.score < o2.score ? +1 : o1.score > o2.score ? -1 : 0;
	    }
	});

	moves.get(0).moves.add(MoveType.DOWN);
	System.err.println("Move: " + moves.get(0).moves + " for round: " + state.getRound());
	return moves.get(0).moves;
    }

    // Return move for no move;
    private Move getResultForCurrentBoard(BotState state) {
	ArrayList<Move> completeMove = new ArrayList<Move>();
	Move bestMove = new Move();

	ArrayList<MoveType> rotations = getCorrectNumberOfRotations(state
		.getCurrentShape());

	while (true) {
	    Move move = new Move();
	    move.moves = new ArrayList<MoveType>(rotations);
	    completeMove.add(move);
	    if (rotations.size() == 0)
		break;
	    else
		rotations.remove(0);

	}

	for (Move move : completeMove) {
	    move.score = evaluateMove(state, move);
	    if (bestMove.score < move.score)
		bestMove = move;
	}
	return bestMove;
    }

    // Returns best move for the left move option
    private Move getBestForLeft(BotState state) {
	ArrayList<MoveType> moves = new ArrayList<MoveType>();
	ArrayList<Move> completeMove = new ArrayList<Move>();
	Move bestMove = new Move();
	moves.add(MoveType.LEFT);
	moves.add(MoveType.LEFT);
	moves.add(MoveType.LEFT);
	moves.add(MoveType.LEFT);
	moves.add(MoveType.LEFT);

	while (!moves.isEmpty()) {
	    ArrayList<MoveType> rotations = getCorrectNumberOfRotations(state
		    .getCurrentShape());

	    while (true) {
		Move move = new Move();
		move.moves = new ArrayList<MoveType>(moves);
		move.moves.addAll(rotations);
		completeMove.add(move);
		if (rotations.size() == 0)
		    break;
		else
		    rotations.remove(0);
	    }
	    moves.remove(0);
	}

	for (Move move : completeMove) {

	    move.score = evaluateMove(state, move);
	    if (bestMove.score < move.score)
		bestMove = move;
	}
	return bestMove;
    }

    private ArrayList<MoveType> getCorrectNumberOfRotations(ShapeType shape) {

	ArrayList<MoveType> rotations = new ArrayList<MoveType>();

	for (int i = 0; i < numberOfRotations.get(shape); i++) {
	    rotations.add(MoveType.TURNRIGHT);
	}
	return rotations;
    }

    // Returns best move for the right move option.
    private Move getBestMoveForRight(BotState state) {
	ArrayList<MoveType> moves = new ArrayList<MoveType>();

	ArrayList<Move> completeMove = new ArrayList<Move>();
	Move bestMove = new Move();
	moves.add(MoveType.RIGHT);
	moves.add(MoveType.RIGHT);
	moves.add(MoveType.RIGHT);
	moves.add(MoveType.RIGHT);
	moves.add(MoveType.RIGHT);

	while (!moves.isEmpty()) {
	    ArrayList<MoveType> rotations = getCorrectNumberOfRotations(state
		    .getCurrentShape());

	    while (true) {
		Move move = new Move();
		move.moves = new ArrayList<MoveType>(moves);
		move.moves.addAll(rotations);
		completeMove.add(move);
		if (rotations.size() == 0)
		    break;
		else
		    rotations.remove(0);
	    }
	    moves.remove(0);
	}

	for (Move move : completeMove) {
	    move.score = evaluateMove(state, move);
	    if (bestMove.score < move.score)
		bestMove = move;
	}

	return bestMove;
    }

    private int evaluateMove(BotState state, Move move) {
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
}
