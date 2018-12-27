package model.pieces.heroes;

import exceptions.OccupiedCellException;
import exceptions.WrongTurnException;
import graphics.Assets;
import model.game.Cell;
import model.game.Direction;
import model.game.Game;
import model.game.Player;

public class Armored extends NonActivatablePowerHero {


	private boolean armorUp;

	public Armored(Player player, Game game, String name) {
		super(player, game, name,  player==game.getPlayer1()?Assets.p1_armored:Assets.p2_armored);
		this.armorUp = true;
	}

	public boolean isArmorUp() {
		return armorUp;
	}

	public void setArmorUp(boolean armorUp) {
		this.armorUp = armorUp;
	}

	@Override
	public String toString() {
		return "A";
	}
	
	public void move (Direction r) throws WrongTurnException, OccupiedCellException
	{
		if(this.getOwner() != getGame().getCurrentPlayer())
			throw new WrongTurnException(this);
		Cell temp = new Cell();
		switch(r)
		{
		case UPRIGHT: temp = getGame().getCellAt(getPosI() - 1 == -1 ? 6 : (getPosI() - 1), (this.getPosJ() + 1) % 6); break;
		case RIGHT: temp = getGame().getCellAt(this.getPosI(), (this.getPosJ() + 1) % 6); break;
		case DOWNRIGHT: temp = getGame().getCellAt((this.getPosI() + 1) % 7, (this.getPosJ() + 1) % 6); break;
		
		case UPLEFT: temp = getGame().getCellAt((getPosI() - 1 == -1 ? 6 : (getPosI() - 1)), (getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1))); break;
		case LEFT: temp = getGame().getCellAt(getPosI(), (getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1))); break;
		case DOWNLEFT: temp = getGame().getCellAt((this.getPosI() + 1) % 7, (getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1))); break;
		
		case UP: temp = getGame().getCellAt((getPosI() - 1 == -1 ? 6 : (getPosI() - 1)), getPosJ()); break;
		case DOWN: temp = getGame().getCellAt((this.getPosI() + 1) % 7, this.getPosJ()); break;
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
