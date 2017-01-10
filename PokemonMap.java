/* An instance of this represents the current area the player is at.
 */

import java.util.*;
import java.io.*;
import java.lang.*;

public class PokemonMap {
    public static final int MAP_WIDTH          = 700;  // Width of map screen
    public static final int MAP_HEIGHT         = 350;  // Height of map screen

    private String worldURL;               // loc of the picture to render
    private BufferedImage worldImage;      // the entire area image
    private BufferedImage currentImage;    // current image rendered depending on where the player is
    private int width;                     // width of the area
    private int height;                    // height of the area
    private int x;                         // current x-coordinate of the top left side of the map
    private int y;                         // current y-coordinate of the top left side of the map
    private int[][] mapMatrix;             // map of integers indicating whether
                                           // player can move towards it on the

    // Do we write the matrix beforehand?? Do we just the current area and
    // matrix together idk
    public PokemonMap(String url, int w, int h, int x_player, int y_player, int[][] m) {
        worldURL = "pics" + File.separator + url;

        // Create buffered image of current area
        try {
            mapImage = ImageIO.read(new File(worldURL));
        }
        catch (IOException e) {
            System.out.println("Can't find map.");
        }

        width = w;
        height = h;

        mapMatrix = m;

        /* TODO: set currentImage given player coordinates */
        updateMapImage(x, y);
    }

    /* This function updates the map shown on the top screen of the game
     * depending on the coordinates of the player. The player should always
     * be in the center, with the map moving, unless the player is hitting an
     * edge. */
    public void updateMapImage(int x_player, int y_player) {

        /* TODO: set currentImage given player coordinates */

    }
}