package Lab02;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PhotoViewer extends JFrame {
	CircularLinkedList myAlbum;  		//uses CircularLinkedList class
	JLabel imageL;

    public PhotoViewer(CircularLinkedList album) {
    	this.myAlbum=album;
    	this.imageL = new JLabel();		//***suggestion from chatGPT
        this.imageL.setHorizontalAlignment(JLabel.CENTER); 
    	
        setTitle("Photo Viewer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        add(imageL, BorderLayout.CENTER);

        JButton button1 = new JButton("next");

        button1.addActionListener(new Button1Listener());

        JPanel myPanel = new JPanel(); //***got help from chatGPT
        myPanel.add(button1);
        add(myPanel, BorderLayout.SOUTH);
        
        if (myAlbum.getSize() > 0) { 
            displayNext(); // Display the first image on startup
        } else {
            imageL.setText("No images available.");
        }
    }

    class Button1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("'Next' was pressed");
            displayNext();
        }
    }
    
    //resource used: https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
    public void display(Node image) {
		try {
			BufferedImage img = ImageIO.read(new File(image.fullPath));
		    Image scaledImage = img.getScaledInstance(700, 500,Image.SCALE_DEFAULT);  //using getScaledInstance
		    ImageIcon imageIcon = new ImageIcon(scaledImage);
		    imageL.setIcon(new ImageIcon(scaledImage)); 
		    setTitle("Photo Viewer - " + image.nameOfFile); 
		} catch (IOException e) {
			System.err.println("Message: "+e); 
			imageL.setIcon(null);
		}
    }
    
    public void displayNext() { 
    	Node n = myAlbum.next(); //give me the next Node in list
    	if (n != null) {
            display(n);
        } else {
        	System.out.println("No image available.");
            imageL.setText("No image available.");
        }
    }

    public static void main(String[] args) throws IOException {
    	CircularLinkedList myAlbum = new CircularLinkedList(); 
        String pathToImages = args[0];
        
        //source: https://www.tutorialspoint.com/how-to-get-list-of-all-files-folders-from-a-folder-in-java#:~:text=The%20ListFiles()%20method,file%2Fdirectory%20in%20a%20folder.
        File directory = new File(pathToImages);                //Creating a File object for directory
        File[] content = directory.listFiles(); //List of all files and directories
        
        
        for(File f: content) {
        	System.out.println(f);
            myAlbum.addNode(f);
        }
    	
    	SwingUtilities.invokeLater(() -> { 
            PhotoViewer ui = new PhotoViewer(myAlbum);
            ui.setVisible(true);
        });
    }
}
