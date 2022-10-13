package Arboles;

public class AvlTree {
    public Node root;

    class Node {
        int key;
        Node left,right;
        int height;

        public Node(int val) {
            key = val;
            left = right = null;
            height = 0;
        }
    }

    public AvlTree(int val) {
        root = new Node(val);
    }

    public AvlTree() {
        root = null;
    }

    public void insert(int val) {
        root = insert(root, val);
    }

    public int getHeight(Node node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    public int max(int a, int b) {
        return a>b?a:b;
    }

    public Node insert(Node node, int val) {
        if (node == null) {
            return new Node(val);
        } 

        if (val<node.key) {
            node.left = insert(node.left, val);
        } else if (val>node.key){
            node.right = insert(node.right, val);
        } else {
            return node;
        }

        node.height =  1+max(getHeight(node.left), getHeight(node.right));

        int balFactor = getBalanceFactor(node);

        //Left - Left Case
        if (balFactor>1 && val < node.left.key) {
            return rightRotation(node);
        }

        //Left - Right Case
        if (balFactor>1 && val > node.left.key) {
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }

        //Right - Right Case
        if (balFactor<-1 && val < node.left.key) {
            return leftRotation(node);
        }

        //Right - Left Case
        if (balFactor>1 && val > node.left.key) {
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }

        return node;
    }

    private Node rightRotation(Node z) {
        Node y = z.left;
        Node t3 = y.right;

        y.right = z;
        z.left = t3;

        z.height= 1 + max(getHeight(z.left), getHeight(z.right));
        y.height= 1 + max(getHeight(y.left), getHeight(y.right));

        return y;
    }

    private Node leftRotation(Node z) {
        Node y= z.right;
        Node t3= y.left;

        y.left= z;
        z.right= t3;
        
        z.height= 1 + max(getHeight(z.left), getHeight(z.right));
        y.height= 1 + max(getHeight(y.left), getHeight(y.right));

        return y;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    public void inOrder(Node root) {
        if (root!=null) {
            inOrder(root.left);
            System.out.println(root.key + " height is " + root.height);
            inOrder(root.right);
        }

    }
}

/*
 * En agradecimiento de este maravilloso codigo dejo aqui en link del origen del codigo:
 * https://www.youtube.com/watch?v=x7dkE4WGgZw&ab_channel=LogicFirstTamil
 */