package ListasEnlazadas;

public class LinkedList {
    private Node head;
    private Node last;
    private int size;

    public LinkedList(){
        this.head= null;
        this.last= null;
        this.size= 0;
    }

    
    /** 
     * @return boolean
     */
    public boolean isEmpty(){
        return this.head==null;
    }
    
    
    /** 
     * @return int
     */
    public int size(){
        return this.size;
    }

    
    /** 
     * @param data
     */
    public void insertFirst(Object data){
        Node newNode= new Node(data);
        if (this.isEmpty()) {
            this.head= this.last= newNode;
        } else {
            newNode.setNext(this.head);
            this.head= newNode;
            this.size++;
        }
    }

    public void insertLast(Object data){
        Node newNode= new Node(data);

        if (this.isEmpty()){
            this.head = this.last = newNode;
        } else {
            this.last.setNext(newNode);
            this.last= newNode;
        }
        this.size++;
    }

    
    /** 
     * @return Node
     */
    public Node deleFirts(){
        if(this.head!=null){
            Node temp= this.head;
            this.head= this.head.getNext();
            this.size--;
            return temp;
        } else{
            return null;
        }
    }

    public void displayList(){
        Node current= this.head;
        while (current!=null){
            System.out.println(current.getData());
            current= current.getNext();
        }
    }

    public Object GetHead() {
        
        return this.head.getData();
    }

    public boolean InsertLastUnique(Object data) {
        Node current= this.head;
        while(current!=null){
            if(current.getData().equals(data)){
                return false;
            } else {
                current = current.getNext();
            }
        }
        insertLast(data);
        return true;
    }

    public Object GetNext (Object searchValue) {
        Node current = this.head;

        while(current!=null) {
            if (current.getData().equals(searchValue)) {
                break;
            } else {
                current = current.getNext();
            }
        }
        if (current.getNext()!=null){
            return current.getNext().getData();
        } else {
            return null;
        } 
        
        
    }
}
