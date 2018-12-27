package model.pieces.sidekicks;
import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import graphics.Assets;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Hero;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;

public abstract class SideKick extends Piece {

	public SideKick(Player player, Game game, String name,BufferedImage image) {
		super(player, game, name, image);
	}
	
	@Override
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
					getGame().getCurrentpayLoadPans()[this.getOwner().getPayloadPos()-1].setBackground(Color.RED); // Update graphics payload
					upgradeSidekick(target); //Upgrade Sidekick
					getGame().checkWinner();
				}
			}
			else
			{
				target.getOwner().getDeadCharacters().add(target);	//Damage enemy
				
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
				getGame().getCurrentpayLoadPans()[this.getOwner().getPayloadPos()-1].setBackground(Color.RED); // Update graphics payload
				upgradeSidekick(target); //Upgrade Sidekick	
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
	
			getGame().getCellAt(target.getPosI(), target.getPosJ()).setPiece(null); //Remove enemy
			this.getOwner().setSideKilled(getOwner().getSideKilled() + 1); //Update Sidekilled
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
			}
			if(getOwner().getSideKilled() % 2 == 0) {                  
				getGame().getCurrentpayLoadPans()[this.getOwner().getPayloadPos()].setBackground(Color.RED); // Update graphics payload
				getOwner().setPayloadPos(getOwner().getPayloadPos() + 1);                                    // Update payload switch
				
			}else {
				getOwner().setPayloadPos(getOwner().getPayloadPos());
			}
			
			
			
			
			getGame().checkWinner();
		}
		
	}
	
	private void upgradeSidekick(Piece target)
	{
		if(target instanceof Medic) {
			//getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(new Medic(this.getOwner(),this.getGame(),target.getName()));
		}
		else if(target instanceof Armored) {
			//getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(new Armored(this.getOwner(),this.getGame(),target.getName()));
		}
		else if(target instanceof Ranged) {
			//getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(new Ranged(this.getOwner(),this.getGame(),target.getName()));
		}
		else if(target instanceof Speedster) {
			//getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(new Speedster(this.getOwner(),this.getGame(),target.getName()));
		}
		else if(target instanceof Super) {
			//getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(new Super(this.getOwner(),this.getGame(),target.getName()));
		}
		else if(target instanceof Tech) {
			//getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(null);
			getGame().getCellAt(this.getPosI(), this.getPosJ()).setPiece(new Tech(this.getOwner(),this.getGame(),target.getName()));
		}
	}
	
	@Override
	public String toString() {
		return "K";
	}
	
}
