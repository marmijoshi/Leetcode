class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[] { 0, 0, grid[0][0] });

        int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int row = curr[0], col = curr[1], maxElevation = curr[2];

            if (row == n - 1 && col == n - 1) {
                return maxElevation;
            }

            if (visited[row][col])
                continue;
            visited[row][col] = true;

            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n
                        && !visited[newRow][newCol]) {
                    pq.offer(new int[] { newRow, newCol,
                            Math.max(maxElevation, grid[newRow][newCol]) });
                }
            }
        }

        return -1;
    }
}