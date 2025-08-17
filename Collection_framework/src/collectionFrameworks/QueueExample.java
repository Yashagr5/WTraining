package collectionFrameworks;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<String> pq = new ArrayDeque();
		pq.offer("Yash");
		pq.offer("Rudy");
		pq.offer("Rush");
		pq.offer("pop");
		pq.add("butter");
		System.out.println("Here is output of queue: "+pq);
		pq.poll();
		pq.remove();
		System.out.println("Here is output of queue: "+pq);
		System.out.println(pq.element());
		System.out.println(pq.size());
		System.out.println(pq.isEmpty());
		System.out.println(pq.peek());
//		System.out.println(pq.());
		
		List<String> list = new ArrayList<>(pq);
		System.out.println(list.get(1));
		
		
		Queue<Integer> q = new PriorityQueue<>();
		q.offer(200); q.offer(50); q.offer(250);
		System.out.println("The elements: "+q);
		System.out.println(q.poll());
		}

}






















//package collectionFrameworks;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Queue;
//
//public class QueueExample {
//
//	public static void main(String[] args) {
//		Queue<String> q = new LinkedList<>();
//		q.offer("Yash");
//		q.offer("Rudy");
//		q.offer("Rush");
//		q.offer("pop");
//		q.add("butter");
//		System.out.println("Here is output of queue: "+q);
//		q.poll();
//		q.remove();
//		System.out.println("Here is output of queue: "+q);
//		System.out.println(q.element());
//		System.out.println(q.size());
//		System.out.println(q.isEmpty());
//		System.out.println(q.peek());
//		System.out.println(q.add(""hut));
//		
//		List<String> list = new ArrayList<>(q);
//		System.out.println(list.get(1));
//		}
//
//}
