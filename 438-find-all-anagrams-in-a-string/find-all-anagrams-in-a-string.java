class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] pCount = new int[26];
        int[] sCount = new int[26];
        
        List<Integer> res = new ArrayList<>();

        // Count frequency of characters in p
        for(char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        // Sliding window over s
        for(int i=0; i<s.length(); i++){
            sCount[s.charAt(i) - 'a']++;

            if(i >= p.length()) {
                sCount[s.charAt(i - p.length()) - 'a']--;
            }

            if(Arrays.equals(pCount, sCount)) {
                res.add(i - p.length() + 1);
            }
        }
        
        return res;
    }
}