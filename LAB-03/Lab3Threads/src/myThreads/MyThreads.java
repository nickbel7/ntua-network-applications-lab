package myThreads;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**The program demonstrates how several threads can run in parallel.
 * Upon running the program, circles appear and move on a canvas.
 * The 1st thread just prints "Hello" to the console, the 2nd one creates new circle objects,
 * adds them to an array list, and paints them, a 3rd one (re)paints the circles to the canvas based on
 * the said list and animates their motion, and the 4th one prints the saved state of the canvas (see "SAVE" button).
 * Also, a timer (a 5th thread) is used for changing the "CLEAR" button color.
 * 
 * @author O.Voutyras
 * 
 */


public class MyThreads extends JFrame implements ActionListener {
	
	// Define named-constants
	private static final int CANVAS_WIDTH = 640;
	private static final int CANVAS_HEIGHT = 480;
	
	// Attributes of moving object
	private int size = 100; // width and height
	private int rad = 10;
	
	private static  boolean isColor=true;  // button
	
	//Object types
	Canvas canvas;
	JButton clear;
	JButton save;
	
	Thread thread3;
	
	ArrayList<myCircle> myCircles;
	Timer timer;
	Graphics2D g2;
	
	private static final long serialVersionUID = -1989891553971170424L;
	
	// Constructor to setup the GUI components, event handlers, and threads
		
