/* Keeps track of the nature modification values for a specific nature.
 * Nature modification values are either 0.9, 1.0, or 1.1.
 */

import java.util.*;

public class Nature {

    private String name;
    private double attackMod;
    private double defenseMod;
    private double spAttackMod;
    private double spDefenseMod;
    private double speedMod;

    // Read CSV file of all the natures and corresponding mods for stats
    public Nature() {

    }

    public double getAttackMod() {
        return attackMod;
    }

    public double getDefenseMod() {
        return defenseMod;
    }

    public double getSpAttackMod() {
        return spAttackMod;
    }

    public double getSpDefenseMod() {
        return spDefenseMod;
    }

    public double getSpeedMod() {
        return speedMod;
    }
}