// Not sure if it's better to use hashmap or hashtable for efficiency

import java.util.*;
import java.io.*;

public class PokemonMap {

    public static HashMap<String, String[]> fillMap(String locF, String mapF) {

        //Part 1: Populate array of locations
        String[] locations            = null;
        HashMap<String, String[]> map = new HashMap<String, String[]>();

        // Read through locations.txt to make an array of locations
        try {

            FileReader file       = new FileReader(locF);
            BufferedReader buffer = new BufferedReader(file);
            String nextLine       = buffer.readLine();
            StringBuffer locs     = new StringBuffer();

            while (nextLine != null) {

                locs.append(nextLine + " ");
                nextLine = buffer.readLine();
            }

            buffer.close();

            locations = locs.toString().split(" ");
        }
        catch(FileNotFoundException e) {

            System.out.println(locF + " not found in directory. \n");
        }
        catch(IOException e) {

            System.out.println("Error reading " + locF + "\n");
        }

        // Part 2: Read through map.txt to get mapping of locations
        try {

            FileReader file       = new FileReader(mapF);
            BufferedReader buffer = new BufferedReader(file);
            String nextLine       = buffer.readLine();
            StringBuffer locs     = new StringBuffer();

            while (nextLine != null) {

                String[] indices = nextLine.split(" ");
                String key    = locations[Integer.parseInt(indices[0])];
                String[] vals = new String[indices.length-1];

                for (int i=1; i<indices.length; i++) {

                    vals[i-1] = locations[Integer.parseInt(indices[i])];
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

    public static void main(String[] args) {

        HashMap<String, String[]> kanto = fillMap("locations.txt", "map.txt");
    }
}