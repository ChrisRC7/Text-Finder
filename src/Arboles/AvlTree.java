package Arboles;

public class AvlTree<T extends Comparable<T>> {

    public AvlNode<T> root;
    int NumComparaciones=0;
    int CantidadResultados=0;
    int Cantidad=0;
    String DocName;

    private static final int ALLOWED_IMBALANCE = 1;

    public AvlTree() {
        root = null;
    }

    public void altura(){
        System.out.println(Cantidad);
    }

    public void insert(T val) {
        Cantidad++;
        root = insert(val, root);
    }

    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }

    private AvlNode<T> insert(T element, AvlNode<T> current) {

        if (current == null) {
            return new AvlNode<T>(element);
        }
        int compareResult= element.compareTo(current.element);
        if (compareResult< 0) {
            current.left = insert(element, current.left);

        }

        if (compareResult >= 0) {
            current.right = insert(element, current.right);
        }

        return balance(current);
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
            NumComparaciones=0;
            CantidadResultados=0;
            return this.contains(element, this.root);
        }

        private boolean contains(T element, AvlNode<T> node) {
            if (node == null) {
                return false;
            }
            int compareResult = element.compareTo(node.element);
            NumComparaciones++;
            if (compareResult < 0){
                return contains(element, node.left);
            }
            if (compareResult >= 0){
                if (element.equals(node.element)){
                    CantidadResultados++;
                }
                return contains(element, node.right);
            }
            else{
                System.out.println("No esta; es el print del de abajo");
                return false;
                }          
            }
        
        public int GetResultados(){
            return CantidadResultados;
        }
            

        public int GetComparaciones(){
            return NumComparaciones;
        }
            
        public void SetDocName(String Doc) {
            DocName= Doc;
        }

        public String GetDocName() {
            return DocName;
        }

        public void Datos(){
            this.inOrder(this.root);
        }
        
        private void inOrder(AvlNode<T> root) {
            if (root!=null) {
                System.out.println(root.element);
                this.inOrder(root.right);
                this.inOrder(root.left);
                
            }
        }
        
        public void Buscar(T element){
            AvlNode<T> current = this.root;
            while(current!=null){
                int actualresult= element.compareTo(current.element);
                if (actualresult>=0){
                    if (element.equals(current.element)){
                        System.out.println("Esta");
                    }
                    current= current.right;
                }
                if (actualresult<0){
                    current= current.left;
                }
            }
            System.out.println("Se termino el recorrido");
        }

}

