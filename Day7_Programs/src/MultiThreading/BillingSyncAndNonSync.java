/* synchronous -- User A ->  userB --> user c can place the order in sequence ()

orders are independent , but uses wait() / notify() to coordinate the billing readiness once the billing is complete

for each user , send a notification 

*

*/

package MultiThreading;

class BillingSystem{
	private boolean billingDone = false;
	
	public synchronized void placeOrder(String userName) {
		System.out.println(userName+" Place Order");
		billingProcess(userName);
		billWaiting(userName);
	}
	
	private synchronized void billingProcess(String userName) {
		System.out.println("Billing for: "+ userName);
		try {
			Thread.sleep(1100);
		}
		catch(InterruptedException e) {
			System.out.println("Error: "+e.getMessage());
		}
		billingDone=true;
		System.out.println("Billing Started");
		notify();
	}
	
	private synchronized void billWaiting(String userName) {
		while(!billingDone) {
			try {
				wait();
			}
			catch(InterruptedException e) {
				System.out.println("Error: "+e.getMessage());
			}
		}
		System.out.println("Billing Completed");
		billingDone = false;
	}
}

class User extends Thread{
	private final String userName;
	private final BillingSystem billingSystem;
	
	public User(String userName, BillingSystem billingSystem) {
		this.userName = userName;
		this.billingSystem = billingSystem;
	}
	
	public void run() {
		billingSystem.placeOrder(userName);
	}
}

public class BillingSyncAndNonSync {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-gener	ated method stub
		BillingSystem billingSystem = new BillingSystem();
		User userA = new User("UserA", billingSystem);
		User userB = new User("UserB", billingSystem);
		User userC = new User("UserC", billingSystem);
		
		userA.start();
		userA.join();
		
		userB.start();
		userB.join();
		
		userC.start();
		userC.join();

		System.out.println("All Done");
	}

}
