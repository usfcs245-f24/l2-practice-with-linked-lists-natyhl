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
            this.head = newNode;       			
            this.tail = newNode;					//head and tail point to the same 1 node
            this.current = head;
            this.tail.next = head;           	//to ensure circularity
            this.size++;
            return; 
        }

        Node temp = this.head; // i = 0 
        while(temp.next!=head){
            temp = temp.next; // i = i + 1 
        }   
        temp.next = newNode;
        this.tail = temp.next;
        this.tail.next=head;						//to ensure circularity
        this.size++;
    }
    
    //returns next node
    public Node next() {
    	if(this.current!=null) {
    		this.current=this.current.next; //***or do I have to have temp here
    		return this.current;
    	}
    	return this.current;
    }

    public void printList(){
        Node temp = head;
        while(temp!=null){
            System.out.println(temp.nameOfFile); 
            temp = temp.next; 
        }
    }  //don't need

    public int getSize(){ 
        return size;
    }
    public Node getHead(){ 
        return this.head;
    }

}


