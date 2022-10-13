package Arboles;

public class Node<T extends Comparable<T>> {
    
    
    T element;
    Node<T> left;
    Node<T> right;
    
    public Node(T element) {

        this(element, null, null);
    }

    public Node(T element, Node<T> left, Node<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }



}
