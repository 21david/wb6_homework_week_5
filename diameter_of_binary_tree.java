//  https://leetcode.com/problems/diameter-of-binary-tree/

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
    int maxDiameter = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        // 18 ms, faster than 5.21%
        // 41.1 MB, less than 5.03%
        
        /*
        Visit all nodes. Start a search at each node.
        Have a variable that keeps track of the maximum diameter found so far.
        */
        
        if(root == null)
            return 0;
        
        visitAll(root);
        
        return maxDiameter;
    }
    
    public void visitAll(TreeNode node) {
        
        // this should return the longest path down on the left side of this subtree
        int l = search(node.left, 0);
        
        // this should return the longest path down on the right side of this subtree
        int r = search(node.right, 0);
        
        if(l + r > maxDiameter)
            maxDiameter = l + r;
        
        if(node.left != null)
            visitAll(node.left);
        
        if(node.right != null)
            visitAll(node.right);
    }
    
    public int search(TreeNode node, int val) {
        if(node == null)
            return 0;
        
        int l = search(node.left, val + 1);
        int r = search(node.right, val + 1);
        
        return Math.max(l, r) + 1;
    }
}
