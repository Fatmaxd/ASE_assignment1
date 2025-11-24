package restaurant.discount;

import restaurant.menu.MenuTag;
import restaurant.order.Order;

import java.util.ArrayList;
import java.util.List;

public final class DiscountEngine {
    private final List<DiscountStrategy> strategies = new ArrayList<>();

    public DiscountEngine() {
        strategies.add(new CategoryDiscountStrategy("Chicken Lovers", MenuTag.CHICKEN, 0.10));
        strategies.add(new CategoryDiscountStrategy("Meat Feast", MenuTag.MEAT, 0.08));
        strategies.add(new CategoryDiscountStrategy("Pizza Mania", MenuTag.PIZZA, 0.05));
    }

    public void addStrategy(DiscountStrategy strategy) {
        strategies.add(strategy);
    }

    public List<DiscountStrategy> getStrategies() {
        return List.copyOf(strategies);
    }

    public double calculateDiscount(Order order) {
        return strategies.stream()
                .mapToDouble(strategy -> strategy.apply(order))
                .sum();
    }
}

