package dev.ikecruz;

public class PermutationsGenerator {
    private int[] indices;
    private boolean hasMore;

    public PermutationsGenerator(int size) {
        indices = new int[size];
        for (int currentIndex = 0; currentIndex < size; currentIndex++) {
            indices[currentIndex] = currentIndex;
        }
        hasMore = true;
    }

    public boolean hasMore() {
        return hasMore;
    }

    public int[] getNext() {
        if (!hasMore) {
            return null;
        }

        int[] result = indices.clone();

        // Find the rightmost element that is smaller than the element to its right
        int smallerRightNeighborIndex = indices.length - 2;
        while (smallerRightNeighborIndex >= 0 && indices[smallerRightNeighborIndex] >= indices[smallerRightNeighborIndex + 1]) {
            smallerRightNeighborIndex--;
        }

        if (smallerRightNeighborIndex < 0) {
            hasMore = false;
        } else {
            // Find the smallest element to the right of i and larger than i
            int largerRightNeighborIndex = indices.length - 1;
            while (indices[largerRightNeighborIndex] <= indices[smallerRightNeighborIndex]) {
                largerRightNeighborIndex--;
            }

            // Swap i and j
            int temp = indices[smallerRightNeighborIndex];
            indices[smallerRightNeighborIndex] = indices[largerRightNeighborIndex];
            indices[largerRightNeighborIndex] = temp;

            // Reverse the elements to the right of i
            int left = smallerRightNeighborIndex + 1;
            int right = indices.length - 1;
            while (left < right) {
                temp = indices[left];
                indices[left] = indices[right];
                indices[right] = temp;
                left++;
                right--;
            }
        }

        return result;
    }
}
