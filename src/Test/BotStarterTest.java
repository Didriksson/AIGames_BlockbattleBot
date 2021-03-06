package Test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Optional;

import moves.Move;
import moves.MoveType;

import org.junit.Test;

import bot.BotStarter;
import bot.BotState;
import bot.MoveEvaluator;
import field.Field;
import field.Shape;
import field.ShapeType;

public class BotStarterTest {

    @Test
    public void testLandingHeight() {
	Field field = new Field(10, 20, "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,2,0,0,0,0,0;" + "0,0,0,0,2,0,0,0,0,0;"
		+ "0,0,0,0,2,0,0,0,0,0");
	Shape shape = new Shape(ShapeType.O, field, new Point(3, -1));
	shape.setLocation(4, 15);

	MoveEvaluator eval = new MoveEvaluator();
	int h = eval.getLandingHeight(shape);
	assertEquals(4, h);
    }

    @Test
    public void testWellSums() {
	Field field = new Field(10, 20, "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,2,0,2,0;" + "2,2,2,2,0,2,2,0,2,2;"
		+ "2,2,2,2,0,2,2,0,2,2");

	assertEquals(9, field.getWellSums());
    }

    @Test
    public void testRowsEliminated() {
	Field field = new Field(10, 20, "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "2,2,2,2,0,0,2,2,2,2;"
		+ "2,2,2,2,0,0,2,2,2,2");
	Shape shape = new Shape(ShapeType.O, field, new Point(3, -1));
	shape.setLocation(4, 18);

	MoveEvaluator eval = new MoveEvaluator();
	int h = eval.getRowsEliminated(shape);
	// System.out.println(shape.getFieldWithShape());
	assertEquals(2, h);

    }


    @Test
    public void timedOutTest() {
	BotStarter botStarter = new BotStarter();

	BotState state = new BotState();
	state.updateSettings("timebank", "10000");
	state.updateSettings("time_per_move", "500");
	state.updateSettings("player_names", "player1,player2");
	state.updateSettings("your_bot", "player1");
	state.updateSettings("field_height", "20");
	state.updateSettings("field_width", "10");

	state.updateState("player1", "round", "1");
	state.updateState("player1", "this_piece_type", "J");
	state.updateState("player1", "next_piece_type", "I");
	state.updateState("player1", "this_piece_position", "3,-1");
	state.updateState("player1", "row_points", "0");
	state.updateState("player1", "combo", "0");
	state.updateState("player1", "field", "0,0,0,1,1,1,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,2,2,2,2;"
		+ "0,0,0,0,0,0,0,0,2,0;" + "0,0,0,0,0,0,0,2,2,2;"
		+ "0,0,0,2,2,2,2,2,0,0;" + "2,2,2,2,2,0,0,0,0,0;"
		+ "3,3,3,3,3,3,3,3,3,3;" + "3,3,3,3,3,3,3,3,3,3;"
		+ "3,3,3,3,3,3,3,3,3,3;" + "3,3,3,3,3,3,3,3,3,3;"
		+ "3,3,3,3,3,3,3,3,3,3;" + "3,3,3,3,3,3,3,3,3,3;"
		+ "3,3,3,3,3,3,3,3,3,3;" + "3,3,3,3,3,3,3,3,3,3;"
		+ "3,3,3,3,3,3,3,3,3,3");

	botStarter.getMoves(state, 10000);

    }

    @Test
    public void findPathIntoSemiIsolatedCells() {
	BotStarter botStarter = new BotStarter();

	BotState state = new BotState();
	state.updateSettings("timebank", "10000");
	state.updateSettings("time_per_move", "500");
	state.updateSettings("player_names", "player1,player2");
	state.updateSettings("your_bot", "player1");
	state.updateSettings("field_height", "20");
	state.updateSettings("field_width", "10");

	state.updateState("player1", "round", "1");
	state.updateState("player1", "this_piece_type", "T");
	state.updateState("player1", "next_piece_type", "I");
	state.updateState("player1", "this_piece_position", "3,-1");
	state.updateState("player1", "row_points", "0");
	state.updateState("player1", "combo", "0");
	state.updateState("player1", "field", "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "2,0,0,0,0,0,0,0,0,0;" + "2,2,0,0,0,0,0,0,0,0;"
		+ "2,0,0,0,0,0,0,0,0,0");

	Field field = new Field(10, 20, "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "2,2,2,2,2,2,2,2,2,2;"
		+ "2,0,0,0,0,0,0,0,0,0;" + "2,2,0,0,0,0,0,0,0,0;"
		+ "2,0,0,0,0,0,0,0,0,0");

	Shape shape = new Shape(ShapeType.T, new Field(field), new Point(3, -1));
	shape.setLocation(1, 18);
	System.out.println(shape.getFieldWithShapeAsString());
	System.out.println(shape.getPathToCurrentPosition(new Point(3, -1)));
	ArrayList<MoveType> moves = botStarter.getMoves(state, 10000);
    }

