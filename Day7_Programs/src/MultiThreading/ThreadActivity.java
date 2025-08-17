// WAP to  create two users user-a  and user-b to perform some task (FileUpload)( they both are thread thread-A and thread-B  )
//Main thread will wait for both users before showing the task is completed of user-a and user b

package MultiThreading;
	
class FileUpload extends Thread{
	private String userName;
	
	public FileUpload(String userName) {
		this.userName = userName;
	}
	
	@Override
	public void run() {
		System.out.println(userName+" started uploading file ");
		try {
			Thread.sleep(2000);
		}
		catch(InterruptedException e) {
			System.out.println(userName + " Interrupted");
		}
		System.out.println(userName+" finished uploading");
	}
}

public class ThreadActivity {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		FileUpload userA = new FileUpload("Thread-A");
		FileUpload userB = new FileUpload("Thread-B");
		
		userA.start();
		userA.join();
		userB.start();
		userB.join();
//		try {
//			userA.join();
//			userB.join();
//		}
//		catch(InterruptedException e) {
//			System.out.println(" Interrupted");
//		}
		System.out.println("Task Completed");

	}

}
