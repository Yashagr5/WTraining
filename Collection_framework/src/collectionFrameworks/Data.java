package collectionFrameworks;

public class Data<T> {
private T item;

public T getItem() {
	return item;
}


public void setItem(T item) {
	this.item = item;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
Data<String> d1 = new Data();
d1.setItem("yash");
System.out.println(d1.getItem());
//d1.setItem(123);
//System.out.println(d1.getItem());

	}


	

}
