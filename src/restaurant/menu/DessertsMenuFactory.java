package restaurant.menu;

public final class DessertsMenuFactory extends BaseMenuFactory {
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
        return items(
                SimpleMenuItem.builder("Tiramisu", 6.75, MenuCategory.DESSERT)
                        .description("Espresso-soaked ladyfingers and mascarpone")
                        .addTags(MenuTag.VEGETARIAN)
                        .build(),
                SimpleMenuItem.builder("Kunafa Cheesecake", 7.25, MenuCategory.DESSERT)
                        .description("Middle Eastern twist on cheesecake")
                        .addTags(MenuTag.VEGETARIAN)
                        .build());
    }

    @Override
    public java.util.List<MenuItem> beverages() {
        return items(
                SimpleMenuItem.builder("Affogato", 4.75, MenuCategory.BEVERAGE)
                        .description("Vanilla gelato topped with espresso")
                        .addTags(MenuTag.BEVERAGE)
                        .build());
    }
}

