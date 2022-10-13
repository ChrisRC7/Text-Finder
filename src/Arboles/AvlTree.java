package Arboles;

public class AvlTree {

    public AvlNode root;

    private static final int ALLOWED_IMBALANCE = 1;

    public AvlTree() {
        root = null;
    }

    public void insert(int val) {
        root = insert(val, root);
    }

    private int height(AvlNode t) {
        return t == null ? -1 : t.height;
    }

    private AvlNode insert(int x, AvlNode t) {
        if (t == null) {
        return new AvlNode(x);

        }

        if (x < t.element) {
        t.left = insert(x, t.left);

        } else if (x > t.element) {
        t.right = insert(x, t.right);
        }

        return balance(t);
    }

    private AvlNode balance(AvlNode t) {
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

        private AvlNode rotateWithLeftChild(AvlNode k2) {
            AvlNode k1 = k2.left;
            k2.left = k1.right;
            k1.right = k2;
            k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
            k1.height = Math.max(height(k1.left), k2.height) + 1;
            return k1;
        }

        private AvlNode doubleWithLeftChild(AvlNode k3) {
            k3.left = rotateWithRightChild(k3.left);
            return rotateWithLeftChild(k3);
        }

        private AvlNode rotateWithRightChild(AvlNode k2) {
            AvlNode k1 = k2.right;
            k2.right = k1.left;
            k1.left = k2;
            k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
            k1.height = Math.max(height(k1.left), k2.height) + 1;
            return k1;
        }

        private AvlNode doubleWithRightChild(AvlNode k3) {
            k3.right = rotateWithRightChild(k3.right);

            return rotateWithRightChild(k3);
        }

        public boolean contains(int element) {
            return this.contains(element, this.root);
        }

        private boolean contains(int element, AvlNode node) {
            if (node == null) {
                System.out.println("No hay");
                return false;
            }else {
                int compareResult = element; //.compareTo(node.element);
                if (compareResult < node.element){
                    System.out.println("es menor");
                    return contains(element, node.left);
                }else {
                    if (compareResult > node.element){
                        System.out.println("es mayor");
                        return contains(element, node.right);
                    }
                    else{
                        System.out.println("esta");
                        return true;
                }
                }
            }
        }
            

}

