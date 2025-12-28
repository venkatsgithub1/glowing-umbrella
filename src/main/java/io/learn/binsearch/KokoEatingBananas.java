package io.learn.binsearch;

import java.util.Arrays;

public class KokoEatingBananas {
    public static void main(String[] args) {
        assert minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 8) == 15 : "Got wrong eating speed";
        assert minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5) == 30 : "Got wrong eating speed";
        assert minEatingSpeed(new int[]{3, 6, 7, 11}, 8) == 4 : "Got wrong eating speed";
    }

    public static int minEatingSpeed(int[] piles, int h) {
        // Sort the piles
        Arrays.sort(piles);
        int optimalSpeed = Integer.MAX_VALUE;
        int lowSpeed = 1; // Speed is eating 1 banana per hour.
        int highSpeed = piles[piles.length - 1]; // Speed is eating highest number of bananas in piles array in an hour.

        // search space is between lowSpeed and highSpeed
        while (lowSpeed <= highSpeed) {
            int midSpeed = (lowSpeed + (highSpeed - lowSpeed) / 2);
            int hoursTakenToEat = 0;

            // check midSpeed on piles
            for (int pile : piles) {
                hoursTakenToEat += (pile % midSpeed == 0) ? (pile / midSpeed) : (pile / midSpeed) + 1;
                if (hoursTakenToEat > h) {
                    // means it took koko more than h hours to eat piles till here
                    // guards will return, koko needs to eat fast (low should be high speed)
                    lowSpeed = midSpeed + 1;
                    break;
                }
            }

            if (hoursTakenToEat <= h) {
                // koko is able to eat in h hours, but can we eat any slower and still eat within h hours?
                // koko can eat slowly
                highSpeed = midSpeed - 1;
                optimalSpeed = Integer.min(optimalSpeed, midSpeed);
            }
        }

        return optimalSpeed;
    }
}
