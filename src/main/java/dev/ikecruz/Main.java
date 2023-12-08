package dev.ikecruz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Main class for the Traveling Salesman Problem solution.
 * This program addresses the Traveling Salesman Problem, where the goal is to find
 * the shortest possible path that visits each city exactly once and returns to the starting city.
 * 
 * The solution is obtained using a brute-force approach that generates all possible permutations
 * of cities and calculates the distance for each permutation.
 * The result, including the shortest path and its distance, is then outputted along with the runtime.
 *
 * Note: This program assumes the input file format is structured with each line representing a city
 * and containing the city number, X-coordinate, and Y-coordinate separated by spaces or tabs.
 *
 * @author Onyeka Ikedinobi
 * @version 1.0
 */
public class Main {

    static final String FILENAME = "test-files/train1.txt";
    // static final String FILENAME = "test-files/train2.txt";
    // static final String FILENAME = "test-files/train3.txt";
    // static final String FILENAME = "test-files/sample1-22.txt";
    // static final String FILENAME = "test-files/sample2-22.txt";
    // static final String FILENAME = "test-files/sample3-22.txt";
    // static final String FILENAME = "test-files/sample4-22.txt";

    /**
     * The function reads city data from a file, parses it, and returns an ArrayList of City objects.
     * 
     * @param filePath The filePath parameter is a String that represents the path to the file with cities.
     * @return The method is returning an ArrayList of City objects.
     * @throws IOException        If an I/O error occurs while reading the file.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static ArrayList<City> readCitiesFromFile(String filePath) throws IOException, FileNotFoundException {

        ArrayList<City> cities = new ArrayList<>();

        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] lineParts = line.replaceAll("[\t ]+", " ").trim().split(" ");
            
            int cityId = Integer.parseInt(lineParts[0]);
            int cityXCoordinate = Integer.parseInt(lineParts[1]);
            int cityYCoordinate = Integer.parseInt(lineParts[2]);

            cities.add(new City(cityId, cityXCoordinate, cityYCoordinate));
        }

        bufferedReader.close();

        return cities;
    }

    /**
     * The function calculates the Euclidean distance between two cities based on their coordinates.
     * 
     * @param city1 The first city object, which contains the x and y coordinates of the city.
     * @param city2 The second city object, which contains the x and y coordinates of the second city.
     * @return The method is returning the Euclidean distance between two cities.
     */
    public static double calculateEuclideanDistance (City city1, City city2) {
        int differenceOfXCoordinates = city1.xCoordinate - city2.xCoordinate;
        int differenceOfYCoordinates = city1.yCoordinate- city2.yCoordinate;

        return Math.sqrt((differenceOfXCoordinates * differenceOfXCoordinates) + (differenceOfYCoordinates * differenceOfYCoordinates));
    }

    /**
     * The function calculates the total distance of a given path by summing up the Euclidean distances
     * between consecutive cities.
     * 
     * @param path An ArrayList of City objects representing a path or route.
     * @return The method is returning the total distance of the given path, which is a double value.
     */
    public static double calculatePathDistance (ArrayList<City> path) {
        double totalDistance = 0;

        // calculating the distance between two consecutive ignoring the last city
        for (int index = 0; index < path.size() - 1; index++) {
            totalDistance += calculateEuclideanDistance(path.get(index), path.get(index + 1));
        }

        // Move back to the starting city from the last city
        totalDistance += calculateEuclideanDistance(path.get(path.size() - 1), path.get(0));

        return totalDistance;

    }

    /**
     * The function calculates the shortest path and distance for a given list of cities using the
     * brute force approach of generating all possible permutations.
     * 
     * @param cities This is an ArrayList of City objects representing cities to be visited. 
     */
    public static void travellingSalesman (ArrayList<City> cities) {
        ArrayList<City> bestPath = null;
        double shortestDistance = Double.MAX_VALUE;

        PermutationsGenerator permutation = new PermutationsGenerator(cities.size());

        while (permutation.hasMore()) {
            ArrayList<City> path = new ArrayList<>();

            for (int cityNumber: permutation.getNext()) {
                path.add(cities.get(cityNumber));
            }

            double distance = calculatePathDistance(path);

            if (distance < shortestDistance) {
                shortestDistance = distance;
                bestPath = path;
            }
        }     
        
        System.out.println("Shortest Path:");
        for (City city : bestPath) {
            System.out.println(city.id + " (" + city.xCoordinate + ", " + city.yCoordinate + ")");
        }
        System.out.println("Total Distance: " + shortestDistance);

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ArrayList<City> cities = readCitiesFromFile(FILENAME);
        long start = System.nanoTime();
        travellingSalesman(cities);
        long stop = System.nanoTime();

        System.out.println("Runtime is: " + (stop - start) + " nano seconds");
    }
}