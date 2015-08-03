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

import java.util.ArrayList;

import moves.Move;
import moves.MoveType;
import field.Cell;
import field.Shape;

/**
 * BotStarter class
 * 
 * This class is where the main logic should be. Implement getMoves() to return
 * something better than random moves.
 * 
 * @author Jim van Eeden <jim@starapple.nl>
 */

public class BotStarter {
    MoveEvaluator evaluator;
    
    public BotStarter() {
	evaluator = new MoveEvaluator();
    }

    public ArrayList<MoveType> getMoves(BotState state, long timeout) {
	
	Move move = evaluator.getBestMove(state);
	return move.moves;
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


    public static void main(String[] args) {

	BotParser parser = new BotParser(new BotStarter());
	parser.run();
    }

 
}
