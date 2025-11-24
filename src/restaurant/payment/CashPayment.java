package restaurant.payment;

public final class CashPayment implements PaymentStrategy {
    @Override
    public String name() {
        return "Cash";
    }

    @Override
    public void pay(double amount) {
        System.out.printf("Processing cash payment: $%.2f%n", amount);
    }
}

