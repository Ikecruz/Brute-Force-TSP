package dev.ikecruz;

/**
 * Represents a city with an ID, X-coordinate, and Y-coordinate.
 * This class is used to model cities in a geographic context, such as in the Traveling Salesman Problem (TSP).
 *
 * @author Onyeka Ikedinobi
 * @version 1.0
 */
public class City {
    /**
     * The unique identifier of the city.
     */
    int id;

    /**
     * The X-coordinate of the city in a 2D plane.
     */
    int xCoordinate;

    /**
     * The Y-coordinate of the city in a 2D plane.
     */
    int yCoordinate;

    /**
     * Constructs a new City with the specified ID, X-coordinate, and Y-coordinate.
     *
     * @param id           The unique identifier of the city.
     * @param xCoordinate The X-coordinate of the city.
     * @param yCoordinate The Y-coordinate of the city.
     */
    public City(int id, int xCoordinate, int yCoordinate) {
        this.id = id;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
}
