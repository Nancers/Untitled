import java.util.*;
import java.io.*;
import java.lang.*;

public class Naturedex {

    public static final int NUM_NATURE = 25; // # of possible natures in game

    private String[] name;
    private double[] atkM;
    private double[] defM;
    private double[] sAtkM;
    private double[] sDefM;
    private double[] speedM;

    public Naturedex(String natureFile) {

        name   = new String[NUM_NATURE];
        atkM   = new double[NUM_NATURE];
        defM   = new double[NUM_NATURE];
        sAtkM  = new double[NUM_NATURE];
        sDefM  = new double[NUM_NATURE];
        speedM = new double[NUM_NATURE];

        fillDex(natureFile);
    }

    public Nature getRandNature() {
        Random rand = new Random();
        int n = rand.nextInt(NUM_NATURE+1);

        return new Nature(name[n], atkM[n], defM[n], sAtkM[n], sDefM[n], speedM[n]);
    }

    private void fillDex(String f) {

        // Read through nature.csv to fill in Naturedex
        try {
            FileReader     file     = new FileReader(f);
            BufferedReader buffer   = new BufferedReader(file);
            String         nextLine = buffer.readLine();
            int            i        = 0;

            while (nextLine != null) {
                nextLine = buffer.readLine();
                String[] values = nextLine.split(",");

                name[i]   = values[0];
                atkM[i]   = Double.parseDouble(values[1]);
                defM[i]   = Double.parseDouble(values[2]);
                sAtkM[i]  = Double.parseDouble(values[3]);
                sDefM[i]  = Double.parseDouble(values[4]);
                speedM[i] = Double.parseDouble(values[5]);
                i++;
            }

            buffer.close();
        }
        catch(FileNotFoundException e) {
            System.out.println(f + " not found in directory. \n");
        }
        catch(IOException e) {
            System.out.println("Error reading " + f + "\n");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Too many natures in " + f + ". Consider changing NUM_NATURE value in Naturedex.java");
        }
    }
}