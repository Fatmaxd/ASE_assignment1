package restaurant.notification;

import restaurant.order.Order;

public final class KitchenDisplay implements OrderObserver {
    @Override
    public void onNewOrder(Order order) {
        System.out.println("[Kitchen] New order received: " + order.getOrderId() +
                " | Type: " + order.getOrderType() +
                " | Items: " + order.getItems().size());
    }
}

