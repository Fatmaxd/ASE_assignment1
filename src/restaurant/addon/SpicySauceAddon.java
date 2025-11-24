package restaurant.addon;

import restaurant.menu.MenuItem;

public final class SpicySauceAddon extends MenuItemDecorator {
    private static final double COST = 0.75;

    public SpicySauceAddon(MenuItem base) {
        super(base);
    }

    @Override
    public double calculatePrice() {
        return base.calculatePrice() + COST;
    }

    @Override
    public String getDescription() {
        return base.getDescription() + " + spicy sauce";
    }
}

