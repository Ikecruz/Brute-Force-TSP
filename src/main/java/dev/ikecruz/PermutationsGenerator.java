package dev.ikecruz;

/**
 * A class for generating permutations of indices for a given size.
 * This class provides a method to retrieve the next permutation in lexicographic order.
 * It is designed to be used iteratively until all permutations are exhausted.
 *
 * <p>The algorithm used is based on finding the next lexicographically greater permutation.
 * It is commonly used to generate permutations for various purposes, such as combinatorial problems.
 *
 * @author Onyeka Ikedinobi
 * @version 1.0
 */

public class PermutationsGenerator {
    private int[] indices;
    private boolean hasMore;

    /**
     * Constructs a new PermutationsGenerator with the specified size.
     *
     * @param size The size of the permutation (the number of indices).
     */
    public PermutationsGenerator(int size) {
        indices = new int[size];
        for (int currentIndex = 0; currentIndex < size; currentIndex++) {
            indices[currentIndex] = currentIndex;
        }
        hasMore = true;
    }

    /**
     * The function returns a boolean value indicating whether there is more data available.
     * 
     * @return The method is returning a boolean value.
     */
    public boolean hasMore() {
        return hasMore;
    }

    /**
     * This function generates the next permutation of an array of integers.
     * 
     * @return The method is returning an array of integers.
     */
    public int[] getNext() {
        if (!hasMore) {
            return null;
        }

        // Clone the current state as the result
        int[] result = indices.clone();

        // Find the rightmost element that is smaller than the element to its right
        int smallerRightNeighborIndex = findSmallerRightNeighborIndex();
        if (smallerRightNeighborIndex < 0) {
            // No more permutations can be generated
            hasMore = false;
        } else {
            int largerRightNeighborIndex = findLargerRightNeighborIndex(smallerRightNeighborIndex);

            swapIndices(smallerRightNeighborIndex, largerRightNeighborIndex);

            reverseIndicesToRight(smallerRightNeighborIndex + 1, indices.length - 1);
        }

        return result;
    }

    /**
     * The function finds the index of the first element in an array that is smaller than its right
     * neighbor.
     * 
     * @return The method is returning the index of the smaller right neighbor.
     */
    private int findSmallerRightNeighborIndex() {
        int currentIndex = indices.length - 2;
        while (currentIndex >= 0 && indices[currentIndex] >= indices[currentIndex + 1]) {
            currentIndex--;
        }
        return currentIndex;
    }

    /**
     * The function finds the index of the larger right neighbor element given the index of a smaller
     * right neighbor element in an array.
     * 
     * @param smallerRightNeighborIndex The parameter "smallerRightNeighborIndex" represents the index
     * of the smaller right neighbor in indices
     * @return The method is returning the index of the larger right neighbor of the element at the
     * given smaller right neighbor index.
     */
    private int findLargerRightNeighborIndex(int smallerRightNeighborIndex) {
        int largerRightNeighborIndex = indices.length - 1;
        while (indices[largerRightNeighborIndex] <= indices[smallerRightNeighborIndex]) {
            largerRightNeighborIndex--;
        }
        return largerRightNeighborIndex;
    }

    /**
     * The function swaps the values at two given indices in an array.
     * 
     * @param firstIndex The index of the first element to be swapped.
     * @param secondIndex The secondIndex parameter is an integer that represents the index of the
     * second element in the indices array.
     */
    private void swapIndices(int firstIndex, int secondIndex) {
        int temp = indices[firstIndex];
        indices[firstIndex] = indices[secondIndex];
        indices[secondIndex] = temp;
    }

    // Reverses the elements in the range [left, right]

   /**
    * The function reverses the order of elements in the indices array from the left index to the right index.
    * 
    * @param left The left parameter represents the starting index of the indices array
    * @param right The "right" parameter represents the index of the rightmost element in the indices array
    */
    private void reverseIndicesToRight(int left, int right) {
        while (left < right) {
            int temp = indices[left];
            indices[left] = indices[right];
            indices[right] = temp;
            left++;
            right--;
        }
    }
}