    @Test
    public void match55bb970035ec1d4039cf3ab8() {
	BotStarter botStarter = new BotStarter();

	BotState state = new BotState();
	state.updateSettings("timebank", "10000");
	state.updateSettings("time_per_move", "500");
	state.updateSettings("player_names", "player1,player2");
	state.updateSettings("your_bot", "player1");
	state.updateSettings("field_height", "20");
	state.updateSettings("field_width", "10");

	state.updateState("player1", "round", "1");
	state.updateState("player1", "this_piece_type", "O");
	state.updateState("player1", "next_piece_type", "I");
	state.updateState("player1", "this_piece_position", "3,-1");
	state.updateState("player1", "row_points", "0");
	state.updateState("player1", "combo", "0");
	state.updateState("player1", "field", "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "2,0,0,0,0,0,0,0,0,0;"
		+ "2,2,0,0,0,0,0,0,2,0;" + "2,2,0,0,0,0,0,0,2,2;"
		+ "2,2,2,0,0,0,0,2,2,2;");

	Field field = new Field(10, 20, "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "2,0,0,0,0,0,0,0,0,0;"
		+ "2,2,0,0,0,0,0,0,2,0;" + "2,2,0,0,0,0,0,0,2,2;"
		+ "2,2,2,0,0,0,0,2,2,2;");

	ArrayList<MoveType> moves = botStarter.getMoves(state, 10000);
	System.out.println(moves);

	Shape shape = new Shape(ShapeType.O, new Field(field), new Point(3, -1));

	shape.performMoves(moves);
	System.out.println(shape.getFieldWithShapeAsString());
    }

    @Test
    public void Match55bba90e35ec1d4039cf3bb3() {
	BotStarter botStarter = new BotStarter();

	BotState state = new BotState();
	state.updateSettings("timebank", "10000");
	state.updateSettings("time_per_move", "500");
	state.updateSettings("player_names", "player1,player2");
	state.updateSettings("your_bot", "player1");
	state.updateSettings("field_height", "20");
	state.updateSettings("field_width", "10");

	state.updateState("player1", "round", "1");
	state.updateState("player1", "this_piece_type", "J");
	state.updateState("player1", "next_piece_type", "I");
	state.updateState("player1", "this_piece_position", "3,-1");
	state.updateState("player1", "row_points", "0");
	state.updateState("player1", "combo", "0");
	state.updateState("player1", "field", "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,2,0;"
		+ "0,0,0,0,0,0,0,0,2,2;" + "2,2,0,0,0,0,0,2,2,2;"
		+ "2,2,0,0,0,0,0,0,2,2;");

	Field field = new Field(10, 20, "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,2,0;"
		+ "0,0,0,0,0,0,0,0,2,2;" + "2,2,0,0,0,0,0,2,2,2;"
		+ "2,2,0,0,0,0,0,0,2,2;");

	ArrayList<MoveType> moves = botStarter.getMoves(state, 10000);
	System.out.println(moves);
    }

