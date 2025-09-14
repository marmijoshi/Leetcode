class Solution {
    public String removeOuterParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder ans = new StringBuilder();

        for(char c : s.toCharArray()) {
            if(stack.isEmpty() && c=='('){
                stack.push(c);
            } else if(!stack.isEmpty() && c=='(') {
                stack.push(c);
                ans.append(c);
            } else if(!stack.isEmpty() && c==')') {
                stack.pop();
                if(!stack.isEmpty()) {
                    ans.append(c);
                }
            }
        }
        return ans.toString();
    }
}