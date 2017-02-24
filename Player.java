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
			int nextMValue = map.getMValue(map.getMY(), map.getMX() - 1);
			boolean move = true;
                            
            switch(nextMValue) {
                case PokemonMap.GRASS:
                    break;

                case PokemonMap.WALL:
                	move = false;
                	break;

                case PokemonMap.WEED:
                	break;
            }

            if (move) {
            	posX -= STEP_SIZE;
				map.updateMX(-1);
			}

			System.out.println("mX: " + map.getMX());
			System.out.println("mY: " + map.getMY());
			System.out.println("Current matrix value: " + map.curMValue());
			System.out.println("--------");
		}
	}

	public void moveRight(PokemonMap map) {
		if (posX <= PokemonGame.PANEL_WIDTH - 2 * STEP_SIZE) {

			int nextMValue = map.getMValue(map.getMY(), map.getMX() + 1);
			boolean move = true;
                            
            switch(nextMValue) {
                case PokemonMap.GRASS:
                    break;

                case PokemonMap.WALL:
                	move = false;
                	break;

                case PokemonMap.WEED:
                	break;
            }

            if (move) {
				posX += STEP_SIZE;
				map.updateMX(1);
			}

			System.out.println("mX: " + map.getMX());
			System.out.println("mY: " + map.getMY());
			System.out.println("Current matrix value: " + map.curMValue());
			System.out.println("--------");
		}
	}

	public void moveUp(PokemonMap map) {
		if (posY >= STEP_SIZE) {
			int nextMValue = map.getMValue(map.getMY() - 1, map.getMX());
			boolean move = true;
                            
            switch(nextMValue) {
                case PokemonMap.GRASS:
                    break;

                case PokemonMap.WALL:
                	move = false;
                	break;

                case PokemonMap.WEED:
                	break;
            }

            if (move) {
				posY -= STEP_SIZE;
				map.updateMY(-1);
			}

			System.out.println("mX: " + map.getMX());
			System.out.println("mY: " + map.getMY());
			System.out.println("Current matrix value: " + map.curMValue());
			System.out.println("--------");
		}
	}

	public void moveDown(PokemonMap map) {
		if (posY <= PokemonGame.PANEL_HEIGHT - 2 * STEP_SIZE) {
			int nextMValue = map.getMValue(map.getMY() + 1, map.getMX());
			boolean move = true;
                            
            switch(nextMValue) {
                case PokemonMap.GRASS:
                    break;

                case PokemonMap.WALL:
                	move = false;
                	break;

                case PokemonMap.WEED:
                	break;
            }

            if (move) {
				posY += STEP_SIZE;
				map.updateMY(1);
			}

			System.out.println("mX: " + map.getMX());
			System.out.println("mY: " + map.getMY());
			System.out.println("Current matrix value: " + map.curMValue());
			System.out.println("--------");
		}
	}
}