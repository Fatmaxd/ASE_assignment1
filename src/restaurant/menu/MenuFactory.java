package restaurant.menu;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract Factory responsible for providing cohesive menu families.
 */
public interface MenuFactory {
    List<MenuItem> starters();

    List<MenuItem> mains();

    List<MenuItem> desserts();

    List<MenuItem> beverages();

    default Map<MenuCategory, List<MenuItem>> catalog() {
        Map<MenuCategory, List<MenuItem>> data = new EnumMap<>(MenuCategory.class);
        data.put(MenuCategory.STARTER, starters());
        data.put(MenuCategory.MAIN_COURSE, mains());
        data.put(MenuCategory.DESSERT, desserts());
        data.put(MenuCategory.BEVERAGE, beverages());
        return Collections.unmodifiableMap(data);
    }

    static MenuFactory forType(MenuType type) {
        return switch (type) {
            case VEGETARIAN -> new VegetarianMenuFactory();
            case NON_VEGETARIAN -> new NonVegetarianMenuFactory();
            case KIDS -> new KidsMenuFactory();
            case DESSERTS -> new DessertsMenuFactory();
            case BEVERAGES -> new BeveragesMenuFactory();
        };
    }
}

