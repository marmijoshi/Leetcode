class Solution {
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int maxWater = 0;

        while (i < j) {
            int area = (j - i) * Math.min(height[i], height[j]);
            maxWater = Math.max(maxWater, area);

            if(height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxWater;
    }
}