package ListasEnlazadas;

public class Double_CircularLinkedList {
    private Node head;
    private Node last;
    private int size;

    public Double_CircularLinkedList(){
        this.head = null;
        this.last = null;
        this.size = 0;

    }

    
    /** 
     * @return boolean
     */
    public boolean isEmpty(){
        return this.head == this.last && this.head== null;
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

        if (this.isEmpty()){
            this.head= this.last = newNode;
        } else{
            this.head.setPrevious(newNode);
            newNode.setNext(this.head);
            newNode.setPrevious(this.last);
            this.last.setNext(newNode);
            this.head = newNode;
        }
        this.size++;
    }

    
    /** 
     * @param data
     */
    public void insertLast(Object data){
        Node newNode= new Node(data);

        if (this.isEmpty()){
            this.head = this.last = newNode;
        } else {
            this.last.setNext(newNode);
            newNode.setNext(this.head);
            newNode.setPrevious(this.last);
            this.head.setPrevious(newNode);
            this.last= newNode;
        }
        this.size++;
    }

    
    /** 
     * @param searchValue
     */
    public void Delete (Object searchValue){
        Node current = this.head;
        Node previous = this.head;

        if (current==this.head) {
            this.head.getNext().setPrevious(this.head.getPrevious());
            this.head.getPrevious().setNext(this.head.getNext());
            this.head= this.head.getNext();
        } else {
            current = current.getNext();
            while(current!=this.head){
                if (current.getData().equals(searchValue)){
                    previous.setNext(current.getNext());
                    current.getNext().setPrevious(previous);
                }else{
                    previous = current;
                    current = current.getNext();
                }
            }
         }
}

    
    /** 
     * @param searchValue
     * @return Object
     */
    public Object GetNext (Object searchValue) {
        Node current = this.head;

        while(current!=null) {
            if (current.getData().equals(searchValue)) {
                break;
            } else {
                current = current.getNext();
            }
        } return current.getNext().getData();
        
    }

    
    /** 
     * @return Object
     */
    public Object GetHead() {
        
        return this.head.getData();
    }

    public void Restart(){
        this.head = null;
        this.last = null;
        this.size = 0;
    }

    
    /** 
     * @param searchValue
     * @return Object
     */
    public Object GetPrevious (Object searchValue) {
        Node current = this.head;
      

        while (current!=null) {
            if (current.getData().equals(searchValue)) {
                break;
            } else {
                current = current.getNext();
            }
        } return current.getPrevious().getData();
    }


    public void displayList(){
        Node current= this.head;
        System.out.println(current.getData());
        current= current.getNext();
        while (current!=this.head){
            System.out.println(current.getData());
            current= current.getNext();
        }
    }
    
}
