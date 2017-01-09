/* An instance of this represents the current area the player is at.
 */

import java.util.*;
import java.io.*;

public class PokemonMap {

    private String worldURL;
    private BufferedImage worldImage;
    private int width;
    private int height;
    private int[][] areaMatrix;

    // Do we write the matrix beforehand?? Do we just the current area and
    // matrix together idk
    public PokemonMap(String url, int w, int h) {

        worldURL = url;

        // Create buffered image of current area
        try {
            mapImage = ImageIO.read(new File("pics" + File.separator + "pallet_town.png"));
        }
        catch (IOException e) {}

        width = w;
        height = h;

        areaMatrix = new int[w][h];
    }
}