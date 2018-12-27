package model.pieces;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import exceptions.WrongTurnException;
import graphics.Assets;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Hero;
import model.pieces.heroes.Speedster;

public abstract class Piece implements Movable {

	private String name;
	private Player owner;
	private Game game;
	private int posI;
	private int posJ;
	private BufferedImage image ;

	public Piece(Player p, Game g, String name,BufferedImage image ) {
		this.owner = p;
		this.game = g;
		this.name = name;
		this.image = image ;

	}
	public BufferedImage getImage() {
		return image;
	}
	public void SetImage(BufferedImage image) {
		this.image = image ;
	}

	public String getName() {
		return name;
	}

	public int getPosI() {
		return posI;
	}

	public void setPosI(int i) {
		posI = i;
	}

	public int getPosJ() {
		return posJ;
	}

	public void setPosJ(int j) {
		posJ = j;
	}

	public Game getGame() {
		return game;
	}

	public Player getOwner() {
		return owner;
	}
	
	public void attack(Piece target) 
	{ 
		if(target instanceof Hero)
		{
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
					target.getOwner().getDeadCharacters().add(target);	//Bury enemy
					
					if(getGame().getCurrentPlayer()==getGame().getPlayer1()) {   //Add to cemetry
						getGame().getP2cemetryButs().get(target.getOwner().getDeadCharacters().size()-1).setIcon(new ImageIcon((new ImageIcon(target.getImage())).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));//putting  in button
					}else {
						getGame().getP1cemetryButs().get(target.getOwner().getDeadCharacters().size()-1).setIcon(new ImageIcon((new ImageIcon(target.getImage())).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));//putting  in button
					}
					
					getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null); //Remove enemy
					
					this.getOwner().setPayloadPos(this.getOwner().getPayloadPos() + 1);	//Update payload
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
					}
					getGame().getCurrentpayLoadPans()[this.getOwner().getPayloadPos()-1].setBackground(Color.RED); //Update payload graphics
					getGame().checkWinner();
				}
			}
			else
			{
				target.getOwner().getDeadCharacters().add(target);	//Bury enemy
				
				if(getGame().getCurrentPlayer()==getGame().getPlayer1()) {   //Add to cemetry
					getGame().getP2cemetryButs().get(target.getOwner().getDeadCharacters().size()-1).setIcon(new ImageIcon((new ImageIcon(target.getImage())).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));//putting  in button
				}else {
					getGame().getP1cemetryButs().get(target.getOwner().getDeadCharacters().size()-1).setIcon(new ImageIcon((new ImageIcon(target.getImage())).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));//putting  in button
				}
					getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null); 
				this.getOwner().setPayloadPos(this.getOwner().getPayloadPos() + 1);	//Update payload
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
				}
				getGame().getCurrentpayLoadPans()[this.getOwner().getPayloadPos()-1].setBackground(Color.RED); //Update payload graphics
				getGame().checkWinner();
			}
		}
		else
		{
			target.getOwner().getDeadCharacters().add(target);	//Bury enemy
			
			if(getGame().getCurrentPlayer()==getGame().getPlayer1()) {   //Add to cemetry
				getGame().getP2cemetryButs().get(target.getOwner().getDeadCharacters().size()-1).setIcon(new ImageIcon((new ImageIcon(target.getImage())).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));//putting  in button
			}else {
				getGame().getP1cemetryButs().get(target.getOwner().getDeadCharacters().size()-1).setIcon(new ImageIcon((new ImageIcon(target.getImage())).getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));//putting  in button
			}
			
			this.getOwner().setSideKilled(this.getOwner().getSideKilled() + 1); //Update sidekilled
				getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null); 
	
			if(this.getOwner().getSideKilled() % 2 == 0) {
				this.getOwner().setPayloadPos(this.getOwner().getPayloadPos() + 1); //Update payload
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
				}
				getGame().getCurrentpayLoadPans()[this.getOwner().getPayloadPos()].setBackground(Color.RED); //Update payload graphics
			}
			getGame().checkWinner();
		}
	}
	
	protected void moveHelper(Direction r) throws WrongTurnException
	{
		if(getGame().getCurrentPlayer() == this.getOwner())
		{
			switch(r)
				{
					case UPRIGHT: moveUpRight(); break;
					case RIGHT: moveRight(); break;
					case DOWNRIGHT: moveDownRight(); break;
					
					case UPLEFT: moveUpLeft(); break;
					case LEFT: moveLeft(); break;
					case DOWNLEFT: moveDownLeft(); break;
					
					case UP: moveUp(); break;
					case DOWN: moveDown(); break;
				}
				getGame().switchTurns();
		}
		else
		{
			throw new WrongTurnException(this);
		}
	}
		
	private void moveDownLeft() //Done with posI & PosJ & Speedster
	{
		if (this instanceof Speedster)
		{
			getGame().getCellAt((this.getPosI() + 2) % 7, (getPosJ() - 2 == -1 ? 5 : getPosJ() - 2 == -2 ? 4 : (getPosJ() - 2))).setPiece(this);;
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			this.setPosI((this.getPosI() + 2) % 7);
			this.setPosJ((getPosJ() - 2 == -1 ? 5 : getPosJ() - 2 == -2 ? 4 : (getPosJ() - 2)));
		}
		else
		{
			getGame().getCellAt((this.getPosI() + 1) % 7, (getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1))).setPiece(this);
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			this.setPosI((this.getPosI() + 1) % 7);
			this.setPosJ((getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1)));
		}
	}
	
	private void moveDownRight() //Done with posI & PosJ & Speedster
	{
		if (this instanceof Speedster)
		{
			getGame().getCellAt((this.getPosI() + 2) % 7, (this.getPosJ() + 2) % 6).setPiece(this);;
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			this.setPosI((this.getPosI() + 2) % 7);
			this.setPosJ((this.getPosJ() + 2) % 6);
		}
		else
		{
			getGame().getCellAt((this.getPosI() + 1) % 7, (this.getPosJ() + 1) % 6).setPiece(this);
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			this.setPosI((this.getPosI() + 1) % 7);
			this.setPosJ((this.getPosJ() + 1) % 6);
		}
	}
	
	private void moveUpLeft() //Done with posI & PosJ & Speedster
	{
		if (this instanceof Speedster)
		{
			getGame().getCellAt((getPosI() - 2 == -1 ? 6 : getPosI() - 2 == -2 ? 5 : (getPosI() - 2)), (getPosJ() - 2 == -1 ? 5 : getPosJ() - 2 == -2 ? 4 : (getPosJ() - 2))).setPiece(this);;
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			this.setPosI((getPosI() - 2 == -1 ? 6 : getPosI() - 2 == -2 ? 5 : (getPosI() - 2)));
			this.setPosJ((getPosJ() - 2 == -1 ? 5 : getPosJ() - 2 == -2 ? 4 : (getPosJ() - 2)));
		}
		else
		{
			getGame().getCellAt((getPosI() - 1 == -1 ? 6 : (getPosI() - 1)), (getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1))).setPiece(this);
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			this.setPosI((getPosI() - 1 == -1 ? 6 : (getPosI() - 1)));
			this.setPosJ((getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1)));
		}
	}
	
	private void moveUpRight() //Done with posI & PosJ & Speedster
	{
		if (this instanceof Speedster)
		{
			getGame().getCellAt(getPosI() - 2 == -1 ? 6 : getPosI() - 2 == -2 ? 5 : (getPosI() - 2), (this.getPosJ() + 2) % 6).setPiece(this);;
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			this.setPosI(getPosI() - 2 == -1 ? 6 : getPosI() - 2 == -2 ? 5 : (getPosI() - 2));
			this.setPosJ((this.getPosJ() + 2) % 6);
		}
		else
		{
			getGame().getCellAt(getPosI() - 1 == -1 ? 6 : (getPosI() - 1), (this.getPosJ() + 1) % 6).setPiece(this);
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			this.setPosI(getPosI() - 1 == -1 ? 6 : (getPosI() - 1));
			this.setPosJ((this.getPosJ() + 1) % 6);
		}
	}
	
	private void moveDown() //Done with posI & PosJ & Speedster
	{
		if (this instanceof Speedster)
		{
			getGame().getCellAt((this.getPosI() + 2) % 7, this.getPosJ()).setPiece(this);;
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			this.setPosI((this.getPosI() + 2) % 7);
			this.setPosJ(this.getPosJ());
		}
		else
		{		
			getGame().getCellAt((this.getPosI() + 1) % 7, this.getPosJ()).setPiece(this);
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			this.setPosI((this.getPosI() + 1) % 7);
			this.setPosJ(this.getPosJ());
		}
	}
	private void moveUp() //Done with posI & PosJ & Speedster 
	{
		if (this instanceof Speedster)
		{
			getGame().getCellAt((getPosI() - 2 == -1 ? 6 : getPosI() - 2 == -2 ? 5 : (getPosI() - 2)), getPosJ()).setPiece(this);;
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			this.setPosI((getPosI() - 2 == -1 ? 6 : getPosI() - 2 == -2 ? 5 : (getPosI() - 2)));
			this.setPosJ(this.getPosJ());
		}
		else
		{	
			getGame().getCellAt((this.getPosI() - 1 == -1 ? 6 : this.getPosI() - 1), this.getPosJ()).setPiece(this);
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			this.setPosI((getPosI() - 1 == -1 ? 6 : getPosI() - 1));
			this.setPosJ(this.getPosJ());
		}
	}
	
	private void moveRight() //Done with posI & PosJ & Speedster
	{
		if (this instanceof Speedster)
		{
			getGame().getCellAt(this.getPosI(), (this.getPosJ() + 2) % 6).setPiece(this);;
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			this.setPosI(this.getPosI());
			this.setPosJ((this.getPosJ() + 2) % 6);
		}
		else
		{	
			getGame().getCellAt(this.getPosI(), (this.getPosJ() + 1) % 6).setPiece(this);
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			this.setPosI(this.getPosI());
			this.setPosJ((this.getPosJ() + 1) % 6);
		}
	}
	
	private void moveLeft() //Done with posI & PosJ
	{
		if (this instanceof Speedster)
		{
			getGame().getCellAt(getPosI(), (getPosJ() - 2 == -1 ? 5 : getPosJ() - 2 == -2 ? 4 : (getPosJ() - 2))).setPiece(this);;
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			this.setPosI(this.getPosI());
			this.setPosJ((getPosJ() - 2 == -1 ? 5 : getPosJ() - 2 == -2 ? 4 : (getPosJ() - 2)));
		}
		else
		{	
			getGame().getCellAt(getPosI(), (getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1))).setPiece(this);
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			this.setPosI(this.getPosI());
			this.setPosJ((getPosJ() - 1 == -1 ? 5 : (getPosJ() - 1)));
		}
	}
}