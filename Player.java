/*
 * Contains all information attached to the player
 */

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Player {
	public static final int PLAYER_WIDTH = 50;
	public static final int PLAYER_HEIGHT = 50;
	public static final int STEP_SIZE = 50;

	private String name;
	private BufferedImage sprite;
	private int posX, posY;

	// private Pokemon[6] pokemonTeam;

	public Player(int x, int y) {
		posX = x;
		posY = y;

		// Load the sprite
        try {
            sprite = ImageIO.read(new File("pics" + File.separator
            	+ "trainer" + File.separator + "red-sprite.jpg"));
        }
        catch (IOException e) {}
	}

	// Getters
	public BufferedImage getSprite() {
		return sprite;
	}

	public int getX() { 
		return posX;
	}

	public int getY() {
		return posY;
	}

	// Adjusting the position on player move
	public void moveLeft(PokemonMap map) {
		if (posX >= STEP_SIZE) {
			posX -= STEP_SIZE;
			map.updateMX(-1);
		}
	}

	public void moveRight(PokemonMap map) {
		if (posX <= PokemonGame.PANEL_WIDTH - 2 * STEP_SIZE) {
			posX += STEP_SIZE;
			map.updateMX(1);
		}
	}

	public void moveUp(PokemonMap map) {
		if (posY >= STEP_SIZE) {
			posY -= STEP_SIZE;
			map.updateMY(-1);
		}
	}

	public void moveDown(PokemonMap map) {
		if (posY <= PokemonGame.PANEL_HEIGHT - 2 * STEP_SIZE) {
			posY += STEP_SIZE;
			map.updateMY(1);
		}
	}
}