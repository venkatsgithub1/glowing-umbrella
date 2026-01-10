package io.learn.lists;

public class FindDuplicateNumber {
    public static void main(String[] args) {
        assert find(new int[]{3, 3, 3}) == 3 : "Got wrong num";
        assert find(new int[]{1, 3, 4, 2, 2}) == 2 : "Got wrong num";
    }

    private static int find(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != nums[nums[i] - 1]) {
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[temp - 1] = temp;
            } else {
                i++;
            }
        }
        return nums[nums.length - 1];
    }
}
