class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }
        
        int m = heightMap.length;
        int n = heightMap[0].length;
        
        if (m < 3 || n < 3) {
            return 0;
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[]{heightMap[i][j], i, j});
                    visited[i][j] = true;
                }
            }
        }
        
        int[] dirs = {-1, 0, 1, 0, -1}; 
        int water = 0;
        int maxHeight = 0;
        
        while (!pq.isEmpty()) {
            int[] cell = pq.poll();
            int height = cell[0];
            int row = cell[1];
            int col = cell[2];
            
            maxHeight = Math.max(maxHeight, height);
            
            for (int k = 0; k < 4; k++) {
                int newRow = row + dirs[k];
                int newCol = col + dirs[k + 1];
                
                if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n || visited[newRow][newCol]) {
                    continue;
                }
                
                if (heightMap[newRow][newCol] < maxHeight) {
                    water += maxHeight - heightMap[newRow][newCol];
                }
                
                pq.offer(new int[]{heightMap[newRow][newCol], newRow, newCol});
                visited[newRow][newCol] = true;
            }
        }
        
        return water;
    }
}