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
    private String mapURL;                 // loc of the picture to render
    private BufferedImage mapImage;        // the entire area image
    private BufferedImage curImage;        // current image rendered depending on where the player is
    private int widthMap;                  // width of the area
    private int heightMap;                 // height of the area
    private int curX;                      // current x-coordinate of the top left corner of the area shown
    private int curY;                      // current y-coordinate of the top left corner of the area shown
    private int[][] mapMatrix;             // map of integers indicating whether
                                           // player can move towards it on the

    // Do we write the matrix beforehand?? Do we just the current area and
    // matrix together idk
    public PokemonMap(String pic, int w, int h, int x_player, int y_player) {
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
        curX = w / 2;
        curY = w / 2;

        updateCurImage();
    }

    /* This function updates the current map image shown onto screen. moveMapX
     * and moveMapY should be called beforehand to update coordinates.
     */
    public void updateCurImage() {
        // http://stackoverflow.com/questions/19601116/how-to-draw-part-of-a-large-bufferedimage
        curImage = mapImage.getSubimage(curX, curY, PokemonGame.PANEL_WIDTH, PokemonGame.PANEL_HEIGHT);
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
                curX = curX + direction * Player.STEP_SIZE;
                return true;
            }

            return false;
        }

        if (curX == widthMap - PokemonGame.PANEL_WIDTH && direction < 0) {
            if (x_player <= PokemonGame.MIDDLE_X) {
                curX = curX + direction * Player.STEP_SIZE;
                return true;
            }

            return false;
        }

        // Otherwise we can move the map as long as we aren't at the
        // left/right edges.
        if (curX != 0 && curX != widthMap - PokemonGame.PANEL_WIDTH) {
            curX = curX + direction * Player.STEP_SIZE;
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
                curY = curY + direction * Player.STEP_SIZE;
                return true;
            }

            return false;
        }

        System.out.println("Current y: " + curY);
        System.out.println("Max y: " + (heightMap - PokemonGame.PANEL_HEIGHT));
        if (curY == heightMap - PokemonGame.PANEL_HEIGHT && direction < 0) {
            if (y_player <= PokemonGame.MIDDLE_Y) {
                curY = curY + direction * Player.STEP_SIZE;
                return true;
            }

            return false;
        }

        // Otherwise we can move the map as long as we aren't at the
        // left/right edges.
        if (curY != 0 && curY != heightMap - PokemonGame.PANEL_HEIGHT) {
            curY = curY + direction * Player.STEP_SIZE;
            return true;
        }

        return false;
    }
}