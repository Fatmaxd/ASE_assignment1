package restaurant.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class Order {
    private final String orderId;
    private final String customerName;
    private final OrderType orderType;
    private final LocalDateTime createdAt;
    private final List<OrderItem> items;

    Order(String customerName, OrderType orderType, List<OrderItem> items) {
        this.orderId = UUID.randomUUID().toString();
        this.customerName = customerName;
        this.orderType = orderType;
        this.createdAt = LocalDateTime.now();
        this.items = Collections.unmodifiableList(new ArrayList<>(items));
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double subtotal() {
        return items.stream().mapToDouble(OrderItem::lineTotal).sum();
    }
}