	MyThreads() {
		
		// GUI components and event handlers
		
		canvas= new Canvas();
		addCircle();
		clear = new JButton("CLEAR");
		clear.addActionListener(this);
		
		save=new JButton("SAVE");
		save.addActionListener(this);
		
		JPanel  pnl = new JPanel();
		canvas.add(clear);
		canvas.add(save);
		
		this.getContentPane().add(pnl, BorderLayout.NORTH);
		this.getContentPane().add(canvas);
		
		this.setSize(640, 480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Moving Circles");
		this.setVisible(true);
		this.setResizable(false);
		
		
		// Threads
		
		Runnable hello = new DisplayMessage("Hello");
		Thread thread1 = new Thread(hello);
		thread1.setName("hello");
		thread1.setDaemon(true);
		thread1.setPriority(Thread.MIN_PRIORITY);
		System.out.println("Starting " + thread1.getName() +" thread...");
		
		thread1.start();
		
				
		Runnable newcircle = new NewCircle("NewCircle");
		Thread thread2= new Thread(newcircle);
		thread2.setName("newcircle");
		System.out.println("Starting newcircle thread...");
		
		thread2.start();
		
		
		System.out.println("Starting thread3...");
		Thread  thread3 = new Thread(canvas);
		thread3.start();
		
		
		Runnable information = new Information("INFORMATION");
		Thread thread4 = new Thread(information);
		thread4.setPriority(Thread.MIN_PRIORITY);
		thread4.setName("information");
		System.out.println("Starting information thread...");
		thread4.start();
		
		
		// wait for threads to end
	    /*    try
	        {
	            thread1.join();
	            thread2.join();
	            thread3.join();
	            thread4.join();
	        }
	        catch(Exception e)
	        {
	            System.out.println("Interrupted");
	        }*/
	}
	
	
	// For thread 3 (paint circles motion)
	@SuppressWarnings("serial")
	// Define inner class Canvas, which is a JPanel used for custom drawing
	class Canvas extends JPanel implements Runnable {
		
		public Canvas() {
			myCircles = new ArrayList<>();
		}
		
		// Draw a circle
		
		public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D) g;
	   	        
	        for (myCircle circle: myCircles) {
	            int x = circle.getX();
	            int y = circle.getY();
	       
	           Ellipse2D ellipse = new Ellipse2D.Double(x-rad, y-rad, 2*rad, 2*rad);
		       g2.fill(ellipse); 
	        }
	    }
	
	    
		@Override
	    public void run() {
			
			while(true) {
				
				collision();
				repaint();								// Refresh the JFrame, callback paintComponent()
				
				try {
					Thread.sleep(100); 					//Time for next repaint
				}
				catch (InterruptedException e) {
					System.out.println("interrupted");
				}
			
			}   
		}
	
	}
	
	
	// Collision with borders check for moving objects and direction update
    
	public void collision() {
        for (myCircle circle: myCircles) {
            int x = circle.getX();
            int y = circle.getY();

            if(x < 0 || x >  CANVAS_WIDTH - size) {
            	circle.reverseX();
            }
            
            if(y < 0 || y > CANVAS_HEIGHT - size) {
            	circle.reverseY();
            }

            circle.move();
        }
    }
	
	
	public void addCircle() {
    
	    int x = (int )(Math.random() * 560);
	    int y = (int )(Math.random() * 360);
	    int velX = -5;
	    int velY = 5;
	    //int rad=10;
	    myCircles.add(new myCircle(x, y, velX, velY, rad));
	}


	public void removeCircles() {
		myCircles.clear();
	}
		
		
	class myCircle implements Serializable {

		private static final long serialVersionUID = 1L;
		
		int x;
	    int y;
	    int velX;
	    int velY;
	    int rad1;

	    public myCircle(int x, int y, int velX, int velY, int rad) {
	        this.x = (int )(Math.random() * 560);
	        this.y = (int )(Math.random() * 360);
	        this.velX = velX;											// Actually, it's displacement on the x axis
	        this.velY = velY;											// Similarly on the y axis
	        this.rad1=rad;
	    }

	    public void move() {
	        x+=velX;
	        y+=velY;
	    }

	    public int getX() {
	        return x;
	    }

	    public int getY() {
	        return y;
	    }

	    public void reverseX() {
	        velX = -velX;
	    }

	    public void reverseY() {
	        velY = -velY;
	    }
	    
	    
	    // "Formatter"
	    @Override
	    public String toString() {
	        return "myCircle{" +
	                "X='" + x + '\'' +
	                ",Y='" + y + '\'' +
	                ",velX='" + velX +'\'' +
	                ", velY='" + velY +'\'' +
	                ", RAD='" + rad1 +'\''+
	                "}\n";
	    }
	}
	
	
	// handler ActionListener
	@Override
	
	public void actionPerformed(ActionEvent ev) {
		
		if (ev.getSource() == clear) {
			
			System.out.println("BUTTON CLEAR  !!!!");
			removeCircles();
			
			rad=10;
			addCircle();
			repaint();
		}
		
		else
			System.out.println("BUTTON SAVE  !!!!");	
		
		saveList();
     }
	 
	 
	// The entry main method
	public static void main(String args[]) {
		new MyThreads();
	}
	
	 
	// For thread 1 ("hello")
	class DisplayMessage implements Runnable {
		 
		 private String message;

		 public DisplayMessage(String message) {
		    this.message = message;
		 }

		 public void run() {
		
		    while(true) {
		    	try {
					Thread.sleep(2000);
				}
		    	catch (InterruptedException e) {
		    		// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       System.out.println(message);
		    }
		 }
	 }
	 
	 
	 //For thread 4 (print saved list)
	 class Information implements Runnable {
		 
		 private String message;
		 public Information(String message) {
		    this.message = message;
		 }

		 public void run() {
							     
		    while(true) {
		    	try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		    	System.out.println(message);
		       
	 	       readList();
	    
		    }
		 }
	
	 }
	 
	 
	 // For thread 2 (paint new circles)
	 class NewCircle implements Runnable {
			 
		private String message;
		
		public NewCircle(String message) {
			this.message = message;
		}
		
		public void run() {
			
			while(true) {
				
				try {
					Thread.sleep(2000);
				}
				
				catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				
				System.out.println(message);
				      
			    rad =rad +2;
				addCircle();
				canvas.repaint();
				 
			    System.out.println ("myCircles:  " + myCircles.size());
				System.out.println("\nUsing Iterator");
				
				Iterator<MyThreads.myCircle> itr = myCircles.iterator();
				while(itr.hasNext()) {
			    	  MyThreads.myCircle obj = itr.next();
			          System.out.println(obj);
			    }
			
			}
		
		}
	
	 }
	 
	 
	 
	 // For thread 5 (timer and change button color)
	 {
		 Runnable updateButton = new Runnable() {
			 
			 public void run() {
				 
				 if (isColor) {
					 
					 clear.setForeground(Color.RED);	//JBUTTON change color
					 clear.setBackground(Color.BLUE);
					 isColor=false;
				}
				 
				 else {
					 clear.setForeground(Color.BLACK);	//JBUTTON change color
		  			 clear.setBackground(Color.GREEN);
		  			 isColor=true;
		  	 	}
			}
		 };
		 
		 //Use of timer
		 Timer timer = new Timer();
		 
		 timer.schedule(new TimerTask() {
			 
			 @Override
			 public void run() {
				 
				 System.out.println("The timer is working");
				 
				 try {
					 SwingUtilities.invokeAndWait(updateButton);
				}
				 
				 catch (InvocationTargetException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
				}
				 
				 catch (InterruptedException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
				}
			}
		},1000, 4000);	//wait 1000 ms before doing the action and do it every 4000ms (4seconds)
	
	 }
	 
	 
	 private void saveList() {
		 // TODO Auto-generated method stub
		 try {
			 FileOutputStream writeData = new FileOutputStream("circledata.ser");
			 ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
			 
			 writeStream.writeObject(myCircles);
			 writeStream.flush();
			 writeStream.close();
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
	 private void readList() {
		 
		 try{
			 FileInputStream readData = new FileInputStream("circledata.ser");
			 ObjectInputStream readStream = new ObjectInputStream(readData);
			 
			 @SuppressWarnings("unchecked")
			 ArrayList<myCircle> myCircles2 = (ArrayList<myCircle>) readStream.readObject();
			 
			 readStream.close();
			 
			 System.out.println(myCircles2);  //.toString());
		}
		
		catch (IOException | ClassNotFoundException e) {
		            e.printStackTrace();
		}
		 
	}
		
}