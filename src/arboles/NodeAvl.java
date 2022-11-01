package arboles;

/**
 * It's a node for an AVL tree
 */
public class NodeAvl<T extends Comparable<T>> {

    // Creating a node with a value, a left child, a right child, a height and a number of appearances.
    T element;
    NodeAvl<T> left;
    NodeAvl<T> right;
    int height;
    int apariciones;

    // A constructor.
    public NodeAvl(T element) {
        this(element, null, null);
    }
    
    // A constructor.
    public NodeAvl(T element, NodeAvl<T> left, NodeAvl<T> right) {
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
