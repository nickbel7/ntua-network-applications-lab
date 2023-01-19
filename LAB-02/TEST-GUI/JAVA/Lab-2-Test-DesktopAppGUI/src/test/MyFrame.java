package test;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A JFrame with the elements presented in your screen
 */
public class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String TITLE = "My First Frame";
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 300;
	
	public MyFrame() {
		
		super(TITLE);

		// TODO: Place the Frame in the middle of your screen
		
		// Specify Frame Location along with its Dimensions
		this.setBounds(100, 100, WIDTH, HEIGHT);
		
		
	    /* COMPONENTS & STYLING */
	    
	    final JLabel label = new JLabel("Type something:"); 
	    // TODO: Increase Font Size
	    
	    final JTextField textField = new JTextField(40);
	    // TODO: Add Padding 10px
	    
	    final JButton button = new JButton("Press me");
	    // TODO: Change Border Color
	    
	    final JTextArea textArea = new JTextArea(5, 45);
	    // TODO: Make Text Area not Editable
	    
	    // TODO: Add the NTUA Logo in your Window
	    
	    final MyDrawingArea myDrawringArea = new MyDrawingArea();
	    
	    /* ADD LISTENERS TO COMPONENTS */
	    
	    button.addActionListener(
	    	// The definition of this Class exists at the End of this File
	    	new MyButtonListener(textField, textArea, myDrawringArea)
	    );
	    
	    // TODO: Add KeyListener to Text Field
	    // Print a message when a predefined phrase is typed
	    
	    /* ADD COMPONENTS to JFrame */
	    
	    final JPanel panel = new JPanel();
	    
	    // TODO: Check Different Panel Layout Methods (panel.setLayout)
	    
	    panel.add(label);
	    panel.add(textField);
	    panel.add(button);
	    panel.add(textArea);
	    panel.add(myDrawringArea);
	    
	    this.setContentPane(panel);
	
	} // END OF MyFrame()
	
	
	// TODO: GUI About File Selection
	// Add another button about Selecting a File from your Local Hard Disk
	// Implement the appropriate Action Listener
	// Present a new Window for selecting the appropriate File
	// Useful Class name: JFileChooser
	// The Path of the selected File should be also presented in the Text Area
	
	
    // TODO: Your Drawing Area
	// Create a Chess Matrix
    // Create an oval for a pawn
    // You should be able to move your pawn with your keyboard buttons
    	
}
