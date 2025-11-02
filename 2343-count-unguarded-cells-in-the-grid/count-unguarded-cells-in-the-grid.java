class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        
        for (int[] guard : guards) {
            grid[guard[0]][guard[1]] = 2;
        }
        
        for (int[] wall : walls) {
            grid[wall[0]][wall[1]] = 3;
        }
        
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        for (int[] guard : guards) {
            int row = guard[0];
            int col = guard[1];
            
            for (int[] dir : directions) {
                int r = row + dir[0];
                int c = col + dir[1];
                
                while (r >= 0 && r < m && c >= 0 && c < n) {
                    if (grid[r][c] == 2 || grid[r][c] == 3) {
                        break;
                    }
                    
                    grid[r][c] = 1;
                    
                    r += dir[0];
                    c += dir[1];
                }
            }
        }
        
        int unguarded = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    unguarded++;
                }
            }
        }
        
        return unguarded;
    }
}