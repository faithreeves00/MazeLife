// File/Class Name: EmptyStackException.java
// Author: Faith Reeves
// Class Purpose: an Exception class that method pop() and peek() throw if the  
//                user attempts to execute them on an empty stack

package amazenlife;

public class EmptyStackException extends Exception {

    /**
     * Creates a new instance of <code>EmptyStackException</code> without detail
     * message.
     */
    public EmptyStackException() {
    }

    /**
     * Constructs an instance of <code>EmptyStackException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EmptyStackException(String msg) {
        super(msg);
    }
}
