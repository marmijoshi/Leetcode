class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] answer = new int[n - k + 1];
        
        for (int i = 0; i <= n - k; i++) {
            answer[i] = calculateXSum(nums, i, i + k - 1, x);
        }
        
        return answer;
    }
    
    private int calculateXSum(int[] nums, int start, int end, int x) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = start; i <= end; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }
        
        if (freqMap.size() < x) {
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += nums[i];
            }
            return sum;
        }
        
        List<int[]> elements = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            elements.add(new int[]{entry.getKey(), entry.getValue()});
        }
        
        Collections.sort(elements, (a, b) -> {
            if (a[1] != b[1]) {
                return b[1] - a[1];
            }
            return b[0] - a[0];
        });
        
        int xSum = 0;
        for (int i = 0; i < x && i < elements.size(); i++) {
            int value = elements.get(i)[0];
            int freq = elements.get(i)[1];
            xSum += value * freq;
        }
        
        return xSum;
    }
}