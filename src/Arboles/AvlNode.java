package Arboles;

public class AvlNode<T extends Comparable<T>> {

    T element;
    AvlNode<T> left;
    AvlNode<T> right;
    int height;

    public AvlNode(T element) {
        this(element, null, null);
    }
    
    public AvlNode(T element, AvlNode<T> left, AvlNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height = 0;
    }
}
