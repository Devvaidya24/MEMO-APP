// Node.java Class
package memoapp;

/**
 * @param <E>
 * @param <F>
 * @author Dev
 */
// Public node class which has element, key and linker parts
public class Node<E, F extends Comparable> implements Comparable<Node> {

    // Setting element node
    public E element;
    // Setting key to compare different nodes
    public F key;
    // Linker pointing to node that have smaller key values
    public Node left;
    // Linker pointing to node that have bigger key values
    public Node right;

    // Public constructor setting with empty element, key, left, right to null
    public Node() {
        this.element = null;
        this.key = null;
        this.left = null;
        this.right = null;
    }

    // Public constructors node object setting element to element and rest to null
    public Node(E element) {
        this.element = element;
        this.key = null;
        this.left = null;
        this.right = null;
    }

    // compareTo method to compare nodes
    @Override
    public int compareTo(Node node) {
        return this.key.compareTo(node.key);
    }
}
