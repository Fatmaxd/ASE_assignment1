package restaurant.payment;

public final class CreditCardPayment implements PaymentStrategy {
    private final String maskedCard;

    public CreditCardPayment(String cardNumber) {
        this.maskedCard = mask(cardNumber);
    }

    private String mask(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "****";
        }
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

    @Override
    public String name() {
        return "Credit Card";
    }

    @Override
    public void pay(double amount) {
        System.out.printf("Charging %s amount $%.2f%n", maskedCard, amount);
    }
}

