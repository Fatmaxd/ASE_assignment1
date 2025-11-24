package restaurant.discount;

import restaurant.order.Order;

public interface DiscountStrategy {
    String name();

    double apply(Order order);
}

