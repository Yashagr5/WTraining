package Practice;

class Employee {
	int id;
	String Name;
	int age;
	String Designation;
	
	public Employee(int id, String Name, int age, String Designation) {
		this.id = id;
		this.Name = Name;
		this.age = age;
		this.Designation = Designation;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", Name=" + Name + ", age=" + age + ", Designation=" + Designation + "]";
	}
	
}

public class p7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee e = new Employee(2101, "Yash", 23, "Developer");
		
		

	}

}
