package restaurant.payment;

public interface PaymentStrategy {
    String name();

    void pay(double amount);
}

