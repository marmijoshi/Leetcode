class Solution {
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxfreq = 0;
        for(int freq : freqMap.values()) {
            maxfreq = Math.max(maxfreq, freq);
        }

        int res = 0;
        for(int freq : freqMap.values()) {
            if(freq == maxfreq) {
                res += freq;
            }
        }

        return res;
    }
}