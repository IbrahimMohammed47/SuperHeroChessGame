package model.pieces.heroes;

import java.awt.image.BufferedImage;

import model.game.Game;
import model.game.Player;
import model.pieces.Piece;

public abstract class Hero extends Piece {

	public Hero(Player player, Game game, String name, BufferedImage image) {
		super(player, game, name, image);
	}

}