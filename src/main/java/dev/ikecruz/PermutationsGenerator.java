package dev.ikecruz;

public class PermutationsGenerator {
    private int[] indices;
    private boolean hasMore;

    public PermutationsGenerator(int size) {
        indices = new int[size];
        for (int i = 0; i < size; i++) {
            indices[i] = i;
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
        int i = indices.length - 2;
        while (i >= 0 && indices[i] >= indices[i + 1]) {
            i--;
        }

        if (i < 0) {
            hasMore = false;
        } else {
            // Find the smallest element to the right of i and larger than i
            int j = indices.length - 1;
            while (indices[j] <= indices[i]) {
                j--;
            }

            // Swap i and j
            int temp = indices[i];
            indices[i] = indices[j];
            indices[j] = temp;

            // Reverse the elements to the right of i
            int left = i + 1;
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
