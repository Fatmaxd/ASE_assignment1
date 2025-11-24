package restaurant.receipt;

import restaurant.discount.DiscountStrategy;

import java.util.List;

public record BillingSummary(
        double subtotal,
        double discountTotal,
        double taxTotal,
        double grandTotal,
        List<String> appliedDiscounts
) {
}

