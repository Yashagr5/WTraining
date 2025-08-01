package ErrorHandling;
import java.util.*;
public class BankMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the account Holder name:");
		String name = sc.next();
		System.out.println("Enter the amount to open your account :");
		double bal = sc.nextDouble();
		BankAccount acc = new BankAccount(name, bal);
		
		try
		{
			System.out.println("Enter the amount to withdraw :");
			double amt = sc.nextDouble();
			acc.withdraw(amt); // It will call withdraw method with the parameter value from BankAccount class 
		}
		
		
		catch(InsufficientFundsExcept e)
		{
		
		System.out.println(e.getMessage());
	}
		
		catch(IllegalArgumentException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		System.out.println("Final Balance in your account is :" + acc.getBalance());

	}

}
