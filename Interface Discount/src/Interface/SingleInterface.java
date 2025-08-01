package Interface;

public class SingleInterface {
    public static void main(String[] args) {
        // Array to hold references to Discountable objects
        Discountable[] discounts = new Discountable[2];

        // Assigning objects of implementing classes to the array
        discounts[0] = new SeasonalProduct("Laptop", 3000);
        discounts[1] = new ClearanceProduct("Old Smartphone", 5000);

        System.out.println("Discount Summary Report");

        // Loop through the array and display information
        for (Discountable d : discounts) {
            if (d instanceof SeasonalProduct sp) {
                sp.show();
            } else if (d instanceof ClearanceProduct cp) {
                cp.show();
            }
        }
    }
}
