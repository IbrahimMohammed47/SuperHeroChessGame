package model.pieces.sidekicks;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import graphics.Assets;
import model.game.Cell;
import model.game.Direction;
import model.game.Game;
import model.pieces.heroes.Armored;

public class SideKickP2 extends SideKick {
	

	public SideKickP2(Game game, String name) {
		super(game.getPlayer2(), game, name, Assets.p2_sidekick);
	}

	public void move (Direction r) throws WrongTurnException, UnallowedMovementException, OccupiedCellException
	{
		if(this.getOwner() != getGame().getCurrentPlayer())
			throw new WrongTurnException(this);
		Cell temp = new Cell();
		switch(r)
		{
			case RIGHT: temp = getGame().getCellAt(this.getPosI(), (this.getPosJ() + 1) % 6); break;
			case DOWNRIGHT: temp = getGame().getCellAt((this.getPosI() + 1) % 7, (this.getPosJ() + 1) % 6); break;
			
			case LEFT: temp = getGame().getCellAt(getPosI(), (getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1))); break;
			case DOWNLEFT: temp = getGame().getCellAt((this.getPosI() + 1) % 7, (getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1))); break;
			
			case DOWN: temp = getGame().getCellAt((this.getPosI() + 1) % 7, this.getPosJ()); break;
			default: throw new UnallowedMovementException(this, r);
		}
		
		if(temp.getPiece() == null)
			moveHelper(r);
		else
		{
			if(this.getOwner() == temp.getPiece().getOwner())
				throw new OccupiedCellException(this, r);
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