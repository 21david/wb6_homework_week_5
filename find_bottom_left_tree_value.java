//  https://leetcode.com/problems/find-bottom-left-tree-value/

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
    public int findBottomLeftValue(TreeNode root) {
        // 8 ms, faster than 6.91%
        // 40.3 MB, less than 5.39%
        
        /*
        Solution using priority queue:
        Place all nodes in a priority queue.
        This priority queue sorts first by level, then by order it was placed.
        If we do a BFS that goes from left to right for each level of the tree,
        the first element in the PQ will be the bottom left value.
        */
        
        // pq will store arrays of size 3: [level, position in level, value]
        // it will sort first by level (descending), then by position in level (ascending)
        PriorityQueue<int[]> pq = new PriorityQueue((xObj, yObj) -> {
            // x[0] is level that the node is on
            // x[1] is what position it is in that level (1 is 1st, 2 is 2nd, etc.)
            int[] x = (int[]) xObj;
            int[] y = (int[]) yObj;
            
            if(x[0] > y[0])
                return -1;
            else if(x[0] < y[0])
                return 1;
            else {
                if(x[1] < y[1])
                    return -1;
                else if(x[1] > y[1])
                    return 1;
                else
                    return 0;
            }
        });
    
        // Now let's add all elements of the tree to the pq
        
        addElements(root, pq);
        
        return pq.poll()[2];
    }
    
    public void addElements(TreeNode root, PriorityQueue<int[]> pq) {
        
        //BFS
        Queue<TreeNode> nodesQueue = new LinkedList<TreeNode>();
        Queue<Integer> levelsQueue = new LinkedList<Integer>();
        
        nodesQueue.add(root);
        levelsQueue.add(1);
        
        // when we reach a new level, we want to reset pos to 1, so we keep
        // track of the level during each iteration to know when we're in a new one
        int prevLevel = 1;
        int pos = 1;
        
        while(!nodesQueue.isEmpty()) {
            // get current node and level
            TreeNode cur = nodesQueue.poll();
            int curLevel = levelsQueue.poll();
            
            // skip null values
            if(cur == null)
                continue;
            
            if(curLevel != prevLevel) // reset position to 1 if we are on a new level
                pos = 1;
            
            // add to the priority queue, which sorts internally
            pq.add(new int[] {curLevel, pos, cur.val});
            
            pos++;
            prevLevel = curLevel;
            
            // add children to the queue
            nodesQueue.add(cur.left);
            levelsQueue.add(curLevel + 1);
            nodesQueue.add(cur.right);
            levelsQueue.add(curLevel + 1);
        }
        
    }
}

/*

Test cases:
(image: imgur.com/a/l2JvKpa
 the nodes circled in blue are the expected output for that tree).

from description:
[2,1,3]
[1,2,3,4,null,5,6,null,null,7]

from image: 
[5,7,8,12,8,6,3,5,7,null,null,2,null,null,1,null,null,null,null,null,17,3]
[5,4,null,3,null,2]
[10]
[20,23,null,5,9,null,null,18,16]
[1,null,2,3,4,5,6,null,7,8,null,null,9,10,11]
[9,null,8,null,7,6,5,4,null,3]
[8,null,6,null,4,10,null,null,12]

*/
