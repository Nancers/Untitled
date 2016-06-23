/* A Pokemon class. Stores information about a Pokemon.
 */

import java.util.*;
import java.io.*;

public class Pokemon {
    private int      number;         // # on the regional Pokedex
    private String   nickname;       // Name given by trainer, if any
    private int      maxHP;          // Max health 
    private int      curHP;          // Current health
    private String[] stats;          // Stats inflicted (can you have multiple?)
    private Move[]   moves;          // Up to 4 moves
    private String   hiddenAbility;  // Hidden ability
    private String   heldItem;       // Need a parent/abstract class of items?
    private int      curLvl;         // Current level
    private int      curExp;         // Current exp gained for current level
    private int      totalExp;       // Exp needed for next level

    // Other Pokemon stats
    private int      attack;         // current attack value
    private int      defense;        // current defense
    private int      spAttack;       // current special attack
    private int      spDefense;      // current special defense
    private int      speed;          // current speed
    private int      friendship;     // current friendship
    private String   gender;         // male or female
    private String   nature;         // used to calculated stat growth
    private String   character;      // used to calculate stat growth
    private int      iv;             // individual value
    private int      ev;             // effort value
    /* TODO: you can add all the ev/iv stuff :D
     * also not sure how exp gain is calculated */

    // For unhatched Pokemon.....do we need a separate class for this?
    private boolean  isEgg;          // If Pokemon is in egg form
    private int      hatchTime;      // Steps left to hatch


}