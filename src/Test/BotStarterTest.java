package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import bot.BotStarter;
import bot.BotState;

public class BotStarterTest {

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
	state.updateState("player1", "this_piece_type", "Z");
	state.updateState("player1", "next_piece_type", "I");
	state.updateState("player1", "this_piece_position", "3,-1");
	state.updateState("player1", "row_points", "0");
	state.updateState("player1", "combo", "0");
	state.updateState("player1", "field", ""
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,2,0,0,0,0,0;"
		+ "0,0,0,2,2,2,0,0,0,0");

	
//	state.updateState("player1", "round", "2");
//	state.updateState("player1", "this_piece_type", "S");
//	state.updateState("player1", "this_piece_position", "4,-1");
//	state.updateState("player1", "field", "" 
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "2,2,0,0,0,0,0,0,0,0;"
//		+ "2,2,0,0,0,0,0,0,0,0");	
//	
//	state.updateState("player1", "round", "3");
//	state.updateState("player1", "this_piece_type", "O");
//	state.updateState("player1", "this_piece_position", "4,-1");
//	state.updateState("player1", "field", "" 
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "0,0,0,0,0,0,0,0,0,0;"
//		+ "2,2,0,0,0,0,0,0,2,2;"
//		+ "2,2,0,0,0,0,0,2,2,0");	
	
	System.out.println("Test result for moves: " + botStarter.getMoves(state, 10000));
    }

}
