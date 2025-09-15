class Solution {
    List<String> res = new ArrayList<>();
    Set<String> set = new HashSet<>();
    
    int getMinValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(ch == '(') {
                stack.push(ch);
            } else if(ch == ')') {
                if(stack.size() > 0 && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(')');
                }
            }
        }
        return stack.size();
    }

    void solve(String s, int minInv) {
        if(set.contains(s)) return;
        else set.add(s);

        if(minInv < 0) {
            return;
        }
        if(minInv == 0) {
            if(getMinValid(s) == 0) {
                res.add(s);
            }
            return;
        }
        for(int i = 0; i < s.length(); i++) {
            String left = s.substring(0, i);
            String right = s.substring(i + 1);
            solve(left + right, minInv - 1);
        }
    }

    public List<String> removeInvalidParentheses(String s) {
        solve(s, getMinValid(s));
        return res;
    }
}