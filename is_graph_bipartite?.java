//  https://leetcode.com/problems/is-graph-bipartite/

class Solution {
    // input graph is represented by adjacency lists
    // each nested array is the adjacency list for a node
    public static boolean isBipartite(int[][] graph)
	{
        // Time Limit Exceeded
        // 56/78 test cases passed
        
	    // Put every node into one of two groups using a BFS
        HashSet<Integer> group1 = new HashSet<>();
        HashSet<Integer> group2 = new HashSet<>();
	    
	    Queue<Integer> queue = new LinkedList<>();
        Queue<Boolean> whichGroup = new LinkedList<>(); // true = group1, false = group2
        
        HashSet<Integer> seen = new HashSet<>();
        
        queue.add(0);
        whichGroup.add(true);
        
        while(!queue.isEmpty()) {
            int curNode = queue.poll();
            boolean curGroup = whichGroup.poll();
            
            if(seen.contains(curNode))
                continue;
            
            seen.add(curNode);
            
            if(curGroup)
                group1.add(curNode);
            else
                group2.add(curNode);
            
            
            // add all adjacent nodes to the queue
            for(int i = 0; i < graph[curNode].length; i++) {
                queue.add(graph[curNode][i]);
                whichGroup.add(!curGroup);
            }
        }
        
   //     System.out.println(group1);
   //     System.out.println(group2);
        
        // now that we have them split into two groups, we have to check
        // that there exist no edges within two nodes that are in the same group
        // (none of the nodes in a group can connect with each other)
        
        for(int node : group1) {
            // iterate through this node's neighbors
            for(int i = 0; i < graph[node].length; i++) {
                if(group1.contains(graph[node][i])) // if a node connects to another node in the same group
                    return false;
            }
        }
        
        for(int node : group2) {
            // iterate through this node's neighbors
            for(int i = 0; i < graph[node].length; i++) {
                if(group2.contains(graph[node][i])) // if a node connects to another node in the same group
                    return false;
            }
        }
        
        boolean ans = true;
        
        // if the graph consists of multiple disjoin graphs, we need to make sure we took
        // care of all of the disjoin graphs
        while(seen.size() < graph.length)
        {
            HashSet<Integer> notSeen = new HashSet<>();
            
            for(int i = 0; i < graph.length; i++)
                if(!seen.contains(i))
                    notSeen.add(i);
            
            for(int notSeenNode : notSeen)
                if(!seen.contains(notSeenNode)) // only start a bfs if we haven't seen this node
                    ans = ans && bfs(graph, notSeenNode, seen);
            
    //        System.out.println(notSeen);
        }
        
        return ans;
	}
    
    public static boolean bfs(int[][] graph, int start, HashSet<Integer> seen) {
        // Put every node into one of two groups using a BFS
        HashSet<Integer> group1 = new HashSet<>();
        HashSet<Integer> group2 = new HashSet<>();
	    
	    Queue<Integer> queue = new LinkedList<>();
        Queue<Boolean> whichGroup = new LinkedList<>(); // true = group1, false = group2
        
        queue.add(start);
        whichGroup.add(true);
        
        
        while(!queue.isEmpty()) {
            int curNode = queue.poll();
            boolean curGroup = whichGroup.poll();
            
            if(seen.contains(curNode))
                continue;
            
            seen.add(curNode);
            
            if(curGroup)
                group1.add(curNode);
            else
                group2.add(curNode);
            
            
            // add all adjacent nodes to the queue
            for(int i = 0; i < graph[curNode].length; i++) {
                queue.add(graph[curNode][i]);
                whichGroup.add(!curGroup);
            }
        }
        
     //   System.out.println(group1);
    //    System.out.println(group2);
        
        // now that we have them split into two groups, we have to check
        // that there exist no edges within two nodes that are in the same group
        // (none of the nodes in a group can connect with each other)
        
        for(int node : group1) {
            // iterate through this node's neighbors
            for(int i = 0; i < graph[node].length; i++) {
                if(group1.contains(graph[node][i])) // if a node connects to another node in the same group
                    return false;
            }
        }
        
        for(int node : group2) {
            // iterate through this node's neighbors
            for(int i = 0; i < graph[node].length; i++) {
                if(group2.contains(graph[node][i])) // if a node connects to another node in the same group
                    return false;
            }
        }
        
        return true;
    }
}

/*

Test cases:

true:
[[1,3],[0,2],[1,3],[0,2]]

[[2],[2,5],[0,1],[],[7],[1],[7],[4,6]]
[[1,3],[0,2],[1,3,5],[0,2,4],[3,5],[2,4]]
[[1,2,3,5],[0],[0],[0],[],[0]]
[[],[],[],[]]
[[3,4],[4,5,6],[5,7],[0],[0,1],[1,2],[1],[2]]
[[2],[4],[0],[6],[1],[7],[3],[5]]
[[]]


false:
[[1,2,3], [0,2], [0,1,3], [0,2]]

[[1,4,5],[0,4],[5,6],[6],[0,1],[0,2],[2,3]]
[[1,3],[0,3],[3],[0,1,2]]
[[1,2],[0,2],[0,1]]
[[1,2],[0,2],[0,1],[4],[3]]
[[2],[2,3],[0,1],[1,4,5],[3,5],[3,4,6],[5,7],[6,8],[7,9],[8]]
[[1],[0,2,3],[1,3],[1,2],[5],[4],[7,8],[6,8],[6,7]]
[[1,3],[0,2],[1,3],[0,2],[5,6],[4,6],[4,5]]

*/
