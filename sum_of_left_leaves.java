//  https://leetcode.com/problems/sum-of-left-leaves/

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
    int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        // 0 ms, faster than 100.00%
        // 37.5 MB, less than 32.08%
        
        if(root == null)
            return 0;
        
        if(root.left != null)
            visitAll(root.left, true);
        
        if(root.right != null)
            visitAll(root.right, false);
        
        return sum;
    }
    
    public void visitAll(TreeNode node, boolean left) {
        //if its a leave node AND a left child, add to sum
        if(node.left == null && node.right == null && left)
            sum += node.val;
        
        if(node.left != null)
            visitAll(node.left, true);
        if(node.right != null)
            visitAll(node.right, false);
    }
}
