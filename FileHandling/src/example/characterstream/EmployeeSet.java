package example.characterstream;

public class EmployeeSet {
	private int id;
	private String name;
	private double salary;
	EmployeeSet(int id, String name, double d){
		this.id=id;
		this.name = name;
		this.salary = d;
	}
	
public EmployeeSet() {
	
}

	public int getId() {
	return id;
}


public String getName() {
	return name;
}


public double getSalary() {
	return salary;
}

	@Override
	public String toString() {
		return "EmployeeSet [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
