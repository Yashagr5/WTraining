// 1. Store menu items with prices using objects and arrays
const menu = [
  { item: "Burger", price: 5.99 },
  { item: "Fries", price: 2.99 },
  { item: "Soda", price: 1.5 },
  { item: "Salad", price: 4.0 },
  { item: "Pizza", price: 8.5 }
];

// 2. Take a customer's order (you can simulate with an array of item names)
let customerOrder = ["Burger", "Fries", "Soda", "Pizza"];

// Function to process the order
function processOrder(order) {
  let total = 0;
  let orderDetails = [];

  for (let i = 0; i < order.length; i++) {
    // Find the menu item
    const menuItem = menu.find(item => item.item === order[i]);

    if (menuItem) {
      total += menuItem.price;
      orderDetails.push(menuItem);
    } else {
      console.log(`Item "${order[i]}" not found on the menu.`);
    }
  }

  // 3. Apply discount if total exceeds $10
  let discount = 0;
  if (total > 10) {
    discount = total * 0.1; // 10% discount
    total -= discount;
  }

  // 4. Display order details
  console.log("Customer Order:");
  for (let i = 0; i < orderDetails.length; i++) {
    console.log(`- ${orderDetails[i].item}: $${orderDetails[i].price.toFixed(2)}`);
  }

  console.log(`Discount: $${discount.toFixed(2)}`);
  console.log(`Total Price: $${total.toFixed(2)}`);
}

// Run the order
processOrder(customerOrder);
