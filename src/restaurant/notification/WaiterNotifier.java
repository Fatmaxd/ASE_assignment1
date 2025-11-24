package restaurant.notification;

import restaurant.order.Order;

public final class WaiterNotifier implements OrderObserver {
    @Override
    public void onNewOrder(Order order) {
        System.out.println("[Waiter] Please serve order " + order.getOrderId() +
                " for " + order.getCustomerName());
    }
}

