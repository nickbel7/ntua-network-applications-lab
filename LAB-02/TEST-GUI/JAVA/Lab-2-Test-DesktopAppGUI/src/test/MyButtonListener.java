package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *	An Action Listener for the your Button 
 */
public class MyButtonListener implements ActionListener {

	private JTextField textField;
	private JTextArea textArea;
		
	private MyDrawingArea myDrawringArea;
		
	public MyButtonListener(JTextField textField, JTextArea textArea, MyDrawingArea myDrawringArea) {
		this.textField = textField;
		this.textArea = textArea;
		this.myDrawringArea = myDrawringArea;
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
			
		if (this.textField.getText().trim().equals("")) {
			// A Simple Frame
			JOptionPane.showMessageDialog(null, "Input Field is emtpy !", "Error Message", JOptionPane.ERROR_MESSAGE);
			
			// TODO: Also use JOptionPane.showOptionDialog and Print the option selected in the Text Area
				
		} else {
			//System.out.println("Button Pressed... TextField Value: " + this.textField.getText());
			this.textArea.setText( this.textField.getText() );
				
			// TODO: Update the Text Area with the given Input (not remove the old one)
		}
			
		myDrawringArea.setIndex(this.textField.getText().length());
		myDrawringArea.repaint();

	} // END OF actionPerformed(..)
		
} // END OF ButtonListener

