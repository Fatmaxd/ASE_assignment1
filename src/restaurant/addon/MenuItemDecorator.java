package restaurant.addon;

import restaurant.menu.MenuCategory;
import restaurant.menu.MenuItem;
import restaurant.menu.MenuTag;

import java.util.Set;

/**
 * Decorator base class for add-ons (extra cheese, sauces, etc.).
 */
public abstract class MenuItemDecorator implements MenuItem {
    protected final MenuItem base;

    protected MenuItemDecorator(MenuItem base) {
        this.base = base;
    }

    @Override
    public String getName() {
        return base.getName();
    }

    @Override
    public MenuCategory getCategory() {
        return base.getCategory();
    }

    @Override
    public Set<MenuTag> getTags() {
        return base.getTags();
    }

    @Override
    public double getBasePrice() {
        return base.getBasePrice();
    }

    @Override
    public String getDescription() {
        return base.getDescription();
    }
}

