package restaurant.workflow;

import restaurant.order.OrderBuilder;
import restaurant.payment.PaymentStrategy;

/**
 * Template Method encapsulating the ordering workflow steps.
 */
public abstract class OrderWorkflowTemplate {
    protected final OrderingFacade facade;

    protected OrderWorkflowTemplate(OrderingFacade facade) {
        this.facade = facade;
    }

    public final OrderResult execute(OrderBuilder builder, PaymentStrategy paymentStrategy) {
        collectCustomerDetails(builder);
        customizeOrder(builder);
        OrderResult result = facade.process(builder, paymentStrategy);
        notifyCompletion(result);
        return result;
    }

    protected void displayMenu() {
        MenuPresenter presenter = new MenuPresenter(facade.menuRegistry());
        presenter.displayFullMenu();
    }

    protected abstract void collectCustomerDetails(OrderBuilder builder);

    protected abstract void customizeOrder(OrderBuilder builder);

    protected void notifyCompletion(OrderResult result) {
        System.out.printf("Order %s completed for %s%n",
                result.order().getOrderId(),
                result.order().getCustomerName());
    }
}
