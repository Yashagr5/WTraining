package set_example;

public class EmployeeSet {
	private int id;
	private String name;
	private int salary;
	EmployeeSet(int id, String name, int salary){
		this.id=id;
		this.name = name;
		this.salary = salary;
	}
	
public EmployeeSet() {
	
}

	public int getId() {
	return id;
}


public String getName() {
	return name;
}


public int getSalary() {
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
