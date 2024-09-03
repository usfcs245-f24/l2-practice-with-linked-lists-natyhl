package Lab02;

import java.io.File;
import java.util.ArrayList;

public class CircularLinkedList {
	private Node head;
    private Node current;					//current position in the list
    private Node tail; 
    private int size;                       // number of nodes in the list

    public CircularLinkedList() {
    	this.head = null;
    	this.current = null;
    	this.tail = null;
    	this.size = 0;
    }

    public void addNode(File f){
        Node newNode = new Node(f.getName(), f.getAbsolutePath(), f.length()); 

        if(head == null){
            head = newNode;       			
            tail = newNode;					//head and tail point to the same 1 node
            current = head;
            tail.next = head;           	//to ensure circularity
            size++;
            return; 
        }

        Node temp = head; // i = 0 
        while(temp.next!=null){
            temp = temp.next; // i = i + 1 
        }   
        temp.next = newNode;
        tail = temp.next;
        tail.next=head;						//to ensure circularity
        size++;
    }
    
    //returns next node
    public Node next() {
    	if(current!=null) {
    		current=current.next; //***or do I have to have temp here
    		return current;
    	}
    	return current;
    }

    /*public void printList(){
        Node temp = head;
        ArrayList<Node> tempList = new ArrayList<>();
        while(temp!=null && tempList.size() < size){
            tempList.add(temp); 
            temp = temp.next; 
        }
    }*/  //don't need

    public int getSize(){ 
        return size;
    }

}


