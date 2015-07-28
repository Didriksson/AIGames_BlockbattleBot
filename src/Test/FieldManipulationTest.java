package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import field.CellType;
import field.Field;
import field.FieldManipulator;
import field.Shape;
import field.ShapeType;

public class FieldManipulationTest {

    @Test
    public void testEmptyCellsFunction() {
	String inputString = "0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;2,2,2,2,2,2,2,2,2,2";
	Field field = new Field(10, 20, inputString);
	assertEquals(190, field.getNumberOfEmptyCells());

	inputString = "0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0";
	field = new Field(10, 20, inputString);
	assertEquals(200, field.getNumberOfEmptyCells());
    }

    @Test
    public void testIsRowFull() {
	String inputString = "0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,2,2,2,2;2,2,2,2,2,2,2,2,2,2";
	Field field = new Field(10, 20, inputString);
	assertEquals(true, field.isRowFull(19));
    }

    @Test
    public void testOneLeft() {
	FieldManipulator fm = new FieldManipulator();
	String inputString = "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0";

	Field field2 = new Field(10, 20, inputString);
	Shape shape = new Shape(ShapeType.O, field2, new Point(4, -1));
	shape.oneLeft();
	fm.insertShape(shape, field2);
	assertEquals(CellType.BLOCK, field2.getCell(3, 19).getState());
	assertEquals(CellType.BLOCK, field2.getCell(3, 18).getState());
	assertEquals(CellType.BLOCK, field2.getCell(4, 18).getState());
	assertEquals(CellType.BLOCK, field2.getCell(4, 19).getState());
    }

    @Test
    public void testOneRight() {
	FieldManipulator fm = new FieldManipulator();
	String inputString = "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0";
	Field field = new Field(10, 20, inputString);
	Shape shape = new Shape(ShapeType.O, field, new Point(4, -1));
	shape.oneRight();
	fm.insertShape(shape, field);
	assertEquals(CellType.BLOCK, field.getCell(5, 19).getState());
	assertEquals(CellType.BLOCK, field.getCell(5, 18).getState());
	assertEquals(CellType.BLOCK, field.getCell(6, 18).getState());
	assertEquals(CellType.BLOCK, field.getCell(6, 19).getState());

    }

    @Test
    public void testCopyOfField() {
	String inputString = "0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,2,2,2,2;2,2,2,2,2,2,2,2,2,2";
	Field fieldFull = new Field(10, 20, inputString);
	assertEquals(true, fieldFull.isRowFull(19));

	Field fieldNotFull = new Field(fieldFull);
	fieldNotFull.getCell(5, 19).setState(CellType.EMPTY);

	assertEquals(true, fieldFull.isRowFull(19));
	assertEquals(false, fieldNotFull.isRowFull(19));
    }

    @Test
    public void testIsolatedCells() {
	String inputString = "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,2,2,0,0,0,0,0,0;" + "0,0,0,2,0,0,0,0,0,0;"
		+ "0,0,2,2,0,2,2,0,0,0;" + "0,0,2,2,2,2,0,0,0,0";

	Field field = new Field(10, 20, inputString);
	assertEquals(2, field.getNumberOfIsolatedCells());
    }

    @Test
    public void testRemoveFullLines() {
	String inputString = "0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,0,0,0,0;0,0,0,0,0,0,2,2,2,2;2,2,2,2,2,2,2,2,2,2";
	Field field = new Field(10, 20, inputString);
	assertEquals(186, field.getNumberOfEmptyCells());

	FieldManipulator fm = new FieldManipulator();
	fm.removeFullRows(field);
	assertEquals(196, field.getNumberOfEmptyCells());
    }

    @Test
    public void testHeightOfBoard() {
	FieldManipulator fm = new FieldManipulator();
	String inputString = "" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,2,0,0,0,0,0,0,0;"
		+ "0,0,2,2,0,0,0,0,0,0;" + "0,0,2,2,0,0,0,0,0,0;"
		+ "0,0,2,2,2,0,2,0,2,0;" + "0,0,2,2,2,2,2,2,2,0;"
		+ "0,2,2,2,2,2,2,2,2,2";
	Field field = new Field(10, 20, inputString);

	Shape shape = new Shape(ShapeType.I, field, new Point(3, -1));
	shape.turnRight();
	shape.oneLeft();
	shape.oneLeft();
	shape.oneLeft();

	fm.insertShape(shape, field);
	assertEquals(5, field.getHeightOfBoard());
    }

