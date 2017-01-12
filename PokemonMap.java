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
    public PokemonMap(String url, int w, int h, int x_player, int y_player, int[][] m) {
        mapURL = "pics" + File.separator + url;

        // Create buffered image of current area
        try {
            mapImage = ImageIO.read(new File(mapURL));
        }
        catch (IOException e) {
            System.out.println("Can't find map.");
        }

        widthMap = w;
        heightMap = h;
        mapMatrix = m;
        curX = x_player - Player.PLAYER_WIDTH / 2 - PokemonGame.PANEL_WIDTH / 2;
        curY = y_player - Player.PLAYER_HEIGHT / 2 - PokemonGame.PANEL_HEIGHT / 2;

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
    public boolean moveMapX() {
        if (curX != 0 && curX + PokemonGame.PANEL_WIDTH < widthMap) {
            curX = x_player - PokemonGame.PANEL_WIDTH / 2;

            return true;
        }

        return false;
    }

    /* This function determines if the map or player should be moved in the y
     * direction.
     */
    public boolean moveMapY() {
        if (curY != 0 && curY + PokemonGame.PANEL_HEIGHT < heightMap) {
            curY = y_player - PokemonGame.PANEL_HEIGHT / 2;

            return true;
        }

        return false;
    }
}