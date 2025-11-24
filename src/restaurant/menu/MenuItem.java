package restaurant.menu;

import java.util.Set;

/**
 * Represents any consumable offered by the restaurant.
 * Menu items are immutable value objects to ease sharing between orders.
 */
public interface MenuItem {
    String getName();

    MenuCategory getCategory();

    /**
     * Tags help downstream services (discounts, filtering, etc.).
     */
    Set<MenuTag> getTags();

    /**
     * Base price before add-ons, taxes, or discounts.
     */
    double getBasePrice();

    /**
     * Human readable description for UI.
     */
    String getDescription();

    /**
     * Final price including any decoration (add-ons).
     */
    double calculatePrice();
}

