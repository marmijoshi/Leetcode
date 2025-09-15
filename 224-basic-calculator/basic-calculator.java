class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        int num = 0;
        int sign = 1;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '+') {
                sum += sign * num;
                num = 0;
                sign = 1;
            } else if (c == '-') {
                sum += sign * num;
                num = 0;
                sign = -1;
            } else if (c == '(') {
                stack.push(sum);
                stack.push(sign);
                sum = 0;
                sign = 1;
            } else if (c == ')') {
                sum += sign * num;
                num = 0;
                sum *= stack.pop(); // previous sign
                sum += stack.pop(); // previous sum
            }
        }
        return sum + sign * num;
    }
}