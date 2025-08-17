package MultiThreading;

public class ThreadInterfaceExample implements Runnable{
	
	@Override
	public void run() {
		System.out.println("Thread is running: "+	Thread.currentThread().getName());
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThreadInterfaceExample tr= new ThreadInterfaceExample();
		Thread tc = new Thread(tr);
		tc.setName("Thread 1");
		tc.start();

	}

}
