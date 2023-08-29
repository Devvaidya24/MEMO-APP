// Memo.java Class
package memoapp;

// Data type
import java.util.Date;

/**
 * @author Dev
 */
// Creating public memo class
public class Memo {

    // Setting date of the memo
    public Date date;
    // Setting title of the memo
    public String title;
    // Setting message of the memo
    public String message;

    // Setting contructor memo object with date, time, message
    public Memo(Date date, String title, String message) {
        this.date = date; // Date of memo
        this.title = title; // Title of memo
        this.message = message; // Message of Memo
    }

    // toString method to return date, title and message of each detail
    @Override
    public String toString() {
        // Returns the variables
        return this.date + "\n" + this.title + "\n" + this.message + "\n";
    }

}
