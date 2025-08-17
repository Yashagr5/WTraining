package BehavioralDesign;


//Allow an object to change behaviour when its internal state changes  status  or state  -- > Pending -- > Shipped --> Delivered


interface OrderState
{
void next(Order o);	
}

class PendingState implements OrderState
{

	@Override
	public void next(Order o) {
		
		o.setState(new ShippedState()); // State is getting changed after pending to shipped
		System.out.println("Order Shipped");
	}
	

}


class ShippedState implements OrderState
{

	@Override
	public void next(Order o) {
		o.setState(new DeliveredState()); // State is getting changed after shipped to delivered
		System.out.println("Order Delivered");
	}
	

}

class DeliveredState implements OrderState
{

	@Override
	public void next(Order o) {
	
		System.out.println("Order already Delivered");
	}
	

}


class Order
{
private OrderState state;
Order()
{
state = new 	PendingState();
}
public void nextState() {
	state.next(this);
}
public void setState(OrderState state) {
	this.state = state;
}


}


public class StateDesignPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Order order = new Order();
		order.nextState();
		order.nextState();
		order.nextState();

	}

}
