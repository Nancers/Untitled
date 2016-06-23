/* A Pokemon class. Stores information about a Pokemon.
 */

import java.util.*;
import java.io.*;
import java.lang.*;

public class Pokemon {
    public static final int MAX_IV          = 32;  // Max IV value
    public static final int MAX_EV          = 251; // Max EV value for any stat
    public static final int BASE_FRIENDSHIP = 70;  // Default friendship
    public static final int MAX_FRIENDSHIP  = 255; // Max friendship

    private int      id;         // ID # on the regional Pokedex; starts at 1
    private String   nickname;   // Name given by trainer, if any
    private int      maxHP;      // Max health 
    private int      curHP;      // Current health
    private String[] stats;      // Stats inflicted (can you have multiple?)
    private Move[]   moves;      // Up to 4 moves
    private String   heldItem;   // Need a parent/abstract class of items?
    private int      curLvl;     // Current level
    private int      curExp;     // Current exp gained for current level
    private int      totalExp;   // Exp needed for next level

    private int      attack;     // Current attack value
    private int      defense;    // Current defense
    private int      spAttack;   // Current special attack
    private int      spDefense;  // Current special defense
    private int      speed;      // Current speed
    private int      friendship; // Current friendship
    private String   gender;     // Male or female
    private Nature   nature;     // Used to calculate stat growth
    private String   character;  // Used to calculate stat growth
    private int      iv;         // Undividual value
    private int      ev;         // Effort value

    private boolean  isEgg;      // If Pokemon is in egg form
    private int      hatchTime;  // Steps left to hatch

    //--------------------------------------------------------------------------
    //------------------------------Constructors--------------------------------
    //--------------------------------------------------------------------------

    // Constructs a wild Pokemon
    public Pokemon(int n, int minLvl, int maxLvl, Pokedex p) {
        id          = n;
        nickname    = getName(n);
        iv          = randNum(0, MAX_IV);
        ev          = 0;
        curLvl      = randNum(minLvl, maxLvl);
        maxHP       = calcHP(getBaseHP(id));
        curHP       = maxHP;
        stats       = new String[];
        moves       = new Move[4];  // need a function to generate this
        heldItem    = "None";
        curExp      = 0;
        totalExp    = 100;
        nature      = new Nature();
        attack      = calcStat(p.getBaseAttack(id), nature.getAttackMod());
        defense     = calcStat(p.getBaseDefense(id), nature.getDefenseMod());
        spAttack    = calcStat(p.getBaseSpAttack(id), nature.getSpAttackMod());
        spDefense   = calcStat(p.getBaseSpDefense(id), nature.getSpDefenseMod());
        speed       = calcStat(p.getBaseSpeed(id), nature.getSpeedMod());
        friendship  = 70;
        gender      = "Male";
        character   = "Something";
        isEgg       = false;
        hatchTime   = 0;
    }

    // Constructs a trainer owned Pokemon with pre-determined stats
    public Pokemon() {

    }

    // Constructs a Pokemon in egg form
    public Pokemon(int num, boolean egg, int steps) {
        number = num;
        nickname = "Egg";
        curLvl = 1;
        maxHP = genHP();

        isEgg = true;
        //hatchTime = genRandNum();

    }

    //--------------------------------------------------------------------------
    //----------------------------Getters/Setters-------------------------------
    //--------------------------------------------------------------------------

    // Renames a Pokemon
    // Input:   n - new name
    // Returns nothing
    public void setName(String n) {
        nickname = n;
    }

    //--------------------------------------------------------------------------
    //-------------------------------Formulas-----------------------------------
    //--------------------------------------------------------------------------

    // Returns a random number [min, max]
    public int randNum(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max + 1) + min;
    }

    // Returns rounded down stat calculation based on base stat
    // Input:   base - base stat
    public int calcStat(int base, double nMod) {
        return (int) (base * 2.0 + iv + (ev/4.0)) * curLvl/100.0 + 5 * nMod); 
    }

    // Returns rounded down HP calculation based on base HP
    // Input:   base - base HP
    public int calcHP(int base) {
        return (int) (base * 2.0 + iv + (ev/4.0)) * curLvl/100.0 + 10 + curLvl);
    }
}