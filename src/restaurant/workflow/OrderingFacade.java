package restaurant.workflow;

import restaurant.menu.MenuRegistry;
import restaurant.notification.OrderPublisher;
import restaurant.order.Order;
import restaurant.order.OrderBuilder;
import restaurant.payment.PaymentStrategy;
import restaurant.receipt.BillingService;
import restaurant.receipt.BillingSummary;
import restaurant.receipt.ReceiptPrinter;

public final class OrderingFacade {
    private final MenuRegistry menuRegistry;
    private final OrderPublisher publisher;
    private final BillingService billingService;
    private final ReceiptPrinter receiptPrinter;

    public OrderingFacade(MenuRegistry menuRegistry,
                          OrderPublisher publisher,
                          BillingService billingService,
                          ReceiptPrinter receiptPrinter) {
        this.menuRegistry = menuRegistry;
        this.publisher = publisher;
        this.billingService = billingService;
        this.receiptPrinter = receiptPrinter;
    }

    public MenuRegistry menuRegistry() {
        return menuRegistry;
    }

    public OrderResult process(OrderBuilder builder, PaymentStrategy paymentStrategy) {
        Order order = builder.build();
        publisher.publish(order);
        BillingSummary summary = billingService.summarize(order);
        paymentStrategy.pay(summary.grandTotal());
        receiptPrinter.print(order, summary);
        return new OrderResult(order, summary);
    }
}

