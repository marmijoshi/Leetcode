class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;

        char[] srr = s.toCharArray();
        char[] trr = t.toCharArray();

        Arrays.sort(srr);
        Arrays.sort(trr);

        return Arrays.equals(srr, trr);
    }
}