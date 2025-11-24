package restaurant.receipt;

import restaurant.discount.DiscountEngine;
import restaurant.order.Order;

import java.util.List;

public final class BillingService {
    private final double taxRate;
    private final DiscountEngine discountEngine;

    public BillingService(double taxRate, DiscountEngine discountEngine) {
        this.taxRate = taxRate;
        this.discountEngine = discountEngine;
    }

    public BillingSummary summarize(Order order) {
        double subtotal = order.subtotal();
        double discountTotal = 0.0;
        List<String> discountNames = new java.util.ArrayList<>();
        for (DiscountStrategy strategy : discountEngine.getStrategies()) {
            double value = strategy.apply(order);
            if (value > 0) {
                discountTotal += value;
                discountNames.add(strategy.name());
            }
        }

        double taxedAmount = Math.max(0, subtotal - discountTotal);
        double tax = taxedAmount * taxRate;
        double grandTotal = taxedAmount + tax;

        return new BillingSummary(subtotal, discountTotal, tax, grandTotal, discountNames);
    }
}

