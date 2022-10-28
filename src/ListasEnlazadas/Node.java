package ListasEnlazadas;


public class Node {
    private Object data;
    private Node next;
    private Node previous;

    public Node(Object data){
        this.next = null;
        this.previous = null; 
        this.data = data;
    } 

    
    /** 
     * @return Object
     */
    public Object getData(){
        return this.data;
    }

    
    /** 
     * @param data
     */
    public void setData(Object data){
        this.data= data;
    }

    
    /** 
     * @return Node
     */
    public Node getNext(){
        return this.next;
    }

    
    /** 
     * @return Node
     */
    public Node getPrevious(){
        return this.previous;
    }

    
    /** 
     * @param node
     */
    public void setNext(Node node){
        this.next= node;
    }

    
    /** 
     * @param node
     */
    public void setPrevious(Node node){
        this.previous= node;
    }
        
    
}
