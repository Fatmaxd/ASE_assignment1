package restaurant.menu;

public final class KidsMenuFactory extends BaseMenuFactory {
    @Override
    public java.util.List<MenuItem> starters() {
        return items(
                SimpleMenuItem.builder("Mini Veggie Sticks", 4.0, MenuCategory.STARTER)
                        .description("Served with yogurt dip")
                        .addTags(MenuTag.KIDS_SPECIAL, MenuTag.VEGETARIAN)
                        .build());
    }

    @Override
    public java.util.List<MenuItem> mains() {
        return items(
                SimpleMenuItem.builder("Mini Cheese Pizza", 9.0, MenuCategory.MAIN_COURSE)
                        .description("Kid-sized pizza with mozzarella")
                        .addTags(MenuTag.KIDS_SPECIAL, MenuTag.PIZZA, MenuTag.VEGETARIAN)
                        .build(),
                SimpleMenuItem.builder("Chicken Nuggets Meal", 9.5, MenuCategory.MAIN_COURSE)
                        .description("Served with fries")
                        .addTags(MenuTag.KIDS_SPECIAL, MenuTag.CHICKEN)
                        .build());
    }

    @Override
    public java.util.List<MenuItem> desserts() {
        return items(
                SimpleMenuItem.builder("Chocolate Sundae", 4.5, MenuCategory.DESSERT)
                        .description("With rainbow sprinkles")
                        .addTags(MenuTag.KIDS_SPECIAL, MenuTag.VEGETARIAN)
                        .build());
    }

    @Override
    public java.util.List<MenuItem> beverages() {
        return items(
                SimpleMenuItem.builder("Fresh Orange Juice", 3.0, MenuCategory.BEVERAGE)
                        .description("No added sugar")
                        .addTags(MenuTag.KIDS_SPECIAL, MenuTag.BEVERAGE)
                        .build());
    }
}

