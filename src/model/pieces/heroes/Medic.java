package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
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

public class Medic extends ActivatablePowerHero {


	
	public Medic(Player player, Game game, String name ) {
		super(player, game, name,  player==game.getPlayer1()?Assets.p1_medic:Assets.p2_medic);
	}

	@Override
	public String toString() {
		return "M";
	}

	public void move(Direction r) throws WrongTurnException, UnallowedMovementException, OccupiedCellException
	{
		if(this.getOwner() != getGame().getCurrentPlayer())
			throw new WrongTurnException(this);
		Cell temp = new Cell();
		switch (r) {
		case RIGHT:
			temp = getGame().getCellAt(this.getPosI(), (this.getPosJ() + 1) % 6);
			break;
		case LEFT:
			temp = getGame().getCellAt(getPosI(), (getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1)));
			break;
		case UP:
			temp = getGame().getCellAt((getPosI() - 1 == -1 ? 6 : (getPosI() - 1)), getPosJ());
			break;
		case DOWN:
			temp = getGame().getCellAt((this.getPosI() + 1) % 7, this.getPosJ());
			break;
		default: throw new UnallowedMovementException(this, r);
		}
		if (temp.getPiece() == null)
			moveHelper(r);
		else {
			if (this.getOwner() == temp.getPiece().getOwner()) {
				throw new OccupiedCellException(this, r);
			} else {
				if (temp.getPiece() instanceof Armored) {
					if (((Armored) temp.getPiece()).isArmorUp()) {
						attack(temp.getPiece());
						getGame().switchTurns();
					} else {
						attack(temp.getPiece());
						moveHelper(r);
					}
				} else {
					attack(temp.getPiece());
					moveHelper(r);
				}
			}
		}

	}

	@Override
	public void usePower(Direction d, Piece target, Point newPos) throws PowerAlreadyUsedException, InvalidPowerTargetException, WrongTurnException, InvalidPowerDirectionException {
		if(getGame().getCurrentPlayer() != this.getOwner())
			throw new WrongTurnException(this);
		
		if (isPowerUsed()) {
			throw new PowerAlreadyUsedException(this);
		} else {
			if(target.getOwner() != this.getOwner())
				throw new InvalidPowerTargetException("You can't resurrect enemy piece!", this, target);
			
			if (this.getOwner().getDeadCharacters().contains(target)) { // resurrect
				this.getOwner().getDeadCharacters().remove(target);
			}
			else {
				throw new InvalidPowerTargetException("You can't resurrect alive piece!", this, target);
			}
			try
			{
				switch (d)
				{
				case RIGHT:
					if(getGame().getCellAt(this.getPosI(), this.getPosJ() + 1).getPiece()==null){
						getGame().getCellAt(this.getPosI(), this.getPosJ() + 1).setPiece(target);
						target.setPosI(this.getPosI());
						target.setPosJ(this.getPosJ() + 1);
						break;
					}
					else{
						throw new InvalidPowerTargetException("Target location occupied!", this, target);
					}
					
				case LEFT:
					if(getGame().getCellAt(this.getPosI(), this.getPosJ() - 1).getPiece()==null){
						getGame().getCellAt(this.getPosI(), this.getPosJ() - 1).setPiece(target);
						target.setPosI(this.getPosI());
						target.setPosJ(this.getPosJ() - 1);
					break;
					}
					else{
						throw new InvalidPowerTargetException("Target location occupied!", this, target);
					}
				case UP:
					if(getGame().getCellAt(this.getPosI() - 1, this.getPosJ()).getPiece()==null){
						getGame().getCellAt(this.getPosI() - 1, this.getPosJ()).setPiece(target);
						target.setPosI(this.getPosI() - 1);
						target.setPosJ(this.getPosJ());
					break;
					}
					else{
						throw new InvalidPowerTargetException("Target location occupied!", this, target);
					}
				case DOWN:
					if(getGame().getCellAt(this.getPosI() + 1, this.getPosJ()).getPiece()==null){
						getGame().getCellAt(this.getPosI() + 1, this.getPosJ()).setPiece(target);
						target.setPosI(this.getPosI() + 1);
						target.setPosJ(this.getPosJ());
					break;
					}
					else{
						throw new InvalidPowerTargetException("Target location occupied!", this, target);
					}
				case UPRIGHT:
					if(getGame().getCellAt(this.getPosI() - 1, this.getPosJ() + 1).getPiece()==null){
						getGame().getCellAt(this.getPosI() - 1, this.getPosJ() + 1).setPiece(target);
						target.setPosI(this.getPosI() - 1);
						target.setPosJ(this.getPosJ() + 1);
					break;
					}
					else{
						throw new InvalidPowerTargetException("Target location occupied!", this, target);
					}
				case UPLEFT:
					if(getGame().getCellAt(this.getPosI() - 1, this.getPosJ() - 1).getPiece()==null){
						getGame().getCellAt(this.getPosI() - 1, this.getPosJ() - 1).setPiece(target);
						target.setPosI(this.getPosI() - 1);
						target.setPosJ(this.getPosJ() - 1);
					break;
					}
					else{
						throw new InvalidPowerTargetException("Target location occupied!", this, target);
					}
				case DOWNRIGHT:
					if(getGame().getCellAt(this.getPosI() + 1, this.getPosJ() + 1).getPiece()==null){
						getGame().getCellAt(this.getPosI() + 1, this.getPosJ() + 1).setPiece(target);
						target.setPosI(this.getPosI() + 1);
						target.setPosJ(this.getPosJ() + 1);
					break;
					}
					else{
						throw new InvalidPowerTargetException("Target location occupied!", this, target);
					}
				case DOWNLEFT:
					if(getGame().getCellAt(this.getPosI() + 1, this.getPosJ() - 1).getPiece()==null){
						getGame().getCellAt(this.getPosI() + 1, this.getPosJ() - 1).setPiece(target);
						target.setPosI(this.getPosI() + 1);
						target.setPosJ(this.getPosJ() - 1);
					break;
					}
					else{
						throw new InvalidPowerTargetException("Target location occupied!", this, target);
					}
				}
			}
			catch(NullPointerException e)
			{
				throw new InvalidPowerDirectionException("Null", this, d);
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
//				getGame().switchTurns();
			}
			setPowerUsed(true);
			getGame().switchTurns();
		}
	}
}