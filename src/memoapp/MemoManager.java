// MemoManager.java Class
package memoapp;

// Data types
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @param <E>
 * @author Dev
 */
// Public memo manager class extends comparable 
public class MemoManager<E extends Comparable> {

    // Declaring variables for binary tree date and title
    public BinaryTree bTreeDate, bTreeTitle;

    // Public method for addMemo which takes strin date title and message created memo object to call
    public void addMemo(String date, String title, String message) {

        Date d = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        try {
            // Parse the date stringto date object
            d = dateFormat.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(MemoManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Creating memo with parsed date title and message
        Memo m = new Memo(d, title, message);

        if (date == null) {
            // Throws an exception is date is empty
            throw new IllegalArgumentException("Empty Date!!!");
        }

        // Add memo into binary tree from date key
        addToTree(m, (E) m.date);

        // Add memo into binary tree from title key
        addToTree(m, (E) m.title);
    }

    // Public addToTree method which takes memo object and key add to bTreetitle and bTreeDate
    public void addToTree(Memo memo, E key) {
        if (key instanceof Date) {
            if (bTreeDate == null) {
                //Creat new binary tree with memo as root node
                bTreeDate = new BinaryTree<>(new Node<>(memo).element, key);
            } else {
                // Add memo to existing binary tree in form of the key
                bTreeDate.addElement(memo, key);
            }
        } else if (key instanceof String) {
            if (bTreeTitle == null) {
                // Create new binary tree with memo as root node
                bTreeTitle = new BinaryTree<>(new Node<>(memo).element, key);
            } else {
                // Add memo to existing binary tree with form of the key
                bTreeTitle.addElement(memo, key);
            }
        }
    }

    // public findMemo method which takes search key for memo 
    public Memo findMemo(E key) {
        if (key instanceof Date) {
            // Search for memo binary tree based on date key
            return (Memo) bTreeDate.searchElement(key);
        }
        if (key instanceof String) {
            // Search for memo binary tree based on title key
            return (Memo) bTreeTitle.searchElement(key);
        }
        // Return nothing
        return null;
    }

    public Memo[] getSortedMemoList(E key) {

        if (key instanceof Date) {
            // Retrieves sorted list of memo from binary tree on date key
            return sortedMemoListAlgo(bTreeDate);
        } else if (key instanceof String) {
            // Retrieves sorted list of memo from binary tree on title key
            return sortedMemoListAlgo(bTreeTitle);
        }
        // Return nothing
        return null;
    }

    // Private method for sorted list of memos for keys which are called  on to getSortedMemoList
    private Memo[] sortedMemoListAlgo(BinaryTree binaryTree) {
        // Gets node of binary tree in sorted order
        Node[] node = binaryTree.toSortedList();

        int n = (node != null) ? node.length : 0;
        Memo[] memos = new Memo[n];

        // Converts node to memos and store in array
        for (int i = 0; i < n; i++) {
            memos[i] = (Memo) node[i].element;
        }
        // Returns the memos
        return memos;
    }

    // Public reverseOrder that reverses order if memos
    public void reverseOrder() {
        //Reverses the order of memos in the binary tree based on date key
        reversOrderAlgo(bTreeDate);
        //Reverses the order of memos in the binary tree based on title key
        reversOrderAlgo(bTreeTitle);
    }

    // Private method for reverse algorithmt which is then called on reverseOrder function
    private void reversOrderAlgo(BinaryTree binaryTree) {
        if (binaryTree != null) {
            // Reverse the order in binary tree
            binaryTree.reverseOrder();
        }
    }
}
