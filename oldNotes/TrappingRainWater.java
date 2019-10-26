package oldNotes;

/**
 * Trapping Rain Water
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * @author Anil Chowdhury
 * Created on 10/26/2019
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        TrappingRainWater driver = new TrappingRainWater();
        int[] buildings0 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] buildings1 = {7, 0, 4, 2, 5, 0, 6, 4, 0, 5};
        int[] buildings2 = {3, 0, 0, 2, 0, 4};
        int[] buildings3 = {3, 0, 3};
        int[] buildings4 = {0, 4, 5, 1};
        int[] buildings5 = {1, 4, 2, 5, 0, 6, 2, 3, 4};
        System.out.println(driver.maxTrappedWater(buildings0)); //6
        System.out.println(driver.maxTrappedWater(buildings1)); //25
        System.out.println(driver.maxTrappedWater(buildings2)); //10
        System.out.println(driver.maxTrappedWater(buildings3)); //3
        System.out.println(driver.maxTrappedWater(buildings4)); //0
        System.out.println(driver.maxTrappedWater(buildings5)); //10
    }

    private int maxTrappedWater(int[] buildings) {
        if (buildings == null || buildings.length < 3) {
            return 0;
        }
        int maxTrappedWater = 0;
        int leftMax = buildings[0], rightMax = buildings[buildings.length - 1];
        int left = 1, right = buildings.length - 2;

        while (left <= right) {
            leftMax = Math.max(leftMax, buildings[left]);
            rightMax = Math.max(rightMax, buildings[right]);

            if (leftMax < rightMax) {
                maxTrappedWater += leftMax - buildings[left++];
            } else {
                maxTrappedWater += rightMax - buildings[right--];
            }
        }
        return maxTrappedWater;
    }
}
