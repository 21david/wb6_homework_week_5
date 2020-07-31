//  https://leetcode.com/problems/island-perimeter/

class Solution {
    
    int perimeter = 0;
    
    public int islandPerimeter(int[][] grid) {
        // 8 ms, faster than 79.31%
        // 40.9 MB, less than 6.79%
        
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        outer: 
        for(int r = 0; r < grid.length; r++)
        {
            for(int c = 0; c < grid[0].length; c++)
            {
                
                // when it finds a '1', it will begin a dfs
                // which will look out for other '1's,
                // and when it finds a 0, or goes out of bounds,
                // it will add 1 to perimeter
                if(grid[r][c] == 1) {
                    dfs(grid, visited, r, c);
                    break outer; // only one island in the input, so we can stop once we've processed it
                }
            }
        }
        
        return perimeter;
    }
    
    public void dfs(int[][] grid, boolean[][] visited, int r, int c) {
        if(outOfBounds(grid, r, c)) {
            perimeter++;
            return;
        }
        else if(visited[r][c])
            return;
        else if(grid[r][c] == 0) {
            perimeter++;
            return;
        }
        else if(grid[r][c] == 1) {
            visited[r][c] = true;
            
            dfs(grid, visited, r - 1, c);
            dfs(grid, visited, r + 1, c);
            dfs(grid, visited, r, c - 1);
            dfs(grid, visited, r, c + 1);
        }
    }
    
    public boolean outOfBounds(int[][] grid, int r, int c) {
        return r < 0 || c < 0 || r >= grid.length || c >= grid[0].length;
    }
}
