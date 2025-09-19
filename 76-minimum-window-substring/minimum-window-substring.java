class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        
        // Count characters in target string t
        for(int i = 0; i < t.length(); i++){
            char ch = t.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int unique = map.size();
        int start = -1;
        int windStart = 0;
        int windEnd = 0;
        int minlen = Integer.MAX_VALUE;
        int N = s.length();

        while(windEnd < N) {
            // Expansion - get character from source string s
            char ch = s.charAt(windEnd);
            if(map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1);
                if(map.get(ch) == 0) {
                    unique--;
                }
            }

            // Shrink window when all characters are found
            while(unique == 0){
                int len = windEnd - windStart + 1;
                if(len < minlen) {
                    minlen = len;
                    start = windStart;
                }
                
                // Get character from source string s
                ch = s.charAt(windStart);
                if(map.containsKey(ch)) {
                    map.put(ch, map.get(ch) + 1);
                    if(map.get(ch) > 0) {
                        unique++;
                    }
                }
                windStart++;
            }

            windEnd++;
        }
        
        if(start == -1){
            return "";
        } else {
            return s.substring(start, start + minlen);
        }
    }
}