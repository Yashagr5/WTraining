package collectionFrameworks;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListCollection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
List list = new LinkedList();
list.add(101);
list.add("Yash");

list.add(101);
list.add("Yash");
System.out.println("The list Elements: "+list);


List alist = new ArrayList();
alist.add(102);
alist.add("Mahi");
System.out.println("The Arraylist Elements: "+alist);
	}

}
