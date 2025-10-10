class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int maxEnergy = Integer.MIN_VALUE;

        for (int i = n - k; i < n; i++) {
            int currentEnergy = 0;
            for (int j = i; j >= 0; j -= k) {
                currentEnergy += energy[j];
                maxEnergy = Math.max(maxEnergy, currentEnergy);
            }
        }

        return maxEnergy;
    }
}