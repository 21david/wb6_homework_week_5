//  https://leetcode.com/problems/longest-univalue-path/

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
    
    int max = 0;
    
    public int longestUnivaluePath(TreeNode root) {
        // 9 ms, faster than 5.22%
        // 63.8 MB, less than 5.51%
        
        // visit each node
        // and from each node, start a search for a univalue path
        // make a variable that keeps track of the longest univalue path found so far
        
        visitEachNode(root);
        
        return max;
    }
    
    // this method visits each node and starts a search at each node for a univalue path
    public void visitEachNode(TreeNode root) {
        if(root == null)
            return;
        
        visitEachNode(root.left);
        visitEachNode(root.right);
        
        // this search should find the longest path found on the left side
        int l = search(root.left, root.val, 0);
        
        // this search should find the longest path found on the right side
        int r = search(root.right, root.val, 0);
        
        // check if we found a bigger length univalue path
        if(l + r > max)
            max = l + r;
    }
    
    public int search(TreeNode root, int val, int depth) {
        if(root == null)
            return 0;
        
        if(root.val != val)
            return depth;
        
        int searchLeft = search(root.left, val, depth) + 1;
        
        int searchRight = search(root.right, val, depth) + 1;
        
        return Math.max(searchLeft, searchRight);
    }
}

/*

Test cases:
(image: imgur.com/a/a6vASUx )

from description:
[5,4,5,1,1,5]
[1,4,5,4,4,null,5]

from image:
[5,5,5]
[5,5,5,5,5,5,5,null,5,4,null,null,5,6,null,5]
[1]
[5,5,5,5,5,4,5,3,null,1,5,null,null,5,6,null,null,null,null,2,null,5,null,7,5]
[1,1,null,1,null,1,null,1]
[8,8,7,8,8,null,8,4,8,8,8,8,8,1,3,8,8,null,8,0,null,8,null,null,8,null,null,null,null,8,null,null,0]
[1,null,1,null,1,null,1,null,1]

*/