    @Test
    public void testMatch55b740a11c687b361d5ba85e() {

	BotState state = new BotState();
	state.updateSettings("timebank", "10000");
	state.updateSettings("time_per_move", "500");
	state.updateSettings("player_names", "player1,player2");
	state.updateSettings("your_bot", "player1");
	state.updateSettings("field_height", "20");
	state.updateSettings("field_width", "10");

	state.updateState("player1", "round", "1");
	state.updateState("player1", "this_piece_type", "T");
	state.updateState("player1", "next_piece_type", "I");
	state.updateState("player1", "this_piece_position", "3,-1");
	state.updateState("player1", "row_points", "0");
	state.updateState("player1", "combo", "0");
	state.updateState("player1", "field", "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0");

	Field field = new Field(state.getMyField());
	Shape shape = new Shape(state.getCurrentShape(), field, new Point(
		state.getShapeLocation()));
	// shape.turnRight();
	// shape.turnRight();
	// Point toLocation = shape.getLocation();
	// shape.setLocation(0, 18);
	// ArrayList<MoveType> moves =
	// shape.getPathToCurrentPosition(toLocation).get().moves;
	// System.out.println(moves);

	// for(Shape s : botStarter.getPossiblePositionsForPiece(shape, field))
	// System.out.println(s.getLocation());

	// ArrayList<MoveType> moves = botStarter.getMoves(state, 10000);
	// System.out.println(moves);
	// moves.add(MoveType.TURNRIGHT);
	// moves.add(MoveType.TURNRIGHT);
	// moves.add(MoveType.LEFT);
	// moves.add(MoveType.LEFT);
	// moves.add(MoveType.LEFT);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	// moves.add(MoveType.DOWN);
	//
	// Shape newShape = new Shape(state.getCurrentShape(), new
	// Field(state.getMyField()), new Point(state.getShapeLocation()));
	// newShape.turnRight();
	// // System.out.println(newShape.getLocation());
	// // System.out.println(newShape.getFieldWithShape());
	// newShape.setLocation(7, 17);
	// Move move =
	// newShape.getPathToCurrentPosition(state.getShapeLocation()).get();
	// fm.insertShape(newShape, field);
	// System.out.println(move.moves);
	// System.out.println(field);
    }

