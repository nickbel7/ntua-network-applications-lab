

/** A simple program that demonstrates how threads can run one after another.
 * In the first "JoinThreads" class (in comments) three threads run simultaneously,
 * but with random order. In the second version of "JoinThreads" (out of comments),
 * the threads run in specific order.*/


/*public class JoinThreads {
   public static void main(String[] args) {
      Thread th1 = new Thread(new MyClass(), "th1");
      Thread th2 = new Thread(new MyClass(), "th2");
      Thread th3 = new Thread(new MyClass(), "th3");
         
      th1.start();
      th2.start();        
      th3.start();
   }
}*/


public class JoinThreads {
	
	public static void main(String[] args) {
		
		Thread th1 = new Thread(new MyClass(), "th1");
		Thread th2 = new Thread(new MyClass(), "th2");
		Thread th3 = new Thread(new MyClass(), "th3");
		
		// Start first thread immediately
		th1.start();
		
		//Start second thread(th2) once first thread(th1) is dead
		
		try {
			th1.join();
		}
		
		catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		th2.start();
		
		// Start third thread(th3) once second thread(th2) is dead
		
		try {
			th2.join();
		}
		
		catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		th3.start();
		
		// Displaying a message once third thread is dead
		
		try {
          th3.join();
      }
		catch (InterruptedException ie) {
            ie.printStackTrace();
        }
		
      System.out.println("All three threads have finished execution");
   }
}


class MyClass implements Runnable {
 
    @Override
    public void run() {
    	Thread t = Thread.currentThread();
        System.out.println("Thread started: "+t.getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("Thread ended: "+t.getName());    
    }
}

