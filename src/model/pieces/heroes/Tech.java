package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerTargetException;
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

public class Tech extends ActivatablePowerHero {

	
	
	
	public Tech(Player player, Game game, String name) {
		super(player, game, name, player==game.getPlayer1()?Assets.p1_tech:Assets.p2_tech);
	}

	@Override
	public String toString() {
		return "T";
	}
	
	public void move (Direction r) throws WrongTurnException, UnallowedMovementException, OccupiedCellException
	{
		if(this.getOwner() != getGame().getCurrentPlayer())
			throw new WrongTurnException(this);
		Cell temp = new Cell();
		switch(r)
		{
		case UPRIGHT: temp = getGame().getCellAt(getPosI() - 1 == -1 ? 6 : (getPosI() - 1), (this.getPosJ() + 1) % 6); break;
		case DOWNRIGHT: temp = getGame().getCellAt((this.getPosI() + 1) % 7, (this.getPosJ() + 1) % 6); break;		
		case UPLEFT: temp = getGame().getCellAt((getPosI() - 1 == -1 ? 6 : (getPosI() - 1)), (getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1))); break;
		case DOWNLEFT: temp = getGame().getCellAt((this.getPosI() + 1) % 7, (getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1))); break;
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
	public void usePower(Direction d, Piece target, Point newPos) throws PowerAlreadyUsedException, InvalidPowerTargetException, WrongTurnException, OccupiedCellException
	{ 
		if(getGame().getCurrentPlayer() != this.getOwner())
			throw new WrongTurnException(this);
		if(isPowerUsed())
			throw new PowerAlreadyUsedException(this);
		else
		{
			if(target != null && newPos != null)
			{
				if(this.getOwner() != target.getOwner())
					throw new InvalidPowerTargetException(this, target);
				if (getGame().getCellAt(newPos.x, newPos.y).getPiece() == null) //Teleport
				{
					getGame().getCellAt(newPos.x, newPos.y).setPiece(target);
					getGame().getCellAt(target.getPosI(),target.getPosJ()).setPiece(null);
					target.setPosI(newPos.x);
					target.setPosJ(newPos.y);
				}
				else
				{
					throw new InvalidPowerTargetException("This target location is occupied!", this, target);
				}
			}
			else
			{
				if (target.getOwner() == this.getOwner()) //Restore
				{
					if(target instanceof Armored)
					{
						if(((Armored) target).isArmorUp())
						{
							throw new InvalidPowerTargetException("Shield is still UP!", this, target);
						}
						else
						{
							((Armored) target).setArmorUp(true);
								if(target.getOwner()==getGame().getPlayer1()) {
									target.SetImage(Assets.p1_armored);
								}else {
									target.SetImage(Assets.p2_armored);
								}
						}
					}
					else
					{
						if(target instanceof ActivatablePowerHero) 
						{                      
							if(((ActivatablePowerHero) target).isPowerUsed())
								((ActivatablePowerHero) target).setPowerUsed(false);
							else
								throw new InvalidPowerTargetException("The target didn't use its power YET!", this, target);
						}
						else
						{ 
							throw new InvalidPowerTargetException("The target is non activatable power hero!", this, target);
						}
					}
				}
				else
				{                                                                //Hack
					if(target instanceof Armored)
					{
						if(((Armored) target).isArmorUp()) {
							((Armored) target).setArmorUp(false);
							if(target.getOwner()==getGame().getPlayer1()) {
								target.SetImage(Assets.p1_armoredNos);
							}else {
								target.SetImage(Assets.p2_armoredNos);
							}
						}
						else
						{
							throw new InvalidPowerTargetException("Shield is already DOWN!", this, target);
						}
					}
					else
					{
						if(target instanceof ActivatablePowerHero) 
						{                      
							if(((ActivatablePowerHero) target).isPowerUsed())
								throw new InvalidPowerTargetException("The target already used his power", this, target);
							else
								((ActivatablePowerHero) target).setPowerUsed(true);
						}
						else
						{ 
							throw new InvalidPowerTargetException("The target is non activatable power hero!", this, target);
						}
					}
				}
			}
			setPowerUsed(true);
		}
	}
}
