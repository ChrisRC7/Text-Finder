package Arboles;

public class Node<T extends Comparable<T>> {
    
    T element;
    Node<T> left;
    Node<T> right;
    int apariciones;
    
    public Node(T element) {

        this(element, null, null);
    }

    public Node(T element, Node<T> left, Node<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.apariciones= 1;
    }

    public void AumentarApariciones() {
        this.apariciones++;
    }

    public int NumApariciones() {
        return this.apariciones;
    }

}
