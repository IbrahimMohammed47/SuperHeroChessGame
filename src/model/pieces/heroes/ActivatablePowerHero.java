package model.pieces.heroes;

import java.awt.Point;
import java.awt.image.BufferedImage;

import exceptions.InvalidPowerDirectionException;
import exceptions.InvalidPowerTargetException;
import exceptions.OccupiedCellException;
import exceptions.PowerAlreadyUsedException;
import exceptions.WrongTurnException;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public abstract class ActivatablePowerHero extends Hero {

	private boolean powerUsed;

	public ActivatablePowerHero(Player player, Game game, String name ,BufferedImage image) {
		super(player, game, name, image);
	}

	public boolean isPowerUsed() {
		return powerUsed;
	}

	public void setPowerUsed(boolean powerUsed) {
		this.powerUsed = powerUsed;
	}
	
	public void usePower(Direction d, Piece target, Point newPos) throws WrongTurnException, PowerAlreadyUsedException, InvalidPowerDirectionException, InvalidPowerTargetException, OccupiedCellException {
		
	}
	
}
