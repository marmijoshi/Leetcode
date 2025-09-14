class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();

        for(String s : operations) {
            if(s.equals("C")) {
                stack.pop();
            } else if(s.equals("D")) {
                int temp = stack.peek() * 2;
                stack.push(temp);
            } else if(s.equals("+")) {
                int temp1 = stack.pop();
                int temp2 = stack.pop();
                int sum = temp1 + temp2;
                stack.push(temp2);
                stack.push(temp1);
                stack.push(sum);
            } else {
                int val = Integer.parseInt(s);
                stack.push(val);
            }
        }

        int ans = 0;
        while(!stack.isEmpty()) {
            ans += stack.pop();
        }

        return ans;
    }
}