class Solution {
    private String s;
    private int k;
    private int n;
    private Map<String, Integer> memo;
    
    public int maxPartitionsAfterOperations(String s, int k) {
        this.s = s;
        this.k = k;
        this.n = s.length();
        this.memo = new HashMap<>();
        
        return solve(0, 0, false);
    }
    
    private int solve(int pos, int mask, boolean changed) {
        if (pos == n) {
            return mask == 0 ? 0 : 1;
        }
        
        String key = pos + "," + mask + "," + changed;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        int result = 0;
        
        // Option 1: Keep original character
        int charBit = 1 << (s.charAt(pos) - 'a');
        int newMask = mask | charBit;
        
        if (Integer.bitCount(newMask) <= k) {
            result = solve(pos + 1, newMask, changed);
        } else {
            result = 1 + solve(pos + 1, charBit, changed);
        }
        
        // Option 2: Change character (if not changed yet)
        if (!changed) {
            // Must try ALL 26 characters to find optimal solution
            for (int c = 0; c < 26; c++) {
                int newCharBit = 1 << c;
                int changedMask = mask | newCharBit;
                
                int subResult;
                if (Integer.bitCount(changedMask) <= k) {
                    subResult = solve(pos + 1, changedMask, true);
                } else {
                    subResult = 1 + solve(pos + 1, newCharBit, true);
                }
                result = Math.max(result, subResult);
            }
        }
        
        memo.put(key, result);
        return result;
    }
}