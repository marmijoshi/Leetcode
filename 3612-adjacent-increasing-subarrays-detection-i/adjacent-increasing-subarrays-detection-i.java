class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        
        // Check each possible starting position for the first subarray
        for (int a = 0; a <= n - 2 * k; a++) {
            int b = a + k;
            
            // Check if subarray [a, a+k-1] is strictly increasing
            if (isStrictlyIncreasing(nums, a, a + k - 1) && 
                isStrictlyIncreasing(nums, b, b + k - 1)) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean isStrictlyIncreasing(List<Integer> nums, int start, int end) {
        for (int i = start; i < end; i++) {
            if (nums.get(i) >= nums.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}