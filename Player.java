/*
 * Contains all information attached to the player
 */

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Player {
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
	public void moveLeft() {
		posX = (posX >= STEP_SIZE) ? posX - STEP_SIZE : posX;
	}

	public void moveRight() {
		posX = (posX <= PokemonGame.WIDTH - STEP_SIZE) ? posX + STEP_SIZE : posX;
	}

	public void moveUp() {
		posY = (posY >= STEP_SIZE) ? posY - STEP_SIZE : posY;
	}

	public void moveDown() {
		posY = (posY <= PokemonGame.HEIGHT - STEP_SIZE) ? posY + STEP_SIZE : posY;
	}
}