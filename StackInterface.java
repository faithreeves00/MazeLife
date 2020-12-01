// File/Class Name: StackInterface.java
// Author: Faith Reeves
// Class Purpose: An interface for the methods in FaithsStack.java  

package amazenlife;

public interface StackInterface<E> {

    E push(E data);

    E pop() throws EmptyStackException;

    E peek() throws EmptyStackException;

    boolean isEmpty();
}
