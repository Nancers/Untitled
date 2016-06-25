/* Keeps track of the nature modification values for a specific nature.
 * Nature modification values are either 0.9, 1.0, or 1.1.
 */

import java.util.*;

public class Nature {

    private String name;
    private double atkM;   // Atk mod value
    private double defM;   // Defense mod value
    private double sAtkM; // Special attack mod value
    private double sDefM; // Special defense mod value
    private double speedM; // Speed mod value

    public Nature(String n, double a, double d, double sa, double sd, double s) {

        name   = n;
        atkM   = a;
        defM   = d;
        sAtkM  = sa;
        sDefM  = sd;
        speedM = s;
    }

    public double getAtkM() {
        return atkM;
    }

    public double getDefM() {
        return defM;
    }

    public double getSAtkM() {
        return sAtkM;
    }

    public double getSDefM() {
        return sDefM;
    }

    public double getSpeedM() {
        return speedM;
    }
}