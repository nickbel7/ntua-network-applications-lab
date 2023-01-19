package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

/**
 * A JComponent with your Drawing Area
 */
public class MyDrawingArea extends JComponent {
    
	private static final long serialVersionUID = 1L;

	int index = -1;
    	
   	public MyDrawingArea() {
   		setPreferredSize(new Dimension(450, 100));
   		setBorder(BorderFactory.createLineBorder(Color.black));
   	}
    	
   	public void setIndex(int index) {
   		this.index = index;
   	}
    	
   	public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
            
        int width = getWidth();
			
		int size = 40;
        int space = 10;
        int n = width / (size + space);
			
        // Top Line
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawLine(space/2, space, width, space);
	        
        // Rectangles
	        
        for (int i = 0; i < n; i++) {
        	if (i == index) {
        		g2.setColor(Color.BLUE);
        	} else {
        		g2.setColor(Color.WHITE);
        	}
        	g2.fillRect(i * (size + space) + space/2, 2*space, size, size);
		}
	        
        // Bottom Line
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawLine(space/2, 3 * space + size, width, 3 * space + size);
	        
  	} // END OF paintComponent(..)

} // END OF MyDrawingArea
