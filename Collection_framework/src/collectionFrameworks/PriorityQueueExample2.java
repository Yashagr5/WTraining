package collectionFrameworks;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Patients{
	String name;
	String condition;
	int priority;
	
	public Patients(String name, String condition, int priority) {
		this.name = name;
		this.condition = condition;
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Patients [name=" + name + ", condition=" + condition + ", priority=" + priority + "]";
	}	
}

class PriorityComparator implements Comparator<Patients>{

	@Override
	public int compare(Patients o1, Patients o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

public class PriorityQueueExample2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Patient> pq = new PriorityQueue<>();
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
