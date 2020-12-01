// File/Class Name: FaithsStack.java
// Author: Faith Reeves
// Class Purpose: an implementation of a generic stack class with methods
//                push(), pop(), peek(), and isEmpty()

package amazenlife;

import java.util.ArrayList;

public class FaithsStack<E> implements StackInterface<E> {

    // declare an ArrayList stack
    private final ArrayList<E> stack;

    // declare a size datafield
    int size;

    // construct an empty stack using an ArrayList as the container
    public FaithsStack() {

        stack = new ArrayList<>();
    }

    // method to push an item onto the stack and return the item
    @Override
    public E push(E data) {

        // push onto stack
        stack.add(data);

        // return item pushed
        return data;
    }

    // method to pop (remove) an item from the stack and return the item removed
    @Override
    public E pop() throws EmptyStackException {

        // if the stack is empty, execute statement
        if (stack.isEmpty()) {

            // throw an exception
            throw (new EmptyStackException());
        }

        // remove the item from the stack
        E top = stack.remove(stack.size() - 1);

        // return the item that was removed
        return top;

    }

    // method to peek at (reuturn) the top item in a stack
    @Override
    public E peek() throws EmptyStackException {

        // if the stack is empty, execute statement
        if (stack.isEmpty()) {

            // throw an exception
            throw (new EmptyStackException());
        }

        // store the top stack item in the variable top
        E top = stack.get(stack.size() - 1);

        // return the top item in the stack
        return top;

    }

    // method to check if stack is empty, returns true or false
    @Override
    public boolean isEmpty() {

        // return true if empty, false if occupied
        return stack.isEmpty();
    }
}
