class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> res = new ArrayList<>();

        for(int num : nums) {
            res.add(num);

            while(res.size() >= 2) {
                int n = res.size();
                int last = res.get(n-1);
                int secondLast = res.get(n-2);

                long gcdVal = gcd(last, secondLast);
                if(gcdVal > 1) {
                    long lcmVal = (long) last * secondLast / gcdVal;
                    res.remove(n-1);
                    res.set(n-2, (int) lcmVal);
                } else {
                    break;
                }
            }
        }
        return res;
    }

    private long gcd(long a, long b) {
        while(b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}