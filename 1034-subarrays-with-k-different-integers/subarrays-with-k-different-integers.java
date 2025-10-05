class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atmostK(nums, k) - atmostK(nums, k-1);
    }
    public int atmostK(int[] nums, int k) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        int start = 0, end = 0, count  = 0, n = nums.length;

        while(end < n) {
            // expand
            freqMap.put(nums[end], freqMap.getOrDefault(nums[end], 0) + 1);

            // shrink
            while(freqMap.size() > k) {
                freqMap.put(nums[start], freqMap.get(nums[start]) - 1);  
                if(freqMap.get(nums[start]) == 0){
                    freqMap.remove(nums[start]);
                }
                start++;  
            }
            count = count + (end-start+1);
            end++;
        }
        return count;
    }
}