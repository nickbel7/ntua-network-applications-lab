package test;

import javax.swing.JFrame;

public class ProgramMain {

	public static void main(String[] args) {

		System.out.println(">> ProgramMain: START");
		
		// Create and Show your Frame
		final MyFrame frame = new MyFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setVisible(true);
		
		System.out.println(">> ProgramMain: END");
	}

}
