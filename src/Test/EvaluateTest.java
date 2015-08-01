package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.ArrayList;

import moves.MoveType;

import org.junit.Test;

import bot.BotStarter;
import bot.BotState;
import field.Field;
import field.FieldManipulator;
import field.Shape;
import field.ShapeType;

public class EvaluateTest {

    /**
     * Check this game! Could be good for evaluation testing.
     * http://theaigames.com/competitions/ai-block-battle/games/55bc8c9935ec1d4039cf46a2
     */

    
    @Test
    public void Match55bc8c9935ec1d4039cf46a2(){
	Field bad = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "2,0,2,2,0,0,0,0,0,2;"
		+ "2,2,2,0,2,2,2,2,0,2;"
		+ "2,2,2,2,2,2,2,2,2,2");
	
	Field good = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "2,0,0,0,0,0,0,0,0,2;"
		+ "2,2,0,0,2,2,2,2,0,2;"
		+ "2,2,0,2,2,2,2,2,2,2");
	
	assertTrue(good.evaluate() > bad.evaluate());
	
	
    }
    
    
    
    @Test
    public void Match55b8acaa35ec1d4039cf161c(){
	Field bad = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "2,2,2,2,0,0,0,0,0,0;"
		+ "2,2,0,2,2,0,0,0,0,0");
	
	Field good = new Field(10, 20, 
				  "0,0,0,0,0,0,0,0,0,0;"
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
				+ "0,0,0,2,0,0,0,0,0,0;"
				+ "2,2,2,2,0,0,0,0,0,0;"
				+ "2,2,2,0,0,0,0,0,0,0");
	
	assertTrue(good.evaluate() > bad.evaluate());
	
	
    }
    
    @Test
    public void Match55bb8dfa1c687b361d5bd096(){
	Field bad = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "2,2,0,0,0,0,0,0,0,0;"
		+ "0,2,2,0,0,0,0,0,0,0");
	
	Field good = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "0,0,0,0,0,0,0,2,2,0;"
		+ "0,0,0,0,0,0,0,0,2,2");

	assertTrue(good.evaluate() > bad.evaluate());

    }
    
    @Test
    public void Match55b8b17935ec1d4039cf1658(){
	Field bad = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "2,0,0,0,0,0,0,0,0,0;"
		+ "2,2,0,0,0,0,0,0,0,0;"
		+ "2,0,0,0,0,0,0,0,0,0");
	
	
	Field good = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "0,2,0,0,0,0,0,0,0,0;"
		+ "2,2,2,0,0,0,0,0,0,0");
	assertTrue(good.evaluate() > bad.evaluate());

    }
    
    
    @Test
    public void Match55bb912e35ec1d4039cf3a6a(){
	Field bad = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "2,0,0,0,0,0,0,0,0,0;"
		+ "2,0,0,0,0,0,0,0,0,0;"
		+ "2,0,0,0,0,0,0,0,0,0;"
		+ "2,2,0,0,0,0,0,0,0,0;"
		+ "2,2,2,0,0,0,0,0,0,0");
	
	
	Field good = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "0,2,0,0,0,0,0,0,0,0;"
		+ "2,2,2,2,2,2,2,0,0,0");
	
	System.err.println("Good:");
	good.evaluate();

	
	System.err.println("Bad:");
	bad.evaluate();
	
	
	assertTrue(good.evaluate() > bad.evaluate());

	
	
	
    }
    @Test
    public void Match55b88c8b35ec1d4039cf14a8(){
	Field bad = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "0,0,0,0,0,0,0,2,0,0;"
		+ "0,0,0,0,0,0,0,2,2,2");
	
	Field good = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "2,0,0,0,0,0,0,0,0,0;"
		+ "2,2,2,0,0,0,0,0,0,0");
	assertTrue(good.evaluate() > bad.evaluate());
	
    }
    
    @Test
    public void horizontalCompactCheckerTest() {
	Field field = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "2,2,2,2,2,0,0,0,0,0");
	
	Field swissField = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "0,0,0,2,2,2,2,0,0,0;"
		+ "0,0,0,0,0,2,2,2,0,0;"
		+ "2,2,2,2,2,2,0,2,2,2");
	
//	System.out.println(field.verticalCompactChecker());
	assertEquals(true, field.verticalCompactChecker() < swissField.verticalCompactChecker());
    }
    
    
    @Test
    public void verticalCheckerTest() {
	Field field = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "0,0,0,2,2,0,0,0,0,0;"
		+ "0,0,0,2,0,0,0,0,0,0;"
		+ "0,0,0,2,0,0,0,0,0,0;"
		+ "0,0,0,2,2,0,0,0,0,0;"
		+ "2,2,2,2,2,0,0,0,0,0");
	
	Field swissField = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "0,0,0,0,0,0,0,0,0,2;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,0,0,0,0,0,0,0;"
		+ "0,0,0,2,2,0,2,0,0,2;"
		+ "0,0,0,2,2,2,2,2,0,0;"
		+ "2,2,0,2,2,0,2,2,0,2");
	
	assertEquals(true, field.verticalCompactChecker() < swissField.verticalCompactChecker());

    }
    
    
    @Test
    public void evaluationTest() {
	Field bad = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "0,0,2,0,0,0,2,2,0,0;"
		+ "2,2,2,0,0,2,2,0,0,0");

	
	Field good = new Field(10, 20, 
		  "0,0,0,0,0,0,0,0,0,0;"
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
		+ "0,2,2,0,0,0,0,0,0,0;"
		+ "2,2,2,0,0,0,0,0,0,0;"
		+ "2,2,2,0,0,0,0,0,0,0");
	

	assertEquals(true, good.evaluate() > bad.evaluate());
	
    }
    
  


}
