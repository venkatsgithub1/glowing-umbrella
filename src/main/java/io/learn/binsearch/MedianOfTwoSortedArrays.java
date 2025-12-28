package io.learn.binsearch;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        assert find(new int[]{1, 3}, new int[]{2}) == 2.0: "Correct median is not found";
        assert find(new int[]{}, new int[]{1}) == 1.0: "Correct median is not found";
        assert find(new int[]{100}, new int[]{101}) == 100.5: "Correct median is not found";
    }

    private static double find(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            return find(nums2, nums1);
        }
        int totalLen = m + n;
        int leftHalfOfWholeArray = (totalLen + 1) / 2; // + 1 to get consistent left half for odd / even

        // search space
        int low = 0;
        int high = m;

        while (low <= high) {
            int partitionM = (low + high) / 2;
            int partitionN = leftHalfOfWholeArray - partitionM;

            int leftM = partitionM <= 0 ? Integer.MIN_VALUE : nums1[partitionM - 1];
            int leftN = partitionN <= 0 ? Integer.MIN_VALUE : nums2[partitionN - 1];
            int rightM = partitionM >= m ? Integer.MAX_VALUE : nums1[partitionM];
            int rightN = partitionN >= n ? Integer.MAX_VALUE : nums2[partitionN];

            if (leftM <= rightN && leftN <= rightM) {
                if (totalLen % 2 == 0) {
                    return (Integer.max(leftM, leftN) + Integer.min(rightM, rightN)) / 2.0;
                } else {
                    return Integer.max(leftM, leftN);
                }
            } else if (leftM > rightN) {
                // move search space to end at left of mid
                high = partitionM - 1;
            } else {
                // move search space to start from right of mid
                low = partitionM + 1;
            }
        }
        return 0.0;
    }
}
