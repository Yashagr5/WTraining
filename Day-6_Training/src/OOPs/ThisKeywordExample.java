package OOPs;

class Public {
	int id;
	String name;
	
	public Public(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public Public() {
		id= 101;
		name="Yash";
	}
	
	void display() {
		System.out.println("ID: "+id+" Name: "+ name);
	}
	static
	{

//	  Connection con = new Connection();
//	static String url ="";	
//	static String sqluser = "";
//	static String sqlpassword ="";
	System.out.println("Static Block Called :");	
	}
}

public class ThisKeywordExample {

	public static void main(String[] args) {
		Public p = new Public(102, "mohit");
		p.display();

	}

}
