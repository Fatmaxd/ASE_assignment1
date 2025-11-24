package restaurant.menu;

public final class NonVegetarianMenuFactory extends BaseMenuFactory {

    @Override
    public java.util.List<MenuItem> starters() {
        return items(
                SimpleMenuItem.builder("Chicken Wings", 7.5, MenuCategory.STARTER)
                        .description("Served with buffalo sauce")
                        .addTags(MenuTag.CHICKEN, MenuTag.MEAT, MenuTag.NON_VEGETARIAN)
                        .build(),
                SimpleMenuItem.builder("Shrimp Cocktail", 8.25, MenuCategory.STARTER)
                        .description("Chilled shrimp with cocktail sauce")
                        .addTags(MenuTag.NON_VEGETARIAN, MenuTag.MEAT)
                        .build()
        );
    }

    @Override
    public java.util.List<MenuItem> mains() {
        return items(
                SimpleMenuItem.builder("Eastern Chicken Pizza", 14.5, MenuCategory.MAIN_COURSE)
                        .description("Spiced chicken with tahini drizzle")
                        .addTags(MenuTag.CHICKEN, MenuTag.PIZZA, MenuTag.NON_VEGETARIAN)
                        .build(),
                SimpleMenuItem.builder("Classic Beef Burger", 13.0, MenuCategory.MAIN_COURSE)
                        .description("Smoked cheddar and caramelized onion")
                        .addTags(MenuTag.MEAT, MenuTag.NON_VEGETARIAN)
                        .build()
        );
    }

    @Override
    public java.util.List<MenuItem> desserts() {
        return items(
                SimpleMenuItem.builder("Chocolate Lava Cake", 6.5, MenuCategory.DESSERT)
                        .description("Served warm with ice cream")
                        .addTags(MenuTag.VEGETARIAN)
                        .build()
        );
    }

    @Override
    public java.util.List<MenuItem> beverages() {
        return items(
                SimpleMenuItem.builder("Mint Lemonade", 3.75, MenuCategory.BEVERAGE)
                        .description("Fresh mint with lemon zest")
                        .addTags(MenuTag.BEVERAGE)
                        .build()
        );
    }
}

