class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        
        Map<Integer, Integer> fullLakes = new HashMap<>();
        
        TreeSet<Integer> dryDays = new TreeSet<>();
        
        for (int i = 0; i < n; i++) {
            if (rains[i] > 0) {
                int lake = rains[i];
                
                if (fullLakes.containsKey(lake)) {
                    int lastRainDay = fullLakes.get(lake);
                    
                    Integer dryDay = dryDays.higher(lastRainDay);
                    
                    if (dryDay == null || dryDay >= i) {
                        return new int[0];
                    }
                    
                    ans[dryDay] = lake;
                    dryDays.remove(dryDay);
                }
                
                fullLakes.put(lake, i);
                ans[i] = -1;
                
            } else {
                dryDays.add(i);
            }
        }
        
        for (int dryDay : dryDays) {
            ans[dryDay] = 1;
        }
        
        return ans;
    }
}