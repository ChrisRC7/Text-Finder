package arboles;

/**
 * This class creates a node with a generic type, a left node, a right node and an integer that will
 * count the number of times the word appears in the text
 */
public class Node<T extends Comparable<T>> {
    
    // Creating a node with a generic type, a left node, a right node and an integer that will count
    // the number of times the word appears in the text.
    T element;
    Node<T> left;
    Node<T> right;
    int apariciones;
    
    // A constructor that initializes the node with the element, the left and right nodes and the
    //     // number of appearances of the word.
    public Node(T element) {

        this(element, null, null);
    }

    // A constructor that initializes the node with the element, the left and right nodes and the
    // number of appearances of the word.
    public Node(T element, Node<T> left, Node<T> right) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.apariciones= 1;
    }

    /**
     * This function increases the number of appearances of a word in a text
     */
    public void AumentarApariciones() {
        this.apariciones++;
    }

    /**
     * This function returns the number of times the word has been found in the text
     * 
     * @return The number of times the word appears in the text.
     */
    public int NumApariciones() {
        return this.apariciones;
    }

}
