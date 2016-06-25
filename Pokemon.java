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
    public static final int NUM_NATURE      = 25;  // number of possible natures

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
    private int      iv;         // Individual value
    private Effort   ev;         // Effort values

    private boolean  isEgg;      // If Pokemon is in egg form
    private int      hatchTime;  // Steps left to hatch

    //--------------------------------------------------------------------------
    //------------------------------Constructors--------------------------------
    //--------------------------------------------------------------------------

    // Constructs a wild Pokemon
    public Pokemon(int i, int minLvl, int maxLvl, Pokedex p, Naturedex n) {
        id          = i;
        nickname    = p.getName(id);
        iv          = randNum(0, MAX_IV);
        ev          = new Effort();
        curLvl      = randNum(minLvl, maxLvl);
        maxHP       = calcHP(p.getBaseHP(id), ev.hpEV);
        curHP       = maxHP;
        stats       = new String[3];
        moves       = new Move[4];  // need a function to generate this
        heldItem    = "None";
        curExp      = 0;
        totalExp    = calcExp(p.getLvlRate(id), curLvl);
        nature      = n.getRandNature();
        attack      = calcStat(p.getBaseAttack(id), nature.getAtkM(), ev.atkEV);
        defense     = calcStat(p.getBaseDefense(id), nature.getDefM(), ev.defEV);
        spAttack    = calcStat(p.getBaseSpAttack(id), nature.getSAtkM(), ev.spAtkEV);
        spDefense   = calcStat(p.getBaseSpDefense(id), nature.getSDefM(), ev.spDefEV);
        speed       = calcStat(p.getBaseSpeed(id), nature.getSpeedM(), ev.speedEV);
        friendship  = BASE_FRIENDSHIP;
        gender      = "Male";
        character   = "Something";
        isEgg       = false;
        hatchTime   = 0;
    }

    // Constructs a trainer owned Pokemon with pre-determined stats
    public Pokemon() {

    }

    // Constructs a Pokemon in egg form
    public Pokemon(int num, boolean egg, int steps, Pokedex p) {
        id = num;
        nickname = "Egg";
        curLvl = 1;
        //maxHP = calcHP(p.getBaseHP(id));

        isEgg = true;
        //hatchTime = genRandNum();

    }

    //--------------------------------------------------------------------------
    //----------------------------Getters/Setters-------------------------------
    //--------------------------------------------------------------------------

    public void setName(String n) {
        nickname = n;
    }

    public void setHP(int n) {
        maxHP = n;
    }

    public void setAttack(int n) {
        attack = n;
    }

    public void setDefense(int n) {
        defense = n;
    }

    public void setSpAttack(int n) {
        spAttack = n;
    }

    public void setSpDefense(int n) {
        spDefense = n;
    }

    public void setSpeed(int n) {
        speed = n;
    }

    //--------------------------------------------------------------------------
    //-------------------------------Formulas-----------------------------------
    //--------------------------------------------------------------------------

    // Returns a random number [min, max]
    private int randNum(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max + 1) + min;
    }

    // Returns rounded down stat calculation based on base stat
    // Input:   base - base stat
    private int calcStat(int base, double nMod, int e) {
        return (int) ((base * 2 + iv + (e / 4.0)) * curLvl/100.0 + 5 * nMod); 
    }

    // Returns rounded down HP calculation based on base HP
    // Input:   base - base HP
    private int calcHP(int base, int e) {
        return (int) ((base * 2 + iv + (e/4.0)) * curLvl/100.0 + 10 + curLvl);
    }

    // Explanations on exp gain: 
    // http://bulbapedia.bulbagarden.net/wiki/Experience

    // Returns total experience needed for next level based on Pokemon's growth
    // Input: rate - rate of growth
    //        n    - current level
    private int calcExp(LevelRate rate, int n) {
        switch (rate) {
            case ERRATIC:
                return calcErraticExp(n);
            case FAST:
                return calcFastExp(n);
            case MFAST:
                return calcMfastExp(n);
            case MSLOW:
                return calcMslowExp(n);
            case SLOW:
                return calcSlowExp(n);
            default:
                return calcFluctuateExp(n);
        }
    }

    // Returns experience needed for next lvl for ERRATIC Pokemon
    // Input: n - current level
    private int calcErraticExp(int n) {
        if (n <= 50) {
            return (int) (n * n * n * (100 - n) / 50.0);
        }
        else if (n <= 68) {
            return (int) (n * n * n * (150 - n) / 100.0);
        }
        else if (n <= 98) {
            return (int) (n * n * n * ((1911 - 10 * n) / 3.0) / 500.0);
        }
        else {
            return (int) (n * n * n * (160 - n) / 100.0);
        }
    }

    // Returns experience needed for next lvl for FAST Pokemon
    // Input: n - current level
    private int calcFastExp(int n) {
        return (int) (4 * n * n * n / 5.0);
    }

    // Returns experience needed for next lvl for MFAST Pokemon
    // Input: n - current level
    private int calcMfastExp(int n) {
        return n * n * n;
    }

    // Returns experience needed for next lvl for MSLOW Pokemon
    // Input: n - current level
    private int calcMslowExp(int n) {
        return (int) (6 * n * n * n / 5.0 - 15 * n * n + 100 * n - 140);
    }

    // Returns experience needed for next lvl for SLOW Pokemon
    // Input: n - current level
    private int calcSlowExp(int n) {
        return (int) (5 * n * n * n / 4.0);
    }

    // Returns experience needed for next lvl for FLUCTATE Pokemon
    // Input: n - current level
    private int calcFluctuateExp(int n) {
        if (n <= 15) {
            return (int) (n * n * n * ((n + 1) / 3.0 + 24) / 50.0);
        }
        else if (n <= 36) {
            return (int) (n * n * n * ((n + 14) / 50.0));
        }
        else {
            return (int) (n * n * n * (n / 2.0 + 32) / 50.0);
        }
    }

    //--------------------------------------------------------------------------
    //-----------------------------Inner Classes--------------------------------
    //--------------------------------------------------------------------------

    class Effort {

        private int hpEV;
        private int atkEV;
        private int defEV;
        private int spAtkEV;
        private int spDefEV;
        private int speedEV;

        public Effort() {
            hpEV    = 0;
            atkEV   = 0;
            defEV   = 0;
            spAtkEV = 0;
            spDefEV = 0;
            speedEV = 0;
        }
    }
}