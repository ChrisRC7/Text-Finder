package Arboles;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public boolean contains(T element) {
        return this.contains(element, this.root);
    }

    private boolean contains(T element, Node<T> node) {
        if (node == null) {
            return false;
        } else {
            int compareResult = element.compareTo(node.element);
            if (compareResult < 0){
                return contains(element, node.left);
            }
            else if (compareResult > 0){
            return contains(element, node.right);
            }
        else
        return true;
        }
    }

    public void insert(T element) {
        this.root = this.insert(element, this.root);
    }

    private Node<T> insert(T element, Node<T> current) {
        if (current == null)
            return new Node<T>(element, null, null);

        int compareResult = element.compareTo(current.element);
        if (compareResult < 0)
            current.left = this.insert(element, current.left);
        else if (compareResult >= 0)
            current.right = this.insert(element, current.right);
        return current;
        }

    public void remove(T element) {
        this.root = this.remove(element, this.root);
    }

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

        public Node<T> findMin() {
            if (this.isEmpty()) {
                return null;
            } else {
                return this.findMin(this.root);
            }
        }

        public Node<T> findMax() {
            if (this.isEmpty()) {
                return null;
            } else {
                return this.findMax(this.root);
            }
        }

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

        private Node<T> findMax(Node<T> node) {
            if (node!= null)
                while (node.right != null) {
                    node = node.right;
            }
            System.out.println(node.element);
            return node;
            }
            
            
            
        
        
}
