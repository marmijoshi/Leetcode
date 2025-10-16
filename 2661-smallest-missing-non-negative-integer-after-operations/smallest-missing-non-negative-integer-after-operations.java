class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int[] count = new int[value];
        
        for (int num : nums) {
            int remainder = ((num % value) + value) % value;
            count[remainder]++;
        }
        
        int mex = 0;
        while (true) {
            int neededRemainder = mex % value;
            if (count[neededRemainder] == 0) {
                return mex;
            }
            count[neededRemainder]--;
            mex++;
        }
    }
}