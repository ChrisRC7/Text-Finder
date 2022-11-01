package Arboles;

/**
 * It's a node for an AVL tree
 */
public class AvlNode<T extends Comparable<T>> {

    // Creating a node with a value, a left child, a right child, a height and a number of appearances.
    T element;
    AvlNode<T> left;
    AvlNode<T> right;
    int height;
    int apariciones;

    // A constructor.
    public AvlNode(T element) {
        this(element, null, null);
    }
    
    // A constructor.
    public AvlNode(T element, AvlNode<T> left, AvlNode<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height = 0;
        this.apariciones= 1;
    }

    /**
     * // Java
     * public void AumentarApariciones(){
     *         this.apariciones++;
     *     }
     */
    public void AumentarApariciones(){
        this.apariciones++;
    }

    
    /** 
     * @return int
     */
    public int NumApariciones() {
        return this.apariciones;
    }
}
