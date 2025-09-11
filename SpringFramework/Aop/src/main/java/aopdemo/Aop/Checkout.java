package aopdemo.Aop;

import org.springframework.stereotype.Component;

@Component
public class Checkout {
void checkoutOrder(String status) {
	System.out.println("order process has started........");
}
int calculation(int x) {
	int y;
	y=x+1;
	return y;
}
}
