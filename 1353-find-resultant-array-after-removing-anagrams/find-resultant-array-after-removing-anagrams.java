class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> result = new ArrayList<>();
        String prevSignature = "";

        for (String word : words) {
            String signature = getSignature(word);

            if (!signature.equals(prevSignature)) {
                result.add(word);
                prevSignature = signature;
            }
        }

        return result;
    }

    private String getSignature(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}