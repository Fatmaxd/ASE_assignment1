package restaurant.notification;

import restaurant.order.Order;

public interface OrderObserver {
    void onNewOrder(Order order);
}

