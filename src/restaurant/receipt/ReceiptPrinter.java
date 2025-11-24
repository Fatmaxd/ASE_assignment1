package restaurant.receipt;

import restaurant.order.Order;
import restaurant.order.OrderItem;

public final class ReceiptPrinter {
    public void print(Order order, BillingSummary summary) {
        System.out.println("\n========== RECEIPT ==========");
        System.out.printf("Order ID: %s%n", order.getOrderId());
        System.out.printf("Customer: %s (%s)%n", order.getCustomerName(), order.getOrderType());
        System.out.println("-----------------------------");
        for (OrderItem line : order.getItems()) {
            System.out.printf("%-25s x%-2d $%.2f%n",
                    line.item().getName(),
                    line.quantity(),
                    line.lineTotal());
        }
        System.out.println("-----------------------------");
        System.out.printf("Subtotal: $%.2f%n", summary.subtotal());
        if (summary.discountTotal() > 0) {
            System.out.printf("Discounts (%s): -$%.2f%n",
                    String.join(", ", summary.appliedDiscounts()),
                    summary.discountTotal());
        }
        System.out.printf("Tax: $%.2f%n", summary.taxTotal());
        System.out.printf("Grand Total: $%.2f%n", summary.grandTotal());
        System.out.println("=============================\n");
    }
}

