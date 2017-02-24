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
	public boolean moveLeft(PokemonMap map) {
		boolean move = false;

		if (posX >= STEP_SIZE) {
			int nextMValue = map.getMValue(map.getMY(), map.getMX() - 1);

            move = canMove(nextMValue);

            if (move) {
            	posX -= STEP_SIZE;
				map.updateMX(-1);

				System.out.println("mX: " + map.getMX());
				System.out.println("mY: " + map.getMY());
				System.out.println("Current matrix value: " + map.curMValue());
			}
		}

		return move;
	}

	public boolean moveRight(PokemonMap map) {
		boolean move = false;

		if (posX <= PokemonGame.PANEL_WIDTH - 2 * STEP_SIZE) {
			int nextMValue = map.getMValue(map.getMY(), map.getMX() + 1);
                            
            move = canMove(nextMValue);

            if (move) {
				posX += STEP_SIZE;
				map.updateMX(1);

				System.out.println("mX: " + map.getMX());
				System.out.println("mY: " + map.getMY());
				System.out.println("Current matrix value: " + map.curMValue());
			}
		}

		return move;
	}

	public boolean moveUp(PokemonMap map) {
		boolean move = false;

		if (posY >= STEP_SIZE) {
			int nextMValue = map.getMValue(map.getMY() - 1, map.getMX());
                            
            move = canMove(nextMValue);

            if (move) {
				posY -= STEP_SIZE;
				map.updateMY(-1);

				System.out.println("mX: " + map.getMX());
				System.out.println("mY: " + map.getMY());
				System.out.println("Current matrix value: " + map.curMValue());
			}
		}

		return move;
	}

	public boolean moveDown(PokemonMap map) {
		boolean move = false;

		if (posY <= PokemonGame.PANEL_HEIGHT - 2 * STEP_SIZE) {
			int nextMValue = map.getMValue(map.getMY() + 1, map.getMX());
                            
            move = canMove(nextMValue);

            if (move) {
				posY += STEP_SIZE;
				map.updateMY(1);

				System.out.println("mX: " + map.getMX());
				System.out.println("mY: " + map.getMY());
				System.out.println("Current matrix value: " + map.curMValue());
			}
		}

		return move;
	}

	private boolean canMove(int nextValue) {
        switch(nextValue) {
            case PokemonMap.GRASS:
                return true;

            case PokemonMap.WALL:
                return false;

            case PokemonMap.WEED:
                return true;
            }

        return true;
    }
}