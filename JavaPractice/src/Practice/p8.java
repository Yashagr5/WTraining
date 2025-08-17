package Practice;

 class StaticNonStatic {
	int count=0;
	
	StaticNonStatic(){
		count++;
	}
	
	void display() {
		System.out.println("Count: "+count);
	}
}

public class p8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StaticNonStatic sns = new StaticNonStatic();
//		System.out.println("Count1: "+ sns);
		sns.display();

		StaticNonStatic sns1 = new StaticNonStatic();
//		System.out.println("Count2: "+ sns1);
		sns1.display();

	}

}
