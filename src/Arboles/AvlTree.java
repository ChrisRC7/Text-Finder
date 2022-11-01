package Arboles;

/**
 * It's a class that creates an AVL tree
 */
public class AvlTree<T extends Comparable<T>> {

    // Declaring the variables that will be used in the class.
    public AvlNode<T> root;
    int NumComparaciones=0;
    int CantidadResultados=0;
    int Cantidad_de_Datos=0;
    String DocName;

    private static final int ALLOWED_IMBALANCE = 1;

    // Creating a constructor for the class.
    public AvlTree() {
        root = null;
    }

    /**
     * It prints the number of data.
     */
    public void altura(){
        System.out.println(Cantidad_de_Datos);
    }

    
    /** 
     * @param val
     */
    public void insert(T val) {
        this.Cantidad_de_Datos++;
        this.root = insert(val, this.root);
    }

    
    /** 
     * @param t
     * @return int
     */
    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }

    /**
     * If the element is not in the tree, insert it. If it is in the tree, increase the number of times
     * it appears
     * 
     * @param element The element to be inserted.
     * @param current the node that is currently being checked
     * @return The method returns the node that is being balanced.
     */
    private AvlNode<T> insert(T element, AvlNode<T> current) {

        if (current == null) {
            return new AvlNode<T>(element);
        }
        int compareResult= element.compareTo(current.element);
        if (compareResult==0) {
            current.AumentarApariciones();
        }
        if (compareResult< 0) {
            current.left = this.insert(element, current.left);

        }

        if (compareResult > 0) {
            current.right = this.insert(element, current.right);
        }

        return this.balance(current);
    }

    /**
     * If the tree is unbalanced, rotate it to the left or right depending on the height of the left or
     * right subtree
     * 
     * @param t the node to be balanced
     * @return The height of the tree.
     */
    private AvlNode<T> balance(AvlNode<T> t) {
        if (t == null)
            return t;
        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
            if (height(t.left.left) >= height(t.left.right))
            t = rotateWithLeftChild(t);
            else
            t = doubleWithLeftChild(t);

        } else {
            if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE){
                if (height(t.right.right) >= height(t.right.left))
                t = rotateWithRightChild(t);
            else
            t = doubleWithRightChild(t);
            }
        }

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    /**
     * The function takes a node, k2, and rotates it to the left
     * 
     * @param k2 the node that is out of balance
     * @return The node that is being rotated.
     */
    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        return k1;
    }

    /**
     * It performs a double rotation on the left child of the node.
     * 
     * @param k3 the node that is out of balance
     * @return The node that is being returned is the node that is being rotated.
     */
    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * "Rotate the right child of the node k2 to the left, and return the new root of the subtree."
     * 
     * The function is called "rotateWithRightChild" because the right child of k2 is being rotated to
     * the left
     * 
     * @param k2 the node that is out of balance
     * @return The node that is being returned is the node that is being rotated.
     */
    private AvlNode<T> rotateWithRightChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        return k1;
    }

    /**
     * It performs a double rotation on the right child of the node.
     * 
     * @param k3 the node that is out of balance
     * @return The node that is being returned is the node that is being rotated.
     */
    private AvlNode<T> doubleWithRightChild(AvlNode<T> k3) {
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }

    /**
     * The function contains() is a recursive function that returns true if the element is found in the
     * tree, and false otherwise
     * 
     * @param element The element to be searched for.
     * @return The method returns a boolean value that indicates if the element is in the tree.
     */
    public boolean contains(T element) {
        this.NumComparaciones=0;
        this.CantidadResultados=0;
        return this.contains(element, this.root);
    }

    /**
     * The function contains() is a recursive function that takes an element and a node as parameters.
     * If the node is null, it returns false. If the node is not null, it compares the element to the
     * node's element. If the element is equal to the node's element, it returns true. If the element
     * is less than the node's element, it calls itself with the element and the node's left child. If
     * the element is greater than the node's element, it calls itself with the element and the node's
     * right child
     * 
     * @param element The element to be searched for.
     * @param node The node to start at.
     * @return The method returns a boolean value.
     */
    private boolean contains(T element, AvlNode<T> node) {
        if (node == null) {
            return false;
        } else {
            int compareResult = element.compareTo(node.element);
            this.NumComparaciones++;
            if (compareResult==0){
                this.CantidadResultados= node.NumApariciones();
                return true;

            } else if(compareResult<0){
                return contains(element, node.left);

            } else if (compareResult > 0) {
                return contains(element, node.right);
            }
        }
        return false;
    }
                    
            
        
    
    /** 
     * @return int
     */
    public int GetResultados() {
        return this.CantidadResultados;
    }
            

    
    /** 
     * @return int
     */
    public int GetComparaciones() {
        return this.NumComparaciones;
    }
            
    /**
     * This function sets the name of the document
     * 
     * @param Doc The name of the document to be created.
     */
    public void SetDocName(String Doc) {
        this.DocName= Doc;
    }

    /**
     * This function returns the name of the document
     * 
     * @return The name of the document.
     */
    public String GetDocName() {
        return this.DocName;
    }

    /**
     * It prints the data in the tree in order.
     */
    public void Datos() {
        this.inOrder(this.root);
    }
        
    /**
     * If the root is not null, print the root, then recursively call the function on the right and
     * left subtrees
     * 
     * @param root the root of the tree
     */
    private void inOrder(AvlNode<T> root) {
        if (root!=null) {
            System.out.println(root.element);
            this.inOrder(root.right);
            this.inOrder(root.left);
                
        }
    }

}

