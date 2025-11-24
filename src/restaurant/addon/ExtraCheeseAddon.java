package restaurant.addon;

import restaurant.menu.MenuItem;

public final class ExtraCheeseAddon extends MenuItemDecorator {
    private static final double COST = 1.25;

    public ExtraCheeseAddon(MenuItem base) {
        super(base);
    }

    @Override
    public double calculatePrice() {
        return base.calculatePrice() + COST;
    }

    @Override
    public String getDescription() {
        return base.getDescription() + " + extra cheese";
    }
}

