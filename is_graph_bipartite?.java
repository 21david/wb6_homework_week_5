//  https://leetcode.com/problems/is-graph-bipartite/

class Solution {
    // input graph is represented by adjacency lists
    // each nested array is the adjacency list for a node
    public static boolean isBipartite(int[][] graph)
	{
        // 5 ms, faster than 17.18%
        // 39.6 MB, less than 92.69%
        
        /*
            Approach:
            First we color the nodes using a BFS. The first node gets set to a color (represented by 'true')
            Then, the nodes 1 level away from the starting node get set to the other color (represented by 'false')
            Then, the nodes 2 levels away get set to the same color as the starting node ('true')
            And we repeat until all nodes are colored.
            The graph may consist of multiple disjoint graphs, so we need to make sure to account for all
              of those disjoint graphs.
            As we're assigning the colors, we check to see if we ever have two adjacent nodes that are assigned
              the same color. If they are, then the graph cannot be split into two groups to make a bipartite
              graph. (In other words, in a bipartite graph, every edge strictly has to connect a node from one group
              to a node from the other group, so if it can't do that for at least 1 pair of nodes, then we can't
              split the graph into a bipartite graph.)
              
            Time complexity: O(V + E) (# of nodes + # of edges), we visit each node, and when we do,
              we visit all of its edges. So O(V + E) in total.
            Space complexity: O(N), we need to store info for each node
        */
        
        HashMap<Integer, Boolean> nodeAndColor = new HashMap<>(); // true = blue, false = red
        nodeAndColor.put(0, true);
	    
	    Queue<Integer> queue = new LinkedList<>();
        
        HashSet<Integer> seen = new HashSet<>();
        
        while(seen.size() < graph.length) {
            for(int i = 0; i < graph.length; i++) {
                // find the first node we haven't seen & put into the queue
                if(!seen.contains(i)) {
                    queue.add(i);
                    nodeAndColor.put(i, true);
                    break;
                }
            }
            
            // BFS on the node we found right above
            while(!queue.isEmpty()) {
                int node = queue.remove();
                
                if(seen.contains(node))
                    continue;
                
                seen.add(node);
                
                //iterate through the adjacent nodes of the current node
                for(int i = 0; i < graph[node].length; i++) {
                    // if we haven't seen this node
                    if(!seen.contains(graph[node][i])) {
                        // add to the queue
                        queue.add(graph[node][i]);
                        // but set it as the other color
                        nodeAndColor.put(graph[node][i], !nodeAndColor.get(node));
                    }
                    // else, if we have seen this node
                    else {
                        // if the current node and this neighbor node have the same color
                        // then the solution cannot be true, so we return false
                        if(nodeAndColor.get(node) == nodeAndColor.get(graph[node][i]))
                           return false;
                    }
                }
            }
        }
        
        return true;
	}
}

/*

Additional test cases:
(image: imgur.com/a/Ws8GB9a )

true:
[[1,3],[0,2],[1,3],[0,2]]
[[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[]]

(from image:)
[[2],[2,5],[0,1],[],[7],[1],[7],[4,6]]
[[1,3],[0,2],[1,3,5],[0,2,4],[3,5],[2,4]]
[[1,2,3,5],[0],[0],[0],[],[0]]
[[],[],[],[]]
[[3,4],[4,5,6],[5,7],[0],[0,1],[1,2],[1],[2]]
[[2],[4],[0],[6],[1],[7],[3],[5]]
[[]]



false:
[[1,2,3], [0,2], [0,1,3], [0,2]]
[[],[10,44,62],[98],[59],[90],[],[31,59],[52,58],[],[53],[1,63],[51,71],[18,64],[24,26,45,95],[61,67,96],[],[40],[39,74,79],[12,21,72],[35,85],[86,88],[18,76],[71,80],[27,58,85],[13,26,87],[75,94],[13,24,68,77,82],[23],[56,96],[67],[56,73],[6],[41],[50,88,91,94],[],[19,72,92],[59],[49],[49,89],[17],[16],[32,84,86],[61,73,77],[94,98],[1,74],[13,57,90],[],[93],[],[37,38,54,68],[33],[11],[7,85],[9],[49],[61],[28,30,87,93],[45,69,77],[7,23,76],[3,6,36,62],[81],[14,42,55,62],[1,59,61],[10],[12,93],[],[96],[14,29,70,73],[26,49,71,76],[57,83],[67],[11,22,68,89],[18,35],[30,42,67],[17,44],[25],[21,58,68],[26,42,57,95],[],[17],[22,83],[60],[26,83,84,94],[69,80,82],[41,82],[19,23,52],[20,41],[24,56],[20,33],[38,71,99],[4,45],[33],[35],[47,56,64],[25,33,43,82],[13,77],[14,28,66],[],[2,43],[89]]

(from image:)
[[1,4,5],[0,4],[5,6],[6],[0,1],[0,2],[2,3]]
[[1,3],[0,3],[3],[0,1,2]]
[[1,2],[0,2],[0,1]]
[[1,2],[0,2],[0,1],[4],[3]]
[[2],[2,3],[0,1],[1,4,5],[3,5],[3,4,6],[5,7],[6,8],[7,9],[8]]
[[1],[0,2,3],[1,3],[1,2],[5],[4],[7,8],[6,8],[6,7]]
[[1,3],[0,2],[1,3],[0,2],[5,6],[4,6],[4,5]]

*/
