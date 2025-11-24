package restaurant.workflow;

import restaurant.order.Order;
import restaurant.receipt.BillingSummary;

public record OrderResult(Order order, BillingSummary billingSummary) {
}

