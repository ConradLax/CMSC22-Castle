package castle;

public class TheGame implements Runnable{
	private boolean running = false;
	public Thread thread;
	
	
	public synchronized void start2() {
		if(running) return;
		running = true;
		Thread thread = new Thread(this);
		thread.start();
	}
	public void run() {
		System.out.println("Thread has begun");
	}
}
