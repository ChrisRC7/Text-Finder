package Arboles;

public class AvlNode<T extends Comparable<T>> {

    T element;
    AvlNode<T> left;
    AvlNode<T> right;
    int height;
    int apariciones;

    public AvlNode(T element) {
        this(element, null, null);
    }
    
    public AvlNode(T element, AvlNode<T> left, AvlNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height = 0;
        this.apariciones= 1;
    }

    public void AumentarApariciones(){
        this.apariciones++;
    }

    public int NumApariciones() {
        return this.apariciones;
    }
}
