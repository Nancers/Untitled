/* An instance of this represents the current area the player is at.
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class PokemonMap {
    // CONSTANTS FOR MATRIX
    public static final int GRASS = 0;
    public static final int WALL = 1;
    public static final int WEED = 2;

    private String mapURL;                 // loc of the picture to render
    private BufferedImage mapImage;        // the entire area image
    private BufferedImage curImage;        // current image rendered depending on where the player is
    private int widthMap;                  // width of the area
    private int heightMap;                 // height of the area
    private int curX;                      // current x-coordinate of the top left corner of the area shown
    private int curY;                      // current y-coordinate of the top left corner of the area shown
    private int mX;                        // current x-index of matrix
    private int mY;                        // current y-index of matrix
    private int[][] mapMatrix;             // map of integers indicating whether
                                           // player can move towards it on the

    // Do we write the matrix beforehand?? Do we just the current area and
    // matrix together idk
    public PokemonMap(String pic, int w, int h, int x_player, int y_player, int[][] m) {
        mapURL = "pics" + File.separator + pic;

        // Create buffered image of current area
        try {
            mapImage = ImageIO.read(new File(mapURL));
        }
        catch (IOException e) {
            System.out.println("Can't find map.");
        }

        widthMap = w;
        heightMap = h;
        // TODO: Need to figure out how to define each tile in map
        //mapMatrix = m;
        // TODO: We will have to fix up where the player should show up in each
        // map; for now it's defaulted to center
        // We need a way to keep track of the player's location every time they
        // move to a new location (START_X and START_Y)

        mapMatrix = m;
        curX = w / 2 - PokemonGame.PANEL_WIDTH/2 + 25;
        curY = h / 2 - PokemonGame.PANEL_HEIGHT/2 + 25;
        mX = (w / 2) / PokemonGame.TILE_SIZE;
        mY = (h / 2) / PokemonGame.TILE_SIZE;

        System.out.println("0: Walkable");
        System.out.println("1: Unwalkable");
        System.out.println("2: Weed");

        updateCurImage();
    }

    /* This function updates the current map image shown onto screen. moveMapX
     * and moveMapY should be called beforehand to update coordinates.
     *
     * TODO: Should we check the matrix value before we update or after we update?
     * Have to consider the different animations depending on what tile we
     * step on next (e.g. jumping off a cliff, entering/exiting an area); for
     * now I just have it checking the matrix value after moving.
     */
    public void updateCurImage() {
        // http://stackoverflow.com/questions/19601116/how-to-draw-part-of-a-large-bufferedimage
        curImage = mapImage.getSubimage(curX, curY, PokemonGame.PANEL_WIDTH, PokemonGame.PANEL_HEIGHT);
        System.out.println("mX: " + mX);
        System.out.println("mY: " + mY);
        System.out.println("Current matrix value: " + mapMatrix[mY][mX]);
    }

    public BufferedImage getCurImage() {
        return curImage;
    }

    /* This function determines if the map or player should be moved in the x
     * direction.
     */
    public boolean moveMapX(int x_player, int direction) {
        // Edge cases:
        // If we are at the left end of the map and player wants to move right,
        // check if the player has reached the middle of panel; if yes, move the
        // map; otherwise map is static as player moves right.
        //
        // Alternatively, if we are at the right end of the map and player wants
        // to move left, check if the player has reached the middle of panel; if
        // yes, move the map; otherwise map is static as player moves left.
        if (curX == 0 && direction > 0) {
            if (x_player >= PokemonGame.MIDDLE_X) {
                return true;
            }

            return false;
        }

        if (curX == widthMap - PokemonGame.PANEL_WIDTH && direction < 0) {
            if (x_player <= PokemonGame.MIDDLE_X) {
                return true;
            }

            return false;
        }

        // Otherwise we can move the map as long as we aren't at the
        // left/right edges.
        if (curX != 0 && curX != widthMap - PokemonGame.PANEL_WIDTH) {
            return true;
        }

        return false;
    }

    /* This function determines if the map or player should be moved in the y
     * direction.
     */
    public boolean moveMapY(int y_player, int direction) {
        
        if (curY == 0 && direction > 0) {
            if (y_player >= PokemonGame.MIDDLE_Y) {
                return true;
            }

            return false;
        }

        if (curY == heightMap - PokemonGame.PANEL_HEIGHT && direction < 0) {
            if (y_player <= PokemonGame.MIDDLE_Y) {
                return true;
            }

            return false;
        }

        // Otherwise we can move the map as long as we aren't at the
        // left/right edges.
        if (curY != 0 && curY != heightMap - PokemonGame.PANEL_HEIGHT) {
            return true;
        }

        return false;
    }

    public void updateMX(int direction) {
        mX += direction;
    }

    public void updateMY(int direction) {
        mY += direction;
    }

    public void updateCurX(int direction) {
        curX = curX + direction * Player.STEP_SIZE;
    }

    public void updateCurY(int direction) {
        curY = curY + direction * Player.STEP_SIZE;
    }

    public int getMX() {
        return mX;
    }

    public int getMY() {
        return mY;
    }

    public int getMValue(int y, int x) {
        return mapMatrix[y][x];
    }

    public int curMValue() {
        return mapMatrix[mY][mX];
    }
}