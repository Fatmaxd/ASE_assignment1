package restaurant.workflow;

import restaurant.menu.MenuItem;
import restaurant.menu.MenuRegistry;
import restaurant.order.OrderBuilder;
import restaurant.payment.PaymentStrategy;

import java.util.List;
import java.util.function.Supplier;

public final class StandardOrderWorkflow extends OrderWorkflowTemplate {
    private final Supplier<String> customerNameSupplier;
    private final List<MenuItem> recommendedItems;

    public StandardOrderWorkflow(OrderingFacade facade,
                                 Supplier<String> customerNameSupplier,
                                 List<MenuItem> recommendedItems) {
        super(facade);
        this.customerNameSupplier = customerNameSupplier;
        this.recommendedItems = recommendedItems;
    }

    @Override
    protected void collectCustomerDetails(OrderBuilder builder) {
        builder.customer(customerNameSupplier.get());
    }

    @Override
    protected void customizeOrder(OrderBuilder builder) {
        if (recommendedItems.isEmpty()) {
            MenuRegistry registry = facade.menuRegistry();
            builder.addItem(registry.allItems().get(0));
        } else {
            recommendedItems.forEach(builder::addItem);
        }
    }

    public OrderResult execute(OrderBuilder builder, PaymentStrategy strategy, Runnable addOnStep) {
        addOnStep.run();
        return super.execute(builder, strategy);
    }
}

