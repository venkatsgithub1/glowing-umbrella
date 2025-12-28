package io.learn.binsearch;

import java.io.PrintWriter;

public class SearchA2DMatrix {
    public static void main(String[] args) {
        assert searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3) : "Not matching expected result";
        assert !searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13) : "Not matching expected result";
    }

    private static boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (target > matrix[i][matrix[i].length - 1]) {
                    continue;
                }
                int low = 0;
                int high = matrix[i].length - 1;
                while (low <= high) {
                    int mid = (low + (high - low) / 2);
                    if (matrix[i][mid] == target) {
                        return true;
                    } else if (matrix[i][mid] > target) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
                return false;
            }
        }

        return false;
    }
}
