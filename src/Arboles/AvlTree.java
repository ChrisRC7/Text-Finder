package Arboles;

public class AvlTree<T extends Comparable<T>> {

    public AvlNode<T> root;
    int NumComparaciones=0;
    int CantidadResultados=0;
    int Cantidad_de_Datos=0;
    String DocName;

    private static final int ALLOWED_IMBALANCE = 1;

    public AvlTree() {
        root = null;
    }

    public void altura(){
        System.out.println(Cantidad_de_Datos);
    }

    public void insert(T val) {
        this.Cantidad_de_Datos++;
        this.root = insert(val, this.root);
    }

    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }

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

    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        return k1;
    }

    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        return k1;
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> k3) {
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }

    public boolean contains(T element) {
        this.NumComparaciones=0;
        this.CantidadResultados=0;
        return this.contains(element, this.root);
    }

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
                    
            
        
    public int GetResultados() {
        return this.CantidadResultados;
    }
            

    public int GetComparaciones() {
        return this.NumComparaciones;
    }
            
    public void SetDocName(String Doc) {
        this.DocName= Doc;
    }

    public String GetDocName() {
        return this.DocName;
    }

    public void Datos() {
        this.inOrder(this.root);
    }
        
    private void inOrder(AvlNode<T> root) {
        if (root!=null) {
            System.out.println(root.element);
            this.inOrder(root.right);
            this.inOrder(root.left);
                
        }
    }

}