    @Test
    public void testEvaluateBoard() {
	String inputString = "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,2,2,0,0,0,0,0,0;" + "0,2,2,2,2,2,0,0,0,0";
	Field field = new Field(10, 20, inputString);

	int evaluateionScore1 = field.evaluate();

	String inputString2 = "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,2,2,0;" + "0,0,0,2,2,2,2,2,0,0";
	Field field2 = new Field(10, 20, inputString2);
	int evaluateionScore2 = field2.evaluate();

	assertTrue(evaluateionScore1 > evaluateionScore2);

	inputString = "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,2,2,0,0,0,0,0,0;" + "0,2,2,2,2,2,2,2,0,0";
	field = new Field(10, 20, inputString);

	evaluateionScore1 = field.evaluate();

	inputString2 = "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,2,2,0,0,0,0,0,0;"
		+ "0,0,2,2,0,0,0,0,0,0;" + "0,2,2,2,2,2,0,0,0,0";
	field2 = new Field(10, 20, inputString2);
	evaluateionScore2 = field2.evaluate();

	assertTrue(evaluateionScore1 > evaluateionScore2);

    }

    @Test
    public void insertPieceTest() {
	FieldManipulator fm = new FieldManipulator();
	String inputString = "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0";

	Field field = new Field(10, 20, inputString);
	Shape shape = new Shape(ShapeType.I, null, new Point(0, 0));
	shape.turnRight();
	fm.insertShape(shape, field);

	assertEquals("BLOCK", field.getCell(0, 16).getState().toString());
	assertEquals("BLOCK", field.getCell(0, 17).getState().toString());
	assertEquals("BLOCK", field.getCell(0, 18).getState().toString());
	assertEquals("BLOCK", field.getCell(0, 19).getState().toString());

	String inputString2 = "0,0,2,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0";

	Field field2 = new Field(10, 20, inputString2);
	Shape shape2 = new Shape(ShapeType.I, null, new Point(0, 0));
	shape2.turnRight();

	assertEquals("BLOCK", field2.getCell(2, 0).getState().toString());
	assertEquals("EMPTY", field2.getCell(2, 1).getState().toString());
	assertEquals("EMPTY", field2.getCell(2, 2).getState().toString());
	assertEquals("EMPTY", field2.getCell(2, 3).getState().toString());
    }

    @Test
    public void outofboundsPiecesTest() {
	FieldManipulator fm = new FieldManipulator();
	String inputString = "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0";

	Field field = new Field(10, 20, inputString);
	Shape shape = new Shape(ShapeType.L, field, new Point(4, -1));
	shape.turnRight();
	shape.oneLeft();
	shape.oneLeft();
	shape.oneLeft();
	shape.oneLeft();
	shape.oneLeft();
	shape.oneLeft();
	shape.oneLeft();
	shape.oneLeft();

	assertEquals(false, shape.checkIfAllCellsInboundsAndEmpty());

	inputString = "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0";

	field = new Field(10, 20, inputString);
	shape = new Shape(ShapeType.L, field, new Point(4, -1));
	shape.turnRight();
	shape.oneRight();
	shape.oneRight();
	shape.oneRight();
	shape.oneRight();
	shape.oneRight();
	shape.oneRight();
	shape.oneRight();
	shape.oneRight();

	fm.insertShape(shape, field);

	assertEquals(false, shape.checkIfAllCellsInboundsAndEmpty());

    }

    @Test
    public void pushDownActivePieces() {
	FieldManipulator fm = new FieldManipulator();
	String inputString = "0,0,0,1,1,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;" + "0,0,0,0,0,0,0,0,0,0";

	Field field = new Field(10, 20, inputString);
	Shape shape = new Shape(ShapeType.O, field, new Point(4, -1));
	shape.turnRight();
	shape.oneLeft();
	fm.insertShape(shape, field);
	assertEquals("BLOCK", field.getCell(3, 18).getState().toString());
	assertEquals("BLOCK", field.getCell(3, 19).getState().toString());
	assertEquals("BLOCK", field.getCell(4, 18).getState().toString());
	assertEquals("BLOCK", field.getCell(4, 19).getState().toString());

	field = new Field(10, 20, inputString);
	shape = new Shape(ShapeType.O, field, new Point(4, -1));
	shape.turnRight();
	shape.oneLeft();
	fm.insertShape(shape, field);
	assertEquals("BLOCK", field.getCell(3, 18).getState().toString());
	assertEquals("BLOCK", field.getCell(3, 19).getState().toString());
	assertEquals("BLOCK", field.getCell(4, 18).getState().toString());
	assertEquals("BLOCK", field.getCell(4, 19).getState().toString());
    }

}
