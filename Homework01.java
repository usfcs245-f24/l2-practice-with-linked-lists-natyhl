import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Homework01{

    static class Customer extends Homework01{ //?
        String name;
        ArrayList<Book> listOfBooks;
        static ArrayList<Book> booksOffered = new ArrayList<>();

        public Customer(String customerName){
            this.name = customerName;
            this.listOfBooks=new ArrayList<Book>();
        }

        public void addBooksOffered(){
            booksOffered.add(new Book("Strangers To Straba", "Carl Jacobi"));
            booksOffered.add(new Book("Two Way Destiny", "Frank Belknap Long"));
            booksOffered.add(new Book("Once A First Wife", "Norman Arkawy"));
            booksOffered.add(new Book("Office Call", "Charles E. Fritch"));
            booksOffered.add(new Book("Danish Ballads", "E. M. Smith-Dampier"));
        }
        

        //List books in their library
        public ArrayList<Book> getListOfBooks(){
            return listOfBooks;
        }

        //List all the books available in the inventory. There should be at least 5 books in the inventory/book store.
        public ArrayList<Book> getbooksOffered(){
            return booksOffered;
        }
    }

    static class Book extends Homework01{
        String title;
        String author;
        ArrayList<String> content;
        int positionInBook;

        public Book(String bTitle, String bAuthor){
            this.title=bTitle;
            this.author=bAuthor;
            this.positionInBook=0;
            this.content = new ArrayList<String>();
        }

        public String getTitle(){
            return title;
        }

        public String getAuthor(){
            return author;
        }

        public void loadContent(String bookName){
                    String filenameString="";
            if(bookName.equals("Strangers To Straba")){
                filenameString = "strangerstostraba.txt";
            }else if(bookName.equals("Two Way Destiny")){
                filenameString = "twowaydestiny.txt";
            }else if(bookName.equals("Once A First Wife")){
                filenameString = "onceafirstwife";
            }else if(bookName.equals("Office Call")){
                filenameString = "officecall";
            }else if(bookName.equals("Danish Ballads")){
                filenameString = "danishballads.txt";
            }else {
                System.out.println("*Book not found.*");
            }
            
            

                    try (BufferedReader reader = new BufferedReader(new FileReader(filenameString))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            content.add(line);
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                    }
                }
 

        public void readBook(String direction, int position){
            if(direction.equals("forward")){
                for(int i=0;i<20;i++){
                    System.out.println(content.get(position));
                    position++;
                }
            }else{
                for(int i=position;i<20;i++){
                    System.out.println(content.get(position));
                    position--;
                } 
            }
        }
    }

     //Print all the lines in a book that match a specified string
     public String foundStrings(String word, String input){
        Scanner scanner1 = new Scanner(input);
        String answer = "";
        while(scanner1.hasNextLine()){
            if(!(scanner1.findInLine(word).equals(null))){
                answer+=scanner1.nextLine()+"/n";
            }else{
                scanner1.nextLine();//is this correct?
            }
        }
        return answer;
    }

  //'Purchase' a book using the book title
    static public String purchaseBook(Customer c,String bookName){
        for(Book b: c.booksOffered) {
            if(b.title.equals(bookName)) {
                if(c.listOfBooks.size()==0) {
                    c.listOfBooks.add(b);
                    return "Book purchased.";       
                }else{
                    for(Book x: c.listOfBooks){
                        if(x.title.equals(bookName)) {
                            return "Book already purchased.";
                            
                        }else{
                            c.listOfBooks.add(b);
                            return "Book purchased.";
                        }
                    }
                }
            }
        }
        return "A book with such name is not offered.";
    }
    
        public static void main(String[] args){
            System.out.println("Hello user. Please type in your name:");
            Scanner scanner2 = new Scanner(System.in);
            String cName = scanner2.nextLine();
            Customer c1 = new Customer(cName);
            c1.addBooksOffered(); //load library
            while(true){
                System.out.println("What would you like to do today?");
                System.out.println("1) List all books available in inventory");
                System.out.println("2) List all books in my library");
                System.out.println("3) Purchase a book");
                System.out.println("4) Read book in your library");
                String answr = scanner2.nextLine();

                if(answr.equals("1")){
                    ArrayList<Book> booksOffered = c1.getbooksOffered();
                    for(Book b: booksOffered){
                        System.out.println(b.title+"by"+b.author);
                    }
                    System.out.println();
      
                }else if(answr.equals("2")){
                    
                    if(c1.listOfBooks.size()==0) {
                        System.out.println("Your library is empty. You have not purchased any books yet.");
                        System.out.println();
                    }else {
                        ArrayList<Book> booksPurchased = c1.getListOfBooks();
                        for(Book b: booksPurchased){
                            System.out.println(b.title+" by "+b.author);
                        }
                    }
    
                }else if(answr.equals("3")){ //Purchase book that is available and is not in your purchased books
                    System.out.println("What is the title of the book you want to purchase?");
                    String input = scanner2.nextLine();
                    System.out.println(purchaseBook(c1,input));
                    

                //find a book with this name in purchased and read it
                }else if(answr.equals("4")){
                    System.out.println("What is the title of the book you want to read?");
                    String input = scanner2.nextLine();
                    if(c1.listOfBooks.size()==0) {
                        System.out.println("Book not purchased.");
                    }else{
                    for(Book b: c1.listOfBooks){
                        if(b.title.equals(input)) {
                            b.loadContent(b.title);
                            System.out.println("Would you like to read following or previous page?");
                            String answer = scanner2.nextLine();
                            if(answer.equals("following")) {
                                b.readBook("forward", b.positionInBook);//reading forward
                            
                            }else if(answer.equals("previous"))  {
                                b.readBook("backward", b.positionInBook);//reading backward
                            }else {
                                System.out.println("Invalid direction.");
                            }
                        }else {
                            System.out.println("Book not purchased.");
                        }
                    }
                    }
                }else{
                    System.out.println("This option doesn't exist."); 
                
                }
            }
    }
}



