package io.learn.binsearch;

public class BinarySearch {
    public static void main(String[] args) {
        assert search(new int[]{1, 2, 3, 4, 5, 6, 7}, 6) == 5 : "Got a different index as result";
        assert search(new int[]{1, 2, 3, 4, 5, 6, 7}, 9) == -1 : "Got a different index as result";
    }

    private static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + (high - low) / 2);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
