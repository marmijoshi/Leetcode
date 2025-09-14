class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for(String token : tokens) {
            switch(token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "-":
                    int b = stack.pop();
                    stack.push(stack.pop() - b);
                    break;
                case "/":
                    int d = stack.pop();
                    stack.push(stack.pop() / d);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.peek();
    }
}