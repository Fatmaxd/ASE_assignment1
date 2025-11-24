package restaurant.menu;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Aggregates menu families and exposes a unified read-only view.
 */
public final class MenuRegistry {
    private final Map<MenuType, MenuFactory> factories = new EnumMap<>(MenuType.class);

    public MenuRegistry() {
        for (MenuType type : MenuType.values()) {
            factories.put(type, MenuFactory.forType(type));
        }
    }

    public Map<MenuType, MenuFactory> getFactories() {
        return Map.copyOf(factories);
    }

    public List<MenuItem> allItems() {
        List<MenuItem> items = new ArrayList<>();
        factories.values().forEach(factory ->
                factory.catalog().values().forEach(items::addAll));
        return items;
    }

    public List<MenuItem> byCategory(MenuCategory category) {
        List<MenuItem> items = new ArrayList<>();
        factories.values().forEach(factory ->
                items.addAll(factory.catalog().getOrDefault(category, List.of())));
        return items;
    }
}

