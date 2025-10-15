class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        
        int[] incLen = new int[n];
        incLen[0] = 1;
        
        for (int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                incLen[i] = incLen[i - 1] + 1;
            } else {
                incLen[i] = 1;
            }
        }
        
        int[] startLen = new int[n];
        startLen[n - 1] = 1;
        
        for (int i = n - 2; i >= 0; i--) {
            if (nums.get(i) < nums.get(i + 1)) {
                startLen[i] = startLen[i + 1] + 1;
            } else {
                startLen[i] = 1;
            }
        }
        
        int maxK = 0;
        
        for (int i = 0; i < n - 1; i++) {
            int len1 = incLen[i];
            int len2 = startLen[i + 1];
            
            int k = Math.min(len1, len2);
            maxK = Math.max(maxK, k);
        }
        
        for (int i = 0; i < n; i++) {
            int len = incLen[i];
            maxK = Math.max(maxK, len / 2);
        }
        
        return maxK;
    }
}