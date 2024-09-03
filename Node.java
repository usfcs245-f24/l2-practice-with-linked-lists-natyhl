package Lab02;
public class Node {
	String nameOfFile;
	String fullPath;
    long sizeOfFile;
    Node next;

    //constructor
    public Node(String name, String path, long size){
        this.nameOfFile = name; 
        this.fullPath=path;
        this.sizeOfFile=size;
        this.next = null; 
    }

    public String getName(){
        return this.nameOfFile; 
    }
}
