package MultiThreading;

class SharedDatas {
    int sharedValue = 0; 
}

class UsingRunnable implements Runnable {

    private final SharedDatas data;

    public UsingRunnable(SharedDatas data) {
        this.data = data;
    }

    @Override
    public void run() {
        int localValue = 0;

        String threadName = Thread.currentThread().getName();

        for (int i = 0; i <= 3; i++) {
            localValue++;
            data.sharedValue++;

            System.out.println(threadName + " at i: " + i + 
                               " localValue: " + localValue + 
                               " sharedValue: " + data.sharedValue);
        }
    }
}

public class UsingRunnableExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 SharedDatas data = new SharedDatas(); // shared object and instance variable is stored in heap

	        // Create Runnable tasks
	        Runnable task1 = new UsingRunnable(data);
	        Runnable task2 = new UsingRunnable(data);

	        // Create Thread objects and associate them with the Runnable tasks
	        Thread t1 = new Thread(task1, "Thread 1");
	        Thread t2 = new Thread(task2, "Thread 2");

	        // Start both threads
	        t1.start();
	        t2.start();

	}

}
