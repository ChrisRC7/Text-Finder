package Arboles;

/**
 * It's a binary tree that stores words and the number of times they appear in a document
 */
public class BinaryTree<T extends Comparable<T>> {
    // It's declaring the variables that will be used in the class.
    private Node<T> root;
    int NumComparaciones=0;
    int CantidadResultados=0;
    int Cantidad_de_Datos=0;
    String DocName;

    // Creating a new BinaryTree object.
    public BinaryTree() {
        this.root = null;
    }

    
    /** 
     * @return boolean
     */
    public boolean isEmpty() {
        return this.root == null;
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
     * It returns true if the element is found in the tree, and false otherwise
     * 
     * @param element The element to be searched for.
     * @param node the node to start at
     * @return The method returns a boolean value.
     */
    private boolean contains(T element, Node<T> node) {
        if (node == null) {
            return false;
        } else {
            int compareResult = element.compareTo(node.element);
            this.NumComparaciones++;
            if (compareResult==0) {
                this.CantidadResultados= node.NumApariciones();
                return true;
            } else if (compareResult < 0){
                return contains(element, node.left);
            }
            else if (compareResult > 0){
                return contains(element, node.right);
            }
        }
        return false;
    }

    /**
     * This function inserts a new element into the tree
     * 
     * @param element The element to be inserted.
     */
    public void insert(T element) {
        this.Cantidad_de_Datos++;
        this.root = this.insert(element, this.root);
    }

    /**
     * If the element is not in the tree, insert it. If it is in the tree, increase the number of times
     * it appears
     * 
     * @param element The element to be inserted.
     * @param current The current node in the tree.
     * @return The current node.
     */
    private Node<T> insert(T element, Node<T> current) {
        if (current == null) {
            return new Node<T>(element, null, null);
        }
        
        int compareResult = element.compareTo(current.element);
        if (compareResult==0) {
            current.AumentarApariciones();
        }
        if (compareResult < 0) {
            current.left = this.insert(element, current.left);
        }
            
        if (compareResult > 0) {
            current.right = this.insert(element, current.right);
        }
        return current;
    }

    /**
     * It removes the element from the tree.
     * 
     * @param element the element to be removed
     */
    public void remove(T element) {
        this.root = this.remove(element, this.root);
    }

    /**
     * If the element is less than the node, remove the element from the left subtree. If the element
     * is greater than the node, remove the element from the right subtree. If the element is equal to
     * the node, replace the node with the smallest element in the right subtree
     * 
     * @param element the element to be removed
     * @param node the node to be removed
     * @return The node that is being removed.
     */
    public Node<T> remove(T element, Node<T> node) {

        if (node == null){
            return node;
        } else{

            int compareResult = element.compareTo(node.element);
            if (compareResult < 0){
                node.left= remove(element, node.left);
            } else{

                if (compareResult > 0) {
                    node.right = remove(element, node.right);
                } else {
                    if (node.left != null && node.right != null){
                        node.element = findMin(node.right).element;
                        node.right = remove(node.element, node.right);
                    } else {
                        node = node.left != null ? node.left : node.right;
                        return node;
                    }
                }
            }
        }
        return node;
    }

    /**
     * If the tree is empty, return null. Otherwise, return the minimum value in the tree
     * 
     * @return The minimum value in the tree.
     */
    public Node<T> findMin() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.findMin(this.root);
        }
    }

    /**
     * If the tree is empty, return null. Otherwise, return the maximum value in the tree
     * 
     * @return The maximum value in the tree.
     */
    public Node<T> findMax() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.findMax(this.root);
        }
    }

    /**
     * If the node is null, return null. If the node is not null, and the left node is null, return the
     * node. If the node is not null, and the left node is not null, return the left node
     * 
     * @param node the node to start at
     * @return The minimum value in the tree.
     */
    private Node<T> findMin(Node<T> node) {
        if (node == null)
            return null;
        else{ if (node.left == null) {
            System.out.println(node.element);
            return node;
        }
        else
            return findMin(node.left);
        }
    }

    /**
     * If the node is not null, then while the node's right child is not null, set the node to the
     * node's right child
     * 
     * @param node the node to start the search from
     * @return The maximum value in the tree.
     */
    private Node<T> findMax(Node<T> node) {
        if (node!= null)
            while (node.right != null) {
                node = node.right;
        }
        System.out.println(node.element);
        return node;
    }
        
    
    /**
     * This function returns the number of results that were found in the search
     * 
     * @return The number of results.
     */
    public int GetResultados(){
        return this.CantidadResultados;
    }
            

    
    /**
     * This function returns the number of comparisons that the algorithm has made
     * 
     * @return The number of comparisons.
     */
    public int GetComparaciones(){
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
     * If the root is not null, print the root, then recursively call inOrder on the right child, then
     * recursively call inOrder on the left child
     * 
     * @param root the root of the tree
     */
    private void inOrder(Node<T> root) {
        if (root!=null) {
            System.out.println(root.element);
            this.inOrder(root.right);
            this.inOrder(root.left);
            
        }
    }
        
}
