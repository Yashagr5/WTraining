package MultiThreading;

public class WithoutThreadExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int localValue = 0;
int sharedValue = 0;

for(int i=1; i<3; i++) {
	localValue++;
	sharedValue++;
	System.out.println("at i :"+i+" the local value is: "+localValue+" teh Shared Value is: "+sharedValue);
}
System.out.println("Final Shared Value is: "+sharedValue);
	}

}
