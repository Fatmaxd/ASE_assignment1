package restaurant.menu;

public final class BeveragesMenuFactory extends BaseMenuFactory {
    @Override
    public java.util.List<MenuItem> starters() {
        return java.util.List.of();
    }

    @Override
    public java.util.List<MenuItem> mains() {
        return java.util.List.of();
    }

    @Override
    public java.util.List<MenuItem> desserts() {
        return java.util.List.of();
    }

    @Override
    public java.util.List<MenuItem> beverages() {
        return items(
                SimpleMenuItem.builder("House Lemonade", 3.25, MenuCategory.BEVERAGE)
                        .description("Fresh lemons with cane sugar")
                        .addTags(MenuTag.BEVERAGE)
                        .build(),
                SimpleMenuItem.builder("Iced Hibiscus Tea", 3.5, MenuCategory.BEVERAGE)
                        .description("Floral and refreshing")
                        .addTags(MenuTag.BEVERAGE)
                        .build(),
                SimpleMenuItem.builder("Cold Brew Coffee", 4.0, MenuCategory.BEVERAGE)
                        .description("12-hour steeped beans")
                        .addTags(MenuTag.BEVERAGE)
                        .build());
    }
}

