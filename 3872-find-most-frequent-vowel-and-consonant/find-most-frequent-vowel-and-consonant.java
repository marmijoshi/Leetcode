class Solution {
    public int maxFreqSum(String s) {
        int[] freq = new int[26];

        for(char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        boolean[] isVowel = new boolean[26];
        isVowel[0] = true;
        isVowel[4] = true;  
        isVowel[8] = true; 
        isVowel[14] = true;
        isVowel[20] = true;

        int maxVowelFreq = 0;
        int maxConsonantFreq = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                if (isVowel[i]) {
                    maxVowelFreq = Math.max(maxVowelFreq, freq[i]);
                } else {
                    maxConsonantFreq = Math.max(maxConsonantFreq, freq[i]);
                }
            }
        }
        
        return maxVowelFreq + maxConsonantFreq;
    }
}