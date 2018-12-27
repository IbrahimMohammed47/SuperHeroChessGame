package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import graphics.Assets;
import model.game.Cell;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Super extends ActivatablePowerHero {
	


	public Super(Player player, Game game, String name) {
		super(player, game, name,  player==game.getPlayer1()?Assets.p1_super:Assets.p2_super);
	}

	@Override
	public String toString() {
		return "P";
	}
	
	public void move (Direction r) throws WrongTurnException, UnallowedMovementException, OccupiedCellException
	{
		if(this.getOwner() != getGame().getCurrentPlayer())
			throw new WrongTurnException(this);
		Cell temp = new Cell();
		switch(r)
		{
		case RIGHT: temp = getGame().getCellAt(this.getPosI(), (this.getPosJ() + 1) % 6); break;
		case LEFT: temp = getGame().getCellAt(getPosI(), (getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1))); break;
		case UP: temp = getGame().getCellAt((getPosI() - 1 == -1 ? 6 : (getPosI() - 1)), getPosJ()); break;
		case DOWN: temp = getGame().getCellAt((this.getPosI() + 1) % 7, this.getPosJ()); break;
		default: throw new UnallowedMovementException(this, r);
		}
		if(temp.getPiece() == null)
			moveHelper(r);
		else
		{
			if(this.getOwner() == temp.getPiece().getOwner())
			{
				throw new OccupiedCellException(this, r);
			}
			else
			{
				if(temp.getPiece() instanceof Armored)
				{
					if(((Armored) temp.getPiece()).isArmorUp())
					{
						attack(temp.getPiece());
						getGame().switchTurns();
					}
					else
					{
						attack(temp.getPiece());
						moveHelper(r);
					}
				}
				else
				{
					attack(temp.getPiece());
					moveHelper(r);
				}
			}
		}
	}
	@Override
	public void usePower(Direction d, Piece target, Point newPos) throws PowerAlreadyUsedException, InvalidPowerDirectionException, WrongTurnException{   
		
		
		if(getGame().getCurrentPlayer() != this.getOwner())
			throw new WrongTurnException(this);
		if(isPowerUsed()) {
			throw new PowerAlreadyUsedException(this);
		}
		if(this.getPosI()<=6 && this.getPosI()>=0 && this.getPosJ()>=0 && this.getPosJ()<=5){//Checks if the Super piece is in the board or not
			try
			{
				Cell Temp1 = new Cell();
				Cell Temp2 = new Cell();
				switch(d)
				{
				case RIGHT:
					if(this.getPosJ() + 1 <= 5)
						Temp1 = getGame().getCellAt(this.getPosI(), this.getPosJ() + 1);
					if(this.getPosJ() + 2 <= 5)
						Temp2 = getGame().getCellAt(this.getPosI(), this.getPosJ() + 2);
					break;
				case LEFT: 
					if(this.getPosJ() - 1 >= 0)
						Temp1 = getGame().getCellAt(this.getPosI(), this.getPosJ() - 1);
					if(this.getPosJ() - 2 >= 0)
						Temp2 = getGame().getCellAt(this.getPosI(), this.getPosJ() - 2);
					break;
				case UP:   
					if(this.getPosI() - 1 >= 0)
						Temp1 = getGame().getCellAt(this.getPosI() - 1, this.getPosJ());
					if(this.getPosI() - 2 >= 0)
						Temp2 = getGame().getCellAt(this.getPosI() - 2, this.getPosJ());
					break;
				case DOWN: 
					if(this.getPosI() + 1 <= 6)
						Temp1 = getGame().getCellAt(this.getPosI() + 1, this.getPosJ());
					if(this.getPosI() + 2 <= 6)
						Temp2 = getGame().getCellAt(this.getPosI() + 2, this.getPosJ());
					break;
				default: throw new InvalidPowerDirectionException("Invalid Power Direction!", this, d);
				}
				if(Temp1.getPiece() != null){
					if(Temp1.getPiece().getOwner() != this.getOwner())
						attack(Temp1.getPiece());
				}
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
				}
				if(Temp2.getPiece() != null){
					if(Temp2.getPiece().getOwner() != this.getOwner())
						attack(Temp2.getPiece());
				}
				this.setPowerUsed(true);
				getGame().switchTurns();
				
			}
			catch (NullPointerException e)
			{
				this.setPowerUsed(true);
				getGame().switchTurns();
			}
			
		}
		
	}
}
