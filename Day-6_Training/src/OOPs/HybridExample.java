package OOPs;

class Clothes extends Products implements IDiscountable {
	public Clothes(String name, double price) {
		super(name, price);
	}
	
	public double getDiscount() {
		return price*0.18;
	}
	public void showTag() {
		System.out.println(name+ " This is of denim and Machine Washable too"+ " Discount: " + getDiscount());
	}
}

public class HybridExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Clothes c = new Clothes("Jeans", 4500);
		c.showDetails();c.showTag();

	}

}
