class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exact = new HashSet<>();
        Map<String, String> capital = new HashMap<>();
        Map<String, String> vowel = new HashMap<>();

        for(String word : wordlist) {
            exact.add(word);
            
            String lower = word.toLowerCase();
            capital.putIfAbsent(lower, word);

            String vowelPattern = getVowelPattern(lower);

            vowel.putIfAbsent(vowelPattern, word);
        }

        String[] res = new String[queries.length];
        for(int i=0; i<queries.length; i++) {
            String query = queries[i];

            if(exact.contains(query)) {
                res[i] = query;
                continue;
            }

            String lowerQuery = query.toLowerCase();

            if(capital.containsKey(lowerQuery)) {
                res[i] = capital.get(lowerQuery);
                continue;
            }

            String vowelPattern = getVowelPattern(lowerQuery);
            if(vowel.containsKey(vowelPattern)) {
                res[i] = vowel.get(vowelPattern);
                continue;
            }

            res[i] = "";
        }
        return res;
    }

    private String getVowelPattern(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (isVowel(c)) {
                sb.append('*');
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}