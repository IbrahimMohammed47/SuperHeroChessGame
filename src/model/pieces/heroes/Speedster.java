package model.pieces.heroes;

import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import graphics.Assets;
import model.game.Cell;
import model.game.Direction;
import model.game.Game;
import model.game.Player;

public class Speedster extends NonActivatablePowerHero {


	
	public Speedster(Player player, Game game, String name) {
		super(player, game, name,  player==game.getPlayer1()?Assets.p1_speedster:Assets.p2_speedster);
	}

	@Override
	public String toString() {
		return "S";
	}
	
	public void move (Direction r) throws WrongTurnException, UnallowedMovementException, OccupiedCellException
	{
		if(this.getOwner() != getGame().getCurrentPlayer())
			throw new WrongTurnException(this);
		Cell temp = new Cell();
		switch(r)
		{
			case UPRIGHT: temp = getGame().getCellAt(getPosI() - 2 == -1 ? 6 : getPosI() - 2 == -2 ? 5 : (getPosI() - 2), (this.getPosJ() + 2) % 6); break; //Done
			case RIGHT: temp = getGame().getCellAt(this.getPosI(), (this.getPosJ() + 2) % 6); break; //Done
			case DOWNRIGHT: temp = getGame().getCellAt((this.getPosI() + 2) % 7, (this.getPosJ() + 2) % 6); break;
			
			case UPLEFT: temp = getGame().getCellAt((getPosI() - 2 == -1 ? 6 : getPosI() - 2 == -2 ? 5 : (getPosI() - 2)), (getPosJ() - 2 == -1 ? 5 : getPosJ() - 2 == -2 ? 4 : (getPosJ() - 2))); break;
			case LEFT: temp = getGame().getCellAt(getPosI(), (getPosJ() - 2 == -1 ? 5 : getPosJ() - 2 == -2 ? 4 : (getPosJ() - 2))); break;
			case DOWNLEFT: temp = getGame().getCellAt((this.getPosI() + 2) % 7, (getPosJ() - 2 == -1 ? 5 : getPosJ() - 2 == -2 ? 4 : (getPosJ() - 2))); break;
			
			case UP: temp = getGame().getCellAt((getPosI() - 2 == -1 ? 6 : getPosI() - 2 == -2 ? 5 : (getPosI() - 2)), getPosJ()); break;
			case DOWN: temp = getGame().getCellAt((this.getPosI() + 2) % 7, this.getPosJ()); break;
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
