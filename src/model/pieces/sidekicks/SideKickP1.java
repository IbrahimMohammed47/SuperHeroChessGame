package model.pieces.sidekicks;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import graphics.Assets;
import model.game.Cell;
import model.game.Direction;
import model.game.Game;
import model.pieces.heroes.Armored;

public class SideKickP1 extends SideKick {
	
	public SideKickP1(Game game, String name) {
		super(game.getPlayer1(), game, name, Assets.p1_sidekick);
	}

	public void move (Direction r) throws WrongTurnException , UnallowedMovementException, OccupiedCellException
	{
		if(this.getOwner() != getGame().getCurrentPlayer())
			throw new WrongTurnException(this);
		Cell temp = new Cell();
		switch(r)
		{
		case UPRIGHT: temp = getGame().getCellAt(getPosI() - 1 == -1 ? 6 : (getPosI() - 1), (this.getPosJ() + 1) % 6); break;
		case RIGHT: temp = getGame().getCellAt(this.getPosI(), (this.getPosJ() + 1) % 6); break;		
		
		case UPLEFT: temp = getGame().getCellAt((getPosI() - 1 == -1 ? 6 : (getPosI() - 1)), (getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1))); break;
		case LEFT: temp = getGame().getCellAt(getPosI(), (getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1))); break;
		
		case UP: temp = getGame().getCellAt((getPosI() - 1 == -1 ? 6 : (getPosI() - 1)), getPosJ()); break;
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
}
