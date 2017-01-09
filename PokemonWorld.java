/*
 * This theoretically should create the map/environment of the Pokemon game.
 * Setting based off of California.
 */

import java.util.*;
import java.io.*;
import java.lang.*;

public class PokemonWorld {

    public static HashMap<String, String[]> fillWorld(String locF, String mapF) {
        //Part 1: Populate array of locations
        ArrayList<String> locations   = new ArrayList<String>();
        HashMap<String, String[]> map = new HashMap<String, String[]>();

        // Read through locations.txt to make an array of locations
        try {
            FileReader file       = new FileReader(locF);
            BufferedReader buffer = new BufferedReader(file);
            String nextLine       = buffer.readLine();

            while (nextLine != null) {
                // Skip empty lines
                if (nextLine.length() > 0) {
                    locations.add(nextLine);
                }

                nextLine = buffer.readLine();
            }

            buffer.close();
        }
        catch(FileNotFoundException e) {
            System.out.println(locF + " not found in directory. \n");
        }
        catch(IOException e) {
            System.out.println("Error reading " + locF + "\n");
        }

        // Create mapping relationship of locations from map.txt
        try {
            FileReader file       = new FileReader(mapF);
            BufferedReader buffer = new BufferedReader(file);
            String nextLine       = buffer.readLine();
            StringBuffer locs     = new StringBuffer();

            while (nextLine != null) {
                String[] indices = nextLine.split(" ");
                String key       = locations.get(Integer.parseInt(indices[0]));
                String[] vals    = new String[indices.length-1];

                for (int i=1; i<indices.length; i++) {
                    vals[i-1] = locations.get(Integer.parseInt(indices[i]));
                }

                map.put(key, vals);
                nextLine = buffer.readLine();
            }

            buffer.close();
        }
        catch(FileNotFoundException e) {
            System.out.println(mapF + " not found in directory. \n");
        }
        catch(IOException e) {
            System.out.println("Error reading " + mapF + "\n");
        }

        return map;
    }

    // For testing purposes
    public static void main(String[] args) {
        HashMap<String, String[]> kanto = fillWorld("locations.txt", "map.txt");

        // Printing out map
        for (String key : kanto.keySet()) {
            System.out.print(key + ": [");

            for (String loc : kanto.get(key)) {
                System.out.print(loc + ", ");
            }

            System.out.println("]");
        }
    }
}