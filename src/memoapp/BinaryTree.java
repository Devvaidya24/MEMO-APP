// BinaryTree.java Class
package memoapp;

// Data Type
import java.util.ArrayList;
import java.util.List;

/**
 * @param <E>
 * @param <F>
 * @author Dev
 */
public class BinaryTree<E, F extends Comparable> {

    // Setting flag which indicated revert order in traversal
    protected boolean revert;
    // Setting root node of binary tree
    public Node root;
    // Setting number of nodes in binary tree
    public int number_of_nodes;
    // Setting array to store sorted list of nodes
    public Node[] nodeList;

    /**
     * Public binary tree constructor Initializing root as null, number of nodes
 as 0 Node list by creating new array of nodes with size 30, revert flag
 as false
     *
     */
    public BinaryTree() {
        root = null;
        number_of_nodes = 0;
        nodeList = new Node[30];
        // Initializing revert flag as false
        revert = false;
    }

    /**
     * Public binary tree constructor Creating a new node and assigning it as
     * root, Setting element of the root node, Setting key of of root node,
     * Calculating code number of nodes in binary tree, Creating new array of
     * nodes with calculated size
     *
     * @param element
     * @param key
     *
     */
    public BinaryTree(E element, F key) {
        root = new Node<>();
        root.element = element;
        root.key = key;
        number_of_nodes = nodesAlgo(root);
        nodeList = new Node[number_of_nodes];
        // Initializing revert flag as false
        revert = false;
    }

    /**
     * Public binary tree constructor Assigning the parameter root to the local
 variable root Calculating number of nodes in binary tree Creating a new
 array of nodes with the calculated size Setting revert flag to false
     *
     * @param root
     */
    public BinaryTree(Node root) {
        root = root;
        number_of_nodes = nodesAlgo(root);
        nodeList = new Node[number_of_nodes];
        // Setting revert flag to false
        revert = false;
    }

    // Public add element taking element and key creates node object
    public void addElement(E element, F key) {
        // Creating new node with element
        Node<E, F> newNode = new Node<>(element);
        // Setting the key of the new node
        newNode.key = key;

        // root is null
        if (root == null) {
            // assign new node as root
            root = newNode;
        } else {
            // or else call add node method to ad new node to the binary tree
            addNode(root, newNode);
        }
    }

    // Public addNode function which calls private addNodeAlgo method
    public void addNode(Node root, Node node) {
        // Calls the addNodeAlgo method to add node
        addNodeAlgo(root, node);
    }

    // Private addNode algorithm method which is called to addNode function
    private void addNodeAlgo(Node root, Node node) {
        // If node key is less than roots key
        if (node.compareTo(root) < 0) {
            //if left of root is null assign the node as left
            if (root.left == null) {
                // Assign node as right child
                root.left = node;
                // Increments number of nodes
                number_of_nodes++;
            } else {
                // Otherwise recursively call addNode on left sub tree
                addNode(root.left, node);
            }
            // If nodes key is greater than roots key
        } else if (node.compareTo(root) > 0) {
            // If right root is null
            if (root.right == null) {
                // Assign node as right child
                root.right = node;
                // Increment number of nodes
                number_of_nodes++;
            } else {
                // Or else recursively call addNode on right sub tree
                addNode(root.right, node);
            }
        }
    }

    // Public Reverse order method 
    public void reverseOrder() {
        // Calling revert Order private method passing root as parameter
        reverseOrder(root);
    }

    // Private revert order method
    private void reverseOrder(Node root) {
        // Setting revert flag as true
        revert = true;
    }

    // Public searchElement method searches certain key in binary tree
    public E searchElement(F key) {
        //Creating aimmed node
        Node<E, F> tNode = new Node<>();
        // Setting key as aimed node
        tNode.key = key;
        // Calling searchNode method which finds te node for certain key
        Node<E, F> resultNode = searchNode(root, tNode);
        // returns element of the result node if it is foun otherwise it will return nothing
        return resultNode != null ? resultNode.element : null;
    }

    // Private searchNode method for recursively searching node in binary tree (which is findNode)
    private Node searchNode(Node root, Node node) {
        // If root or node is null return nothing
        if (root == null || node == null) {
            // Returns null
            return null;
        }
        // Or if root and node have the same key then
        if (root.compareTo(node) == 0) {
            // Return root
            return root;
            // Or else if the roots key is less than the nodes key
        } else if (root.compareTo(node) < 0) {
            // Then returns recursively search in right 
            return searchNode(root.right, node);
            // Or else recursively search left 
        } else {
            // Then returns recursively search in left
            return searchNode(root.left, node);
        }
    }

    // Public toSortedList method stores nodes to node list which convert binary tree to sorted list
    public Node[] toSortedList() {
        // Creating new node list to store sorted nodes
        List<Node> nodeList = new ArrayList<>();
        // Calling the toSortedList  method to convert the binary tree to sorted list
        toSortedList(root, nodeList);
        // Convert the list to array and then returns it
        return nodeList.toArray(new Node[number_of_nodes]);
    }

    // Private toSortedList which is a recursive algorithm for converting binary tree to sorted list
    private void toSortedList(Node root, List<Node> nodeList) {
        // If root is null
        if (root == null) {
            // Return
            return;
            // Or else if revert flag is true then
        } else if (revert) {
            // Recursively traverse the right 
            toSortedList(root.right, nodeList);
            // Adds theroot node to list
            nodeList.add(root);
            // Recursively traverse left
            toSortedList(root.left, nodeList);
            // Or else
        } else {
            // Recursively traverse left
            toSortedList(root.left, nodeList);
            // Adds the root node to list
            nodeList.add(root);
            // Recursively traverse the right
            toSortedList(root.right, nodeList);
        }
    }

    // Private method for counting number of nodes in binary tree
    private int nodesAlgo(Node<E, F> node) {
        // If node is null, return 0 or calculate the number of nodes recursively
        return node == null ? 0 : 1 + nodesAlgo(node.left) + nodesAlgo(node.right);
    }

    // Pubic traversal method for displaying in order of binary tree and prints elements
    public void traversal() {
        // Calling traversalAlgo private method in order traversal
        traversalAlgo(root);
    }

    // Private method for traversal of binary tree
    private void traversalAlgo(Node root) {
        // If root is not null then
        if (root != null) {
            // Recursively traverse left
            traversalAlgo(root.left);
            // Prints the element of the root node
            System.out.println(root.element.toString());
            //Recursively traverse right
            traversalAlgo(root.right);
        }
    }
}
