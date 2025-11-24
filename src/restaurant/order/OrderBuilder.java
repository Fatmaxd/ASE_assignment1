package restaurant.order;

import restaurant.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder pattern for assembling complex orders.
 */
public final class OrderBuilder {
    private String customerName = "Guest";
    private OrderType orderType = OrderType.DINE_IN;
    private final List<OrderItem> items = new ArrayList<>();

    public OrderBuilder customer(String name) {
        if (name != null && !name.isBlank()) {
            this.customerName = name;
        }
        return this;
    }

    public OrderBuilder orderType(OrderType type) {
        this.orderType = type;
        return this;
    }

    public OrderBuilder addItem(MenuItem item) {
        return addItem(item, 1);
    }

    public OrderBuilder addItem(MenuItem item, int quantity) {
        items.add(new OrderItem(item, quantity));
        return this;
    }

    public Order build() {
        if (items.isEmpty()) {
            throw new IllegalStateException("Order must contain at least one item");
        }
        return new Order(customerName, orderType, items);
    }
}

