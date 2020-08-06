//  https://leetcode.com/problems/same-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 0 ms, faster than 100.00%
        // 39 MB, less than 5.05%
        
        // Time complexity: O(N)
        // Space complexity: O(1), no extra space is created
        
        return isSame(p, q);
    }
    
    public boolean isSame(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        else if(p == null || q == null || p.val != q.val)
            return false;
        else 
            return isSame(p.left, q.left) && isSame(p.right, q.right);
            
    }
}
