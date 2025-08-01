package collectionFrameworks;

public class Employeee {

		private int id;
		 private String name;
		 private double Salary;


		 public Employeee(int id, String name, double salary) {
		  super();
		  this.id = id;
		  this.name = name;
		  Salary = salary;
		 }
		 public Employeee() {
			 
		 }
		 public int getId() {
		        return id;
		    }


		 @Override
		 public String toString() {
		  return "Employeee [id=" + id + ", name=" + name + ", Salary=" + Salary + "]";
		 }
	}
