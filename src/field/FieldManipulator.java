package field;

public class FieldManipulator {

    public void removeFullRows(Field field) {
	for (int i = 0; i < field.getHeight(); i++) {
	    if (field.isRowFull(i))
		field.clearRow(i);
	}
    }

    //Shapes put on the board by reported state. Not necessary in my implementation. As I insert these on the proper spot.
    private void checkIfActiveShapeOnField(Field field) {
	for(Cell cell : field.getRow(0))
	    if(cell.getState() == CellType.SHAPE)
		cell.setState(CellType.EMPTY);
    }

    public void insertShape(Shape shape, Field field) {
	checkIfActiveShapeOnField(field);
	boolean collided = false;
	while (!collided) {
	    for (Cell cell : shape.getBlocks()) {
		if (cell.hasCollision(field)) {
		    collided = true;
		} else if (cell.isOutOfBoundaries(field)) {
		    if (!((int)cell.getLocation().getY() < field.getHeight()))
			collided = true;
		}
	    }
	    if (!collided)
		shape.oneDown();
	}
	// Moving shape until it hits something. We do not want that to happen
	// so move it up again.
	shape.oneUp();
	for(Cell cell : shape.getBlocks()){
	    cell.setState(CellType.BLOCK);
	    field.setCell(cell);
	}
	
	removeFullRows(field);
    }

}
