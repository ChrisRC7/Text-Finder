package Arboles;

public class AvlNode {
    String element;
    AvlNode left;
    AvlNode right;
    int height;

    public AvlNode(String element) {
        this(element, null, null);
    }
    
    public AvlNode(String element, AvlNode left, AvlNode right) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height = 0;
    }
}