    @Test
    public void getMovesTest() {
	BotStarter botStarter = new BotStarter();
	BotState state = new BotState();

	state.updateSettings("timebank", "10000");
	state.updateSettings("time_per_move", "500");
	state.updateSettings("player_names", "player1,player2");
	state.updateSettings("your_bot", "player1");
	state.updateSettings("field_height", "20");
	state.updateSettings("field_width", "10");

	state.updateState("player1", "round", "1");
	state.updateState("player1", "this_piece_type", "O");
	state.updateState("player1", "next_piece_type", "I");
	state.updateState("player1", "this_piece_position", "3,-1");
	state.updateState("player1", "row_points", "0");
	state.updateState("player1", "combo", "0");
	state.updateState("player1", "field", "" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0");

	state.updateState("player1", "round", "2");
	state.updateState("player1", "this_piece_type", "J");
	state.updateState("player1", "this_piece_position", "3,-1");
	state.updateState("player1", "field", "" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "2,2,0,0,0,0,0,0,0,0;"
		+ "2,2,0,0,0,0,0,0,0,0");

	state.updateState("player1", "round", "3");
	state.updateState("player1", "this_piece_type", "J");
	state.updateState("player1", "this_piece_position", "3,-1");
	state.updateState("player1", "field", "" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,2,2,0;" + "0,0,0,0,2,0,2,2,2,0;"
		+ "2,2,0,2,2,2,2,2,2,0");

	ArrayList<MoveType> moves = botStarter.getMoves(state, 10000);
	Shape shape = new Shape(ShapeType.J, state.getMyField(),
		state.getShapeLocation());
	shape.performMoves(moves);
    }

    @Test
    public void ShapeFloatingTest() {
	Field field = new Field(10, 20, "" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0");

	Shape shape = new Shape(ShapeType.O, field, new Point(0, 17));
	assertEquals(true, shape.isFloating());

	shape.setLocation(0, 18);
	assertEquals(false, shape.isFloating());

    }

    @Test
    public void getLocationsForPiece() {
	MoveEvaluator eval = new MoveEvaluator();
	Shape shape;
	ArrayList<Shape> points;
	Field field = new Field(10, 20, "" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0");

	shape = new Shape(ShapeType.I, field, new Point(3, -1));
	shape.turnLeft();

	points = eval.getPossiblePositionsForPiece(shape, field);
	// for(Shape s : points)
	// System.out.println(s.getLocation());

	assertEquals(10, points.size());

	field = new Field(10, 20, "" + "2,2,2,2,2,2,2,2,2,2;"
		+ "2,2,2,2,2,2,2,2,2,2;" + "2,2,2,2,2,2,2,2,2,2;"
		+ "2,2,2,2,2,2,2,2,2,2;" + "2,2,2,2,2,2,2,2,2,2;"
		+ "2,2,2,2,2,2,2,2,2,2;" + "2,2,2,2,2,2,2,2,2,2;"
		+ "2,2,2,2,2,2,2,2,2,2;" + "2,2,2,2,2,2,2,2,2,2;"
		+ "2,2,2,2,2,2,2,2,2,2;" + "2,2,2,2,0,2,2,2,2,2;"
		+ "2,2,2,2,0,2,2,2,2,2;" + "2,2,2,2,0,2,2,2,2,2;"
		+ "2,2,2,2,0,2,2,2,2,2;" + "2,2,2,2,2,2,2,2,2,2;"
		+ "2,2,2,2,2,2,0,2,2,2;" + "2,2,2,2,2,2,0,2,2,2;"
		+ "2,2,2,2,2,2,0,2,2,2;" + "2,2,2,2,2,2,0,2,2,2;"
		+ "2,2,2,2,2,2,2,2,2,2");
	shape = new Shape(ShapeType.I, field, new Point(0, 0));
	shape.turnLeft();
	ArrayList<Shape> list = eval.getPossiblePositionsForPiece(shape, field);
	assertEquals(2, list.size());
    }

    @Test
    public void findPathToPoint() {
	MoveEvaluator eval = new MoveEvaluator();

	Field field;
	field = new Field(10, 20, "" + "2,2,2,2,2,2,2,2,2,2;"
		+ "2,2,2,2,0,2,2,2,2,2;" + "2,2,2,2,0,2,2,2,2,2;"
		+ "2,2,2,2,0,2,2,2,2,2;" + "2,2,2,2,0,2,2,2,2,2;"
		+ "2,2,2,2,0,2,2,2,2,2;" + "2,2,2,2,0,2,2,2,2,2;"
		+ "2,2,2,2,0,2,2,2,2,2;" + "2,2,2,2,0,2,2,2,2,2;"
		+ "2,2,2,2,0,2,2,2,2,2;" + "2,2,2,2,0,2,2,2,2,2;"
		+ "2,2,2,2,0,2,2,2,2,2;" + "2,2,2,2,0,2,2,2,2,2;"
		+ "2,2,2,2,0,2,2,2,2,2;" + "2,2,2,2,2,2,2,2,2,2;"
		+ "2,2,2,2,2,2,2,2,2,2;" + "2,2,2,2,2,2,2,2,2,2;"
		+ "2,2,2,2,2,2,2,2,2,2;" + "2,2,2,2,2,2,2,2,2,2;"
		+ "2,2,2,2,2,2,2,2,2,2");

	Shape shape = new Shape(ShapeType.I, field, new Point(4, -1));
	shape.turnLeft();
	ArrayList<Shape> points = eval.getPossiblePositionsForPiece(shape,
		field);
	Optional<Move> move = eval.getPathToShapesPosition(points.get(0),
		new Point(4, -1));
	assertEquals(false, move.isPresent());

	field = new Field(10, 20, "" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "2,2,2,2,0,2,2,2,2,2;" + "2,2,2,2,0,2,2,2,2,2;"
		+ "2,2,2,2,0,2,2,2,2,2;" + "2,2,2,2,0,2,2,2,2,2;"
		+ "2,2,2,2,0,2,2,2,2,2;" + "2,2,2,2,0,2,2,2,2,2;"
		+ "2,2,2,2,0,2,2,2,2,2;" + "2,2,2,2,0,2,2,2,2,2;"
		+ "2,2,2,2,0,2,2,2,2,2;" + "2,2,2,2,0,2,2,2,2,2;"
		+ "2,2,2,2,2,2,2,2,2,2;" + "2,2,2,2,2,2,2,2,2,2;"
		+ "2,2,2,2,2,2,2,2,2,2");

	shape = new Shape(ShapeType.I, field, new Point(3, -1));
	shape.turnLeft();
	points = eval.getPossiblePositionsForPiece(shape, field);
	move = eval.getPathToShapesPosition(points.get(0), new Point(3, -1));
	assertEquals(true, move.isPresent());

	field = new Field(10, 20, "" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,2,0,0,0,0,0,0,0,0;" + "0,2,2,2,2,2,2,0,0,0;"
		+ "2,2,2,2,2,2,2,2,2,2");

	shape = new Shape(ShapeType.J, field, new Point(4, -1));
	points = eval.getPossiblePositionsForPiece(shape, field);
	move = eval.getPathToShapesPosition(points.get(0), new Point(4, -1));
	assertEquals(true, move.isPresent());

    }

}
