/* A Move class. Stores information about Moves.
 */

import java.util.*;
import java.io.*;

public class Move {
    // Basic info for the Pokedex
    private String   name;           // Move name
    private String   type;           // Type
    private String   category;       // Physical, special or status
    private String   curPP;          // current amount of PP left
    private int      maxPP;          // max amount of PP
    private int      power;          // power, if any
    private int      accuracy;       // % accuracy, if any
    private String   contest;        // probably don't need this
}