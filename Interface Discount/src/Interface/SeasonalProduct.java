package Interface;

public class SeasonalProduct implements Discountable {

    private String name;
    private double price;

    public SeasonalProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double calculateDiscount() {
        return price * 0.10; // 10% discount for seasonal products
    }

    public void show() {
        System.out.printf("Seasonal: %s | Price: Rs. %.2f | Discount: Rs. %.2f%n", name, price, calculateDiscount());
    }
}
