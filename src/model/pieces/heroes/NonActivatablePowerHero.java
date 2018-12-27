package model.pieces.heroes;

import java.awt.image.BufferedImage;

import model.game.Game;
import model.game.Player;

public abstract class NonActivatablePowerHero extends Hero {

	public NonActivatablePowerHero(Player player, Game game, String name, BufferedImage image) {
		super(player, game, name, image);
	}

}
