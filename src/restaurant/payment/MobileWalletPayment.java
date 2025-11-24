package restaurant.payment;

public final class MobileWalletPayment implements PaymentStrategy {
    private final String walletProvider;

    public MobileWalletPayment(String walletProvider) {
        this.walletProvider = walletProvider;
    }

    @Override
    public String name() {
        return walletProvider + " Wallet";
    }

    @Override
    public void pay(double amount) {
        System.out.printf("Debiting %s wallet with $%.2f%n", walletProvider, amount);
    }
}

