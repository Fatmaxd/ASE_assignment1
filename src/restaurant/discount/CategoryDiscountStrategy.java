package restaurant.discount;

import restaurant.menu.MenuTag;
import restaurant.order.Order;
import restaurant.order.OrderItem;

public final class CategoryDiscountStrategy implements DiscountStrategy {
    private final MenuTag targetTag;
    private final double percentage;
    private final String name;

    public CategoryDiscountStrategy(String name, MenuTag targetTag, double percentage) {
        this.name = name;
        this.targetTag = targetTag;
        this.percentage = percentage;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public double apply(Order order) {
        double eligibleTotal = order.getItems().stream()
                .filter(item -> item.item().getTags().contains(targetTag))
                .mapToDouble(OrderItem::lineTotal)
                .sum();
        return eligibleTotal * percentage;
    }
}

