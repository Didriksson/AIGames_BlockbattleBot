package field;

public class FieldManipulator {

    public void removeFullRows(Field field) {
	for (int i = 0; i < field.getHeight(); i++) {
	    if (field.isRowFull(i))
		field.clearRow(i);
	}
    }

    //Shapes put on the board by reported state. Not necessary in my implementation. As I insert these on the proper spot.
    public void checkIfActiveShapeOnField(Field field) {
	for(Cell cell : field.getRow(0))
	    if(cell.getState() == CellType.SHAPE)
		cell.setState(CellType.EMPTY);
    }

    public void insertShape(Shape shape, Field field) {
	for(Cell cell : shape.getBlocks()){
	    cell.setState(CellType.BLOCK);
	    field.setCell(cell);
	}
	removeFullRows(field);
    }
}
