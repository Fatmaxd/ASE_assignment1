package restaurant.menu;

public final class VegetarianMenuFactory extends BaseMenuFactory {

    @Override
    public java.util.List<MenuItem> starters() {
        return items(
                SimpleMenuItem.builder("Caprese Salad", 5.5, MenuCategory.STARTER)
                        .description("Tomatoes, basil, and mozzarella")
                        .addTags(MenuTag.VEGETARIAN)
                        .build(),
                SimpleMenuItem.builder("Stuffed Mushrooms", 6.0, MenuCategory.STARTER)
                        .description("Herbed cream cheese filling")
                        .addTags(MenuTag.VEGETARIAN)
                        .build()
        );
    }

    @Override
    public java.util.List<MenuItem> mains() {
        return items(
                SimpleMenuItem.builder("Margherita Pizza", 12.0, MenuCategory.MAIN_COURSE)
                        .description("Classic tomato & basil pizza")
                        .addTags(MenuTag.VEGETARIAN, MenuTag.PIZZA)
                        .build(),
                SimpleMenuItem.builder("Paneer Tikka Masala", 13.5, MenuCategory.MAIN_COURSE)
                        .description("Grilled paneer in spiced gravy")
                        .addTags(MenuTag.VEGETARIAN)
                        .build()
        );
    }

    @Override
    public java.util.List<MenuItem> desserts() {
        return items(
                SimpleMenuItem.builder("Mango Mousse", 5.75, MenuCategory.DESSERT)
                        .description("Fresh mango puree with cream")
                        .addTags(MenuTag.VEGETARIAN)
                        .build()
        );
    }

    @Override
    public java.util.List<MenuItem> beverages() {
        return items(
                SimpleMenuItem.builder("Fresh Lime Soda", 3.5, MenuCategory.BEVERAGE)
                        .description("Sweet & salty summer drink")
                        .addTags(MenuTag.BEVERAGE, MenuTag.VEGETARIAN)
                        .build()
        );
    }
}

