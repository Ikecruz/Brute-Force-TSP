package dev.ikecruz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static ArrayList<City> readCitiesFromFile(String filePath) throws IOException, FileNotFoundException {

        ArrayList<City> cities = new ArrayList<>();

        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] lineDivided = line.replaceAll("[\t ]+", " ").trim().split(" ");
            
            int id = Integer.parseInt(lineDivided[0]);
            int x = Integer.parseInt(lineDivided[1]);
            int y = Integer.parseInt(lineDivided[2]);

            cities.add(new City(id, x, y));

        }

        bufferedReader.close();

        return cities;
    }

    public static double euclideanDistance (City city1, City city2) {
        int differenceOfX = city1.x - city2.x;
        int differenceOfY = city1.y- city2.y;

        return Math.sqrt((differenceOfX * differenceOfX) + (differenceOfY * differenceOfY));
    }

    /**
     * The function calculates the total distance of a given path by summing up the Euclidean distances
     * between consecutive cities in the path and also accounting for the distance between the last
     * city and the first city.
     * 
     * @param path An ArrayList of City objects representing a path or route.
     * @return The method is returning the total distance of the given path, which is a double value.
     */
    public static double calculatePathDistance (ArrayList<City> path) {
        double totalDistance = 0;

        for (int index = 0; index < path.size() - 1; index++) {
            totalDistance += euclideanDistance(path.get(index), path.get(index + 1));
        }

        // MOVE BACK TO START POINT
        totalDistance += euclideanDistance(path.get(path.size() - 1), path.get(0));

        return totalDistance;

    }

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
            System.out.println(city.number + " (" + city.x + ", " + city.y + ")");
        }
        System.out.println("Total Distance: " + shortestDistance);

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ArrayList<City> cities = readCitiesFromFile("test-files/train1.txt");
        long start = System.nanoTime();
        travellingSalesman(cities);
        long stop = System.nanoTime();

        System.out.println("Runtime is: " + (stop - start) + " nano seconds");
    }
}