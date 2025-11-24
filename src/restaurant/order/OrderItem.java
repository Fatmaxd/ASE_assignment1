package restaurant.order;

import restaurant.menu.MenuItem;

public record OrderItem(MenuItem item, int quantity) {
    public OrderItem {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
    }

    public double lineTotal() {
        return item.calculatePrice() * quantity;
    }
}

