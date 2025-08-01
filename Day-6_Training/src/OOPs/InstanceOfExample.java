package OOPs;

class Persons{
	
}
class Employee extends Persons{
	void action() {
		System.out.println("Job");
	}
}
class Student extends Persons{
	void action() {
		System.out.println("Study");
	}
}
public class InstanceOfExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Persons p = new Employee();

if(p instanceof Employee) {
	Employee e = (Employee)p; // safe downcast
	e.action();
}
else {
Student s = (Student)p;
s.action();
}
	}

}
