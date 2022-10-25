package Main;
 import Arboles.AvlTree;
public class Main {
    public static void main(String[] args){
        AvlTree Tree;
        Tree= new AvlTree();
        Tree.insert("50000");
        Tree.insert("20");
        Tree.insert("600000");
        Tree.insert("1");
        Tree.insert("300");
        Tree.insert("4000");
        Tree.contains("600001");
    }
}
