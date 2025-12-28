package io.learn.binsearch;

public class FindMinInRotatedSortedArray {
    public static void main(String[] args) {
        assert find(new int[]{4,5,6,7,0,1,2,3}) == 0:"Got wrong number as min";
    }

    private static int find(int[] nums) {
        // [4,5,6,7,0,1,2,3] 0 at 4
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = (low + (high - low) / 2);
            if (nums[mid]<=nums[high]) {
                high = mid;
            } else if (nums[mid] >= nums[high]) {
                low = mid + 1;
            }
        }
        return nums[low];
    }
}
