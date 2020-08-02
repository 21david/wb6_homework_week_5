//  https://leetcode.com/problems/course-schedule-ii/

class Solution {
    // input graph is represented by an edge list
    // each nested array is an edge from prereq[i][0] to prereq[i][1]
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 4 ms, faster than 88.09%
        // 40.7 MB, less than 43.50%
        
        // Time complexity: O(V + E)
        // Space complexity: O(V + E)
        
        // 'indegree' means the number of nodes pointing to a node
        int[] indegree = new int[numCourses];
        
        
        // convert to adjacency list. O(E).
        ArrayList[] adj = new ArrayList[numCourses];
        for(int[] pair : prerequisites) {
            if(adj[ pair[0] ] == null)
                adj[ pair[0] ] = new ArrayList<>();
            
            adj[ pair[0] ].add( pair[1] );
        }
        
        // Traverse ajdacency lists to fill indegrees of vertices. O(V + E).
        for(int i = 0; i < numCourses; i++) {
            ArrayList<Integer> temp = (ArrayList<Integer>) adj[i];
            
            if(temp == null) // if this node doesn't point to any other node
                continue;
            
            for(int node : temp)
                indegree[node]++;
        }
        
        
        // BFS: (O(V + E))
        
        // Create a queue and initially add all vertices with indegree 0
        Queue<Integer> queue = new LinkedList<Integer>(); 
        for(int i = 0; i < numCourses; i++) 
            if(indegree[i] == 0) 
                queue.add(i);  
        
        // keep a count of visited vertices
        int ct = 0;
        
        // this list will store the result (a topological ordering of the nodes,
        // or an order that we can visit all the nodes in, given the edges.)
        ArrayList <Integer> topOrder = new ArrayList<Integer>(); 
        
        while(!queue.isEmpty()) {
            int u = queue.poll();
            topOrder.add(0, u);
            
            // Iterate through all its neighbouring nodes 
            // of cuurent node u and decrease their in-degree by 1 
            if(adj[u] != null) // (if current node point to other nodes)
                for(int i = 0; i < adj[u].size(); i++) {
                    int node = (Integer) adj[u].get(i); // current neighbor node
                    
                    // If in-degree becomes zero, add it to the queue
                    if(--indegree[node] == 0)
                        queue.add(node);
                }
            
            ct++;
        }
        
        // So at this point, if there was a cycle in the graph, then 
        // ct != numCourses. If there was no cycle, then ct == numCourses
        // and an order that we can take the classes in is stored in
        // topOrder.
        
        if( ct != numCourses ) {
            return new int[0]; // O(1).
        }
        else {
           // convert topOrder to int[]. O(V).
            int[] ans = new int[topOrder.size()];
            int a = 0;

            for(int classNum : topOrder)
                ans[a++] = classNum;

            return ans; 
        }
        
    }
}

/*
Test cases:
(image: imgur.com/a/4NAFNJH )

true:
2
[[1,0]]
4
[[1,0],[2,1],[3,1]]
7
[[1,0],[4,1],[4,2],[4,3],[5,4],[6,4]]
7
[[1,0],[2,0],[3,1],[4,2],[5,3],[5,4],[6,5]]
1
[]
3
[]
7
[[4,0],[4,1],[4,2],[3,0],[3,1],[3,2],[5,3],[5,4],[6,5]]
7
[[3,0],[4,1],[4,2],[6,4]]
6
[[1,0],[3,2],[5,4]]


false:
2
[[1,0],[0,1]]
3
[[1,0],[2,1],[0,2]]
5
[[3,0],[3,1],[3,2],[4,3],[2,4]]
11
[[2,0],[2,1],[3,2],[6,3],[7,6],[8,7],[9,8],[10,9],[6,10]]
8
[[1,0],[2,0],[3,1],[3,2],[3,7],[5,4],[6,4],[7,5],[7,6],[7,3]]
13
[[1,0],[2,1],[3,1],[4,2],[4,3],[5,4],[7,6],[10,8],[10,9],[11,10],[12,11],[11,12]]
9
[[8,7],[7,6],[1,0],[2,1],[3,2],[4,3],[5,4],[0,5]]

*/
