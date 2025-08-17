package collectionFrameworks;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class Patient implements Comparable<Patient>{
	String name;
	String condition;
	int priority;
	
	public Patient(String name, String condition, int priority) {
		this.name=name;
		this.condition = condition;
		this.priority = priority;
	}
	@Override
	public int compareTo(Patient o) {
		return Integer.compare(this.priority, o.priority);
	}

	@Override
	public String toString() {
		return "Patient [name=" + name + ", condition=" + condition + ", priority=" + priority + "]";
	}
	
}

public class PriorityQueueExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		Queue<Patient> pq = new PriorityQueue<>();
//		System.out.println("Enter Number of Patients: ");
//		int num = in.nextInt();
//		For(int i=0; i<num; i++){
//			System.out.println("Enter Details of Patient: 1.Name 2.Condition 3.Priority ");
//			System.out.println("Name: ");
//			String name = in.nextLine();
//			System.out.println("Condition: ");
//			String condition = in.nextLine();
//			System.out.println("Priority: 1.High  2.Medium  3.Low");
//			int priority = in.nextInt();
//			pq.offer(new Patient(name, condition, priority));
//}
pq.offer(new Patient("Yash", "Heart Attack", 1));
pq.offer(new Patient("Noone", "Fever", 2));
pq.offer(new Patient("lone", "Fractured Hand", 2));
pq.offer(new Patient("kris", "cold", 3));
pq.offer(new Patient("pop", "Asthama", 1));

while(!pq.isEmpty()){
System.out.println(pq.poll());	
}
	}
}

