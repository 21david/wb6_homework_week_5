//  https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

class Solution {
    int max = 0;
    
    public int longestIncreasingPath(int[][] matrix) {
        // 9 ms, faster than 61.57%
        // 39.8 MB, less than 26.31%
        
        /*
        If we can create a matrix of equivalent size that has the
        maximum number of moves you could move at each spot while
        only visiting bigger numbers, then the biggest number in 
        that matrix is the answer. 
        
        When creating this matrix, we can memoize the result of each 
        'search', so that we never recalculate it again, and we reuse
        the result of each search to calculate the other values, until
        we finish making this matrix.
        
        For example, in Example 1, the first two 9s don't have any
        bigger values around them, so the values in those spots would
        be 0. For the 4, we can move a maximum of 1 time, so that spot
        would have a 1. We would reuse these values until we know the 
        maximum # of moves you can make at each spot, and return the
        largest one.
        
        We could also solve this the other way around, where each spot
        in the matrix represents how far you can move from a spot while
        only visiting smaller values.
        */
        
        // edge cases:
        //   there is nothing in the input matrix
        if(matrix.length == 0 || matrix[0].length == 0)
            return 0;
        
        
        // we will use this to store values we have already calculated
        int[][] dp = new int[matrix.length][matrix[0].length];
        
        
        // fill dp matrix with -1s
        for(int i = 0; i < dp.length; i++)
            Arrays.fill(dp[i], -1);
        
        
        // iterate through each element in the matrix
        for(int r = 0; r < matrix.length; r++) {
            for(int c = 0; c < matrix[0].length; c++) {
                
                // if we've already calculated a value for this spot
                // then we don't want to do it again
                if(dp[r][c] >= 0)
                    continue;
                
                // calculate the value for this spot
                findPath(matrix, dp, r, c, 0, matrix[r][c] - 1);
                
            }
        }
        
        
  //      System.out.println(Arrays.deepToString(dp).replace("],","]\n"));
        return max + 1;
    }
    
    /* 
    For a given spot in the matrix, find out the largest number of moves you can make
    starting from that spot while only visiting bigger values, and save that value in 
    the dp matrix, so that later recursive calls don't repeat the same calculations.
    
    The biggest number in the matrix will have a value of 0, and the smallest number will
    have a value of at least 1.
    */
    public int findPath(int[][] matrix, int[][] dp, int r, int c, int ct, long prev) {
        
        if(outOfBounds(matrix, r, c))
            return 0;
        
        
        if( matrix[r][c] <= prev ) // if the new number did not increase
            return 0;
        
        
        // if this spot has already been calculated, we simply add 1 to that
        // value and return it
        if(dp[r][c] >= 0)
            return dp[r][c] + 1;
        
        
        // we want to search in all 4 directions, and only assign the length of 
        // the longest path we found out of all 4 directions to dp[r][c].
        dp[r][c] = max(findPath(matrix, dp, r + 1, c, ct + 1, matrix[r][c]),
                       findPath(matrix, dp, r - 1, c, ct + 1, matrix[r][c]),
                       findPath(matrix, dp, r, c + 1, ct + 1, matrix[r][c]),
                       findPath(matrix, dp, r, c - 1, ct + 1, matrix[r][c]));
        
        
        // always look out for a new longest increasing path
        if(dp[r][c] > max)
            max = dp[r][c];
        
        
        return dp[r][c] + 1;
    }
    
    
    // returns the maximum of an array of numbers
    public int max(int... nums)
    {
        if(nums.length == 0)
            return -1;
        
        int max = nums[0];
        
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > max)
                max = nums[i];
        }
        
        return max;
    }
    
    
    // returns true if the coordinates r, c are out of bounds in matrix
    public boolean outOfBounds(int[][] matrix, int r, int c) {
        return r < 0 || c < 0 || r >= matrix.length || c >= matrix[0].length;
    }
    
}
