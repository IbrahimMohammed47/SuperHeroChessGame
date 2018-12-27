package model.pieces;
import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Direction;

public interface Movable
{
		
	public void move (Direction r) throws WrongTurnException, UnallowedMovementException, OccupiedCellException;
	
//	public void moveDownLeft();
//	
//	public void moveDownRight();
//	
//	public void moveUpLeft();
//	
//	public void moveUpRight();
//	
//	public void moveDown();
//	
//	public void moveUp();
//	
//	public void moveLeft();
//	
//	public void moveRight();
}