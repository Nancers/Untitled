/* A Pokemon class. Stores information about a Pokemon for the game.
 */

import java.util.*;
import java.io.*;

public class Pokemon {
    // Basic info for the Pokedex
    private int      number;         // # on the regional Pokedex
    private String   name;           // Official name
    private String   nickname;       // Nickname, if any
    private String   altername;      // Alternate name (e.g. Lizard Pokemon)
    private String[] type;           // Type(s)
    private String   ability;        // Known ability
    private double   height;         // Height
    private double   weight;         // Weight
    private String   bio;            // Pokedex bio
    private String   artFile;        // The drawing to display

    // Other stuff needed for battles/Pokedex
    private int      maxHP;          // Max health 
    private int      curHP;          // Current health
    private String[] stats;          // Stats inflicted (can you have multiple?)
    private Move[]   moves;          //***** Make a class for moves
    private String   hiddenAbility;  // Hidden ability
    private String   heldItem;       // Need a parent/abstract class of items?
    private int      curLvl;         // Current level
    private int      curExp;         // Current exp gained for current level
    private int      totalExp;       // Exp needed for next level
    private boolean  evolves;        // If Pokemon can evolve
    private int      evolveLvl;      // Level needed to evolve
    private boolean  megaEvolve;     // If Pokemon has a mega-evolution
    private String   frontSprite;    // Pokemon sprite file
    private String   backSprite;     // Pokemon sprite file
    private String   miniSprite;     // Pokemon sprite for inventory
    // Not sure if we should add a boolean where Pokemon won't listen to you if
    // it's too high lvl; or we can determine that from the # of badges a player
    // has; or we can just ignore this factor of the game

    // Other Pokemon stats
    private int      attack;
    private int      defense;
    private int      spAttack;       // Special attack
    private int      spDefense;      // Special defense
    private int      speed;
    private int      friendship;
    private String   gender;
    private String   nature;
    private String   character;
    /* TODO: you can add all the ev/iv stuff :D
     * also not sure how exp gain is calculated */

    // Map of moves it can learn; hashset keeps keys in order?
    private TreeMap<Int, String> movesToLearn; // level: move
    private String[]             machineMoves; // moves it can learn from HM/TM

    // For wild Pokemon too
    private int      catchRate;      // Default catch rate at full HP

    // For unhatched Pokemon.....do we need a separate class for this?
    private boolean  isEgg;          // If Pokemon is in egg form
    private int      hatchTime;      // Steps left to hatch
    private String[] eggGroup;       // not sure what this is used for...
}