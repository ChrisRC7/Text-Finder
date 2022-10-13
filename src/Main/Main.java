package Main;

import Arboles.AvlTree;

public class Main {
    public static void main(String[] args) throws Exception {
        AvlTree Tree;
        Tree = new AvlTree();
        Tree.insert(50);
        Tree.insert(20);
        Tree.insert(60);
        Tree.insert(10);
        Tree.insert(30);
        Tree.insert(40);
        Tree.contains(30);
    }
}
