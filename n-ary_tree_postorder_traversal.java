//  https://leetcode.com/problems/n-ary-tree-postorder-traversal/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

/*
Post order traversal is L, R, P
(left node, right node, parent node)
*/

class Solution {
    public List<Integer> postorder(Node root) {
        // 1 ms, faster than 74.68%
        // 43.8 MB, less than 5.23%
        
        ArrayList<Integer> postOrder = new ArrayList<>();
        
        postOrderTraversal(root, postOrder);
        
        return postOrder;
    }
    
    public void postOrderTraversal(Node root, ArrayList<Integer> list) {
        // base case is when a null child is sent as input
        if(root == null)
            return;
        
        // add the children to the list from left to right, then add the parent
        for(Node child : root.children)
            postOrderTraversal(child, list);
        
        list.add(root.val);
    }
}
