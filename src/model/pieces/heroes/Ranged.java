package model.pieces.heroes;

import java.awt.Point;

import exceptions.InvalidPowerDirectionException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.WrongTurnException;
import graphics.Assets;
import model.game.Cell;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public class Ranged extends ActivatablePowerHero {

	
	public Ranged(Player player, Game game, String name) {
		super(player, game, name, player==game.getPlayer1()?Assets.p1_ranged:Assets.p2_ranged);
	}

	public String toString() {
		return "R";
	}
	
	public void move (Direction r) throws WrongTurnException, OccupiedCellException
	{
		if(this.getOwner() != getGame().getCurrentPlayer())
			throw new WrongTurnException(this);
		Cell temp = new Cell();
		switch(r)
		{
			case UPRIGHT: temp = getGame().getCellAt(this.getPosI() - 1 == -1 ? 6 : (this.getPosI() - 1), (this.getPosJ() + 1) % 6); break;
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
	@Override
	public void usePower(Direction d, Piece target, Point newPos) throws PowerAlreadyUsedException, InvalidPowerDirectionException, WrongTurnException {
		if(getGame().getCurrentPlayer() != this.getOwner()){
			throw new WrongTurnException(this);
		}
		if(this.isPowerUsed()) {
			throw new PowerAlreadyUsedException(this);
		}
		else {
				switch(d)
				{
				case RIGHT:shootArrowRight(); break;
				case LEFT: shootArrowLeft(); break;
				case UP:   shootArrowUp(); break;
				case DOWN: shootArrowDown(); break;
				default: throw new InvalidPowerDirectionException("Wrong ARROW Direction", this, d);
				}
			
		}
		
	}
	
	private boolean checkUpDirectionEmpty(){
		boolean f = true;
		for(int i=this.getPosI()-1; i >= 0; i--){
			if(getGame().getCellAt(i, this.getPosJ()).getPiece()!=null)
				f= false;
		}
		return f;
	}
	
	private boolean checkDownDirectionEmpty(){
		boolean f = true;
		for(int i=this.getPosI()+1; i < getGame().getBoardHeight(); i++){
			if(getGame().getCellAt(i, this.getPosJ()).getPiece()!=null)
				f= false;
		}
		return f;
	}
	
	private boolean checkLeftDirectionEmpty(){
		boolean f = true;
		for(int j=this.getPosJ()-1; j >= 0; j--){
			if(getGame().getCellAt(this.getPosI(), j).getPiece()!=null)
				f= false;
		}
		return f;
	}
	
	private boolean checkRightDirectionEmpty(){
		boolean f = true;
		for(int j = this.getPosJ()+1; j < getGame().getBoardWidth(); j++){
			if(getGame().getCellAt(this.getPosI(), j).getPiece()!=null)
				f= false;
		}
		return f;
	}
	
	private void shootArrowRight() throws InvalidPowerDirectionException {	
		if(!(this.checkRightDirectionEmpty())){
		for(int j = this.getPosJ()+1 ; j<getGame().getBoardWidth() ; j++) {
			if(getGame().getCellAt(this.getPosI(), j).getPiece()!=null) {
				if(getGame().getCellAt(this.getPosI(), j).getPiece().getOwner() == this.getOwner()) {
					throw new InvalidPowerDirectionException("Friendly Fire1!", this, Direction.RIGHT);
				}else {
					attack(getGame().getCellAt(this.getPosI(), j).getPiece());
					this.setPowerUsed(true);
					getGame().switchTurns();
					break;
				}
			}
				}
			}else
				throw new InvalidPowerDirectionException("No Pieces in that direction1!", this, Direction.RIGHT);
		}
	
	private void shootArrowLeft() throws InvalidPowerDirectionException {	
		if(!(this.checkLeftDirectionEmpty())){
		for(int j = this.getPosJ()-1 ; j>=0 ; j--) {
			if(getGame().getCellAt(this.getPosI(), j).getPiece()!=null) {
				if(getGame().getCellAt(this.getPosI(), j).getPiece().getOwner()==this.getOwner()) {
					throw new InvalidPowerDirectionException("Friendly Fire2!", this, Direction.LEFT);
				}else {
					attack(getGame().getCellAt(this.getPosI(), j).getPiece());
					this.setPowerUsed(true);
					getGame().switchTurns();
					break;
					}
			}
		}
		}else
			throw new InvalidPowerDirectionException("No Pieces in that direction2!", this, Direction.LEFT);
	}
	private void shootArrowDown() throws InvalidPowerDirectionException {	
		if(!(this.checkDownDirectionEmpty())){
		for(int i = this.getPosI()+1 ; i < getGame().getBoardHeight() ; i++) {
			if(getGame().getCellAt(i, this.getPosJ()).getPiece()!=null) {
				if(getGame().getCellAt(i, this.getPosJ()).getPiece().getOwner()==this.getOwner()) {
					throw new InvalidPowerDirectionException("Friendly Fire3!",this, Direction.DOWN);
				}else
				{
					attack(getGame().getCellAt(i, this.getPosJ()).getPiece());
					this.setPowerUsed(true);
					getGame().switchTurns();
					break;
				}
			}
		}
		}else
			throw new InvalidPowerDirectionException("No Pieces in that direction3!", this, Direction.DOWN);
	}
	private void shootArrowUp() throws InvalidPowerDirectionException {	
		if(!(this.checkUpDirectionEmpty())){
		for(int i = this.getPosI()-1 ; i >= 0 ; i--) {
			if(getGame().getCellAt(i, this.getPosJ()).getPiece()!=null) {
				if(getGame().getCellAt(i, this.getPosJ()).getPiece().getOwner()==this.getOwner()) {
					throw new InvalidPowerDirectionException("Friendly Fire4!", this, Direction.UP);
				} else {
					attack(getGame().getCellAt(i, this.getPosJ()).getPiece());
					this.setPowerUsed(true);
					getGame().switchTurns();
					break;
				}
			}
		}
		}else
			throw new InvalidPowerDirectionException("No Pieces in that direction4!", this, Direction.UP);
	}

}
