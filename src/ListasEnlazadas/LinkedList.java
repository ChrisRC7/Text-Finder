// A package declaration.
package ListasEnlazadas;

/**
 * This class represents a linked list of nodes.
 */
public class LinkedList {
    private Node head;
    private Node last;
    private int size;

   // The constructor of the class.
    public LinkedList(){
        this.head= null;
        this.last= null;
        this.size= 0;
    }

    
    
    /**
     * @return The method isEmpty() returns a boolean value.
     */
    public boolean isEmpty(){
        return this.head==null;
    }
    
    

    /**
     * This function returns the size of the list
     * 
     * @return The size of the array.
     */
    public int size(){
        return this.size;
    }

    
 /**
  * If the list is empty, set the head and last to the new node. Otherwise, set the new node's next to
  * the head, set the head to the new node, and increment the size
  * 
  * @param data the data to be inserted
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

    /**
     * If the list is empty, set the head and last to the new node. Otherwise, set the next of the last
     * node to the new node and set the last to the new node
     * 
     * @param data the data to be inserted
     */
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
     * Deleting the first node of the list.
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

   /**
    * The function starts at the head of the list and prints out each node's data until it reaches the
    * end of the list
    */
    public void displayList(){
        Node current= this.head;
        while (current!=null){
            System.out.println(current.getData());
            current= current.getNext();
        }
    }

    /** 
     * @return Object
     * A method that returns the data of the head node.
     */
    public Object GetHead() {
        
        return this.head.getData();
    }

    /** 
     * @param data
     * @return boolean
     * Checking if the data is already in the list.
     */
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

    /** 
     * @param searchValue
     * @return Object
     * Searching for the next node after the one that is given as a parameter.
     */
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

    
    /** 
     * @param searchValue
     * Deleting a node from the list.
     */
    public void Delete (Object searchValue){
        Node current = this.head;
        Node previous = this.head;

        if (current==this.head && current.getData().equals(searchValue)) {
            this.head= this.head.getNext();
        } else {
            current = current.getNext();
            while(current!=null){
                if (current.getData().equals(searchValue)){
                    previous.setNext(current.getNext());
                    break;
                }else{
                    previous = current;
                    current = current.getNext();
                }
            }
        }
    }
    
}
