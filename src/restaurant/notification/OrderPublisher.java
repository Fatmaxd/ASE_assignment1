package restaurant.notification;

import restaurant.order.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer subject for notifying kitchen/waiter subsystems.
 */
public final class OrderPublisher {
    private final List<OrderObserver> observers = new ArrayList<>();

    public void register(OrderObserver observer) {
        observers.add(observer);
    }

    public void unregister(OrderObserver observer) {
        observers.remove(observer);
    }

    public void publish(Order order) {
        observers.forEach(o -> o.onNewOrder(order));
    }
}

