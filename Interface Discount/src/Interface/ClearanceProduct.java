package Interface;

public class ClearanceProduct implements Discountable {

    private String name;
    private double price;

    public ClearanceProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double calculateDiscount() {
        return price * 0.50; // 50% discount for clearance products
    }

    public void show() {
        System.out.printf("Clearance: %s | Price: Rs. %.2f | Discount: Rs. %.2f%n", name, price, calculateDiscount());
    }
}
