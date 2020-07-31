//  https://leetcode.com/problems/island-perimeter/

class Solution {
    
    int perimeter = 0;
    
    public int islandPerimeter(int[][] grid) {
        // DFS: 
        // 8 ms, faster than 79.31%
        // 40.9 MB, less than 6.79%
        
        // BFS:
        // 18 ms, faster than 10.99%
        // 40.6 MB, less than 20.37%
        
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
                 // dfs(grid, visited, r, c);
                    bfs(grid, visited, r, c);
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
    
    public void bfs(int[][] grid, boolean[][] visited, int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(new int[] {r, c});
        
        while(!queue.isEmpty()) {
            int[] curLoc = queue.poll();
            
            int curR = curLoc[0];
            int curC = curLoc[1];
            
            if(outOfBounds(grid, curR, curC) || grid[curR][curC] == 0)
            {
                perimeter++;
                continue;
            }
            
            // if we've already been here, continue
            if(visited[curR][curC])
                continue;
            
            if(grid[curR][curC] == 1)
                visited[curR][curC] = true;
            
            // add neighboring nodes of current node to the queue
            queue.add(new int[] {curR - 1, curC});
            queue.add(new int[] {curR + 1, curC});
            queue.add(new int[] {curR, curC + 1});
            queue.add(new int[] {curR, curC - 1});
        }
    }
    
    public boolean outOfBounds(int[][] grid, int r, int c) {
        return r < 0 || c < 0 || r >= grid.length || c >= grid[0].length;
    }
}
