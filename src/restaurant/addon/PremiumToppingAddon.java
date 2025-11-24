package restaurant.addon;

import restaurant.menu.MenuItem;

public final class PremiumToppingAddon extends MenuItemDecorator {
    private final String toppingName;
    private final double cost;

    public PremiumToppingAddon(MenuItem base, String toppingName, double cost) {
        super(base);
        this.toppingName = toppingName;
        this.cost = cost;
    }

    @Override
    public double calculatePrice() {
        return base.calculatePrice() + cost;
    }

    @Override
    public String getDescription() {
        return base.getDescription() + " + " + toppingName;
    }
}

