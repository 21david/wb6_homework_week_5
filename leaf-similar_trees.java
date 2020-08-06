//  https://leetcode.com/problems/leaf-similar-trees/

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

/*
If we do a depth first search and add nodes to a list only if they are leaf nodes,
we can get the lead value sequence for two trees and compare them.

*/

class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // 0 ms, faster than 100.00%
        // 39.3 MB, less than 5.22%
        
        // Time complexity: O(N1 + N2), N1 = # of nodes in tree 1, N2 = # of nodes in tree 2
        // Space complexity: O(N1 + N2)
        
        ArrayList<Integer> lvs1 = new ArrayList<>();
        ArrayList<Integer> lvs2 = new ArrayList<>();
        
        lvs1 = help(root1, lvs1);
        lvs2 = help(root2, lvs2);
        
        if(lvs1.size() != lvs2.size())
            return false;
        
        for(int i = 0; i < lvs1.size(); i++)
            if(lvs1.get(i) != lvs2.get(i))
                return false;
        
        return true;
    }
    
    public ArrayList<Integer> help(TreeNode root, ArrayList<Integer> list) {
        if(root == null)
            return list;
        
        if(root.left == null && root.right == null)
            list.add(root.val);
        else {
            help(root.left, list);
            help(root.right, list);
        }
        
        return list;
    }
}

/*

Test cases:

[3]
[7]
[8]
[8]
[3, 1, 2]
[19, 1, 2]
[3, 1, 2]
[5, 3, 4, 1, null, 2, null]
[3,5,1,6,2,9,8,null,null,7,4]
[3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]

*/
