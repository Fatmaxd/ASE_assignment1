package restaurant.workflow;

import restaurant.menu.MenuFactory;
import restaurant.menu.MenuItem;
import restaurant.menu.MenuRegistry;
import restaurant.menu.MenuType;

public final class MenuPresenter {
    private final MenuRegistry registry;

    public MenuPresenter(MenuRegistry registry) {
        this.registry = registry;
    }

    public void displayFullMenu() {
        System.out.println("======= MENU =======");
        registry.getFactories().forEach((type, factory) -> printFactory(type, factory));
        System.out.println("====================\n");
    }

    private void printFactory(MenuType type, MenuFactory factory) {
        System.out.println("* " + type);
        printSection("Starters", factory.starters());
        printSection("Main Course", factory.mains());
        printSection("Desserts", factory.desserts());
        printSection("Beverages", factory.beverages());
    }

    private void printSection(String title, java.util.List<MenuItem> items) {
        if (items.isEmpty()) {
            return;
        }
        System.out.println("  - " + title);
        items.forEach(item ->
                System.out.printf("      %s : $%.2f (%s)%n",
                        item.getName(),
                        item.calculatePrice(),
                        item.getDescription()));
    }
}

