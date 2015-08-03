package moves;

import java.awt.Point;
import java.util.ArrayList;

public class Move {
    public ArrayList<MoveType> moves;
    public double score;
    public Point targetCoordinate;
    
    public Move(){
	 moves = new ArrayList<MoveType>();
    }
   
}
