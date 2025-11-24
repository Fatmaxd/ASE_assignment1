package restaurant;

import restaurant.addon.ExtraCheeseAddon;
import restaurant.addon.PremiumToppingAddon;
import restaurant.addon.SpicySauceAddon;
import restaurant.discount.DiscountEngine;
import restaurant.menu.MenuCategory;
import restaurant.menu.MenuItem;
import restaurant.menu.MenuRegistry;
import restaurant.menu.MenuFactory;
import restaurant.notification.KitchenDisplay;
import restaurant.notification.OrderPublisher;
import restaurant.notification.WaiterNotifier;
import restaurant.order.OrderBuilder;
import restaurant.order.OrderType;
import restaurant.payment.CashPayment;
import restaurant.payment.CreditCardPayment;
import restaurant.payment.MobileWalletPayment;
import restaurant.payment.PaymentStrategy;
import restaurant.receipt.BillingService;
import restaurant.receipt.ReceiptPrinter;
import restaurant.workflow.OrderResult;
import restaurant.workflow.OrderingFacade;
import restaurant.workflow.StandardOrderWorkflow;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Supplier;

public final class RestaurantApp {

        public static void main(String[] args) {
                RestaurantApp app = new RestaurantApp();
                app.runInteractive();
        }

        private void runInteractive() {
                MenuRegistry menuRegistry = new MenuRegistry();
                OrderPublisher publisher = new OrderPublisher();
                publisher.register(new KitchenDisplay());
                publisher.register(new WaiterNotifier());

                DiscountEngine discountEngine = new DiscountEngine();
                BillingService billingService = new BillingService(0.14, discountEngine);
                ReceiptPrinter receiptPrinter = new ReceiptPrinter();

                OrderingFacade facade = new OrderingFacade(menuRegistry, publisher, billingService, receiptPrinter);
                Scanner in = new Scanner(System.in);

                while (true) {
                        System.out.println("\nChoose an option:");
                        System.out.println("1) Create custom order");
                        System.out.println("2) Show menu");
                        System.out.println("3) Manage discounts");
                        System.out.println("0) Exit");
                        System.out.print("> ");
                        String choice = in.nextLine().trim();
                        try {
                                switch (choice) {
                                        case "1" -> handleCustomOrder(in, facade, menuRegistry);
                                        case "2" -> showMenuOptions(in, menuRegistry);
                                        case "3" -> manageDiscounts(in, discountEngine);
                                        case "0" -> {
                                                System.out.println("Goodbye.");
                                                in.close();
                                                return;
                                        }
                                        default -> System.out.println("Unknown option. Try again.");
                                }
                        } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                        }
                }
        }

        private void handleCustomOrder(Scanner in, OrderingFacade facade, MenuRegistry menuRegistry) {
                System.out.print("Customer name: ");
                String name = in.nextLine().trim();

                System.out.println("Order type (DINE_IN, DELIVERY, TAKEAWAY): ");
                String typeStr = in.nextLine().trim().toUpperCase();
                OrderType orderType = OrderType.valueOf(typeStr);

                List<MenuItem> items = new ArrayList<>();
                System.out.println("Enter item names one per line (empty line to finish):");
                while (true) {
                        System.out.print("item> ");
                        String itemName = in.nextLine().trim();
                        if (itemName.isEmpty())
                                break;
                        MenuItem found = findItemByName(menuRegistry, itemName);
                        if (found != null)
                                items.add(found);
                        else
                                System.out.println("Item not found: " + itemName);
                }

                if (items.isEmpty()) {
                        System.out.println("No items selected, cancelling order.");
                        return;
                }

                System.out.println("Payment method: 1) Cash 2) Credit Card 3) Mobile Wallet");
                String pm = in.nextLine().trim();
                PaymentStrategy payment;
                switch (pm) {
                        case "2" -> {
                                System.out.print("Card number: ");
                                String card = in.nextLine().trim();
                                payment = new CreditCardPayment(card);
                        }
                        case "3" -> {
                                System.out.print("Wallet provider: ");
                                String prov = in.nextLine().trim();
                                payment = new MobileWalletPayment(prov);
                        }
                        default -> payment = new CashPayment();
                }

                runWorkflow(facade, () -> name, items, payment, orderType);
        }

        private void manageDiscounts(Scanner in, DiscountEngine engine) {
                while (true) {
                        System.out.println("\nDiscounts:");
                        System.out.println("1) List active discounts");
                        System.out.println("2) Add category discount");
                        System.out.println("0) Back");
                        System.out.print("> ");
                        String c = in.nextLine().trim();
                        switch (c) {
                                case "1" -> {
                                        var strategies = engine.getStrategies();
                                        if (strategies.isEmpty())
                                                System.out.println("(no discounts)");
                                        else
                                                strategies.forEach(s -> System.out.printf("- %s%n", s.name()));
                                }
                                case "2" -> {
                                        System.out.print("Discount name: ");
                                        String name = in.nextLine().trim();
                                        System.out.println("Choose tag:");
                                        for (var t : restaurant.menu.MenuTag.values()) {
                                                System.out.printf("%d) %s%n", t.ordinal() + 1, t);
                                        }
                                        System.out.print("> ");
                                        String tagChoice = in.nextLine().trim();
                                        int idx = Integer.parseInt(tagChoice) - 1;
                                        var tag = restaurant.menu.MenuTag.values()[idx];
                                        System.out.print("Percentage (e.g. 0.10 for 10%): ");
                                        double pct = Double.parseDouble(in.nextLine().trim());
                                        engine.addStrategy(new restaurant.discount.CategoryDiscountStrategy(name, tag,
                                                        pct));
                                        System.out.println("Discount added.");
                                }
                                case "0" -> {
                                        return;
                                }
                                default -> System.out.println("Unknown option.");
                        }
                }
        }

        private void showMenuOptions(Scanner in, MenuRegistry registry) {
                System.out.println("1) Full menu\n2) Menu by type");
                System.out.print("> ");
                String choice = in.nextLine().trim();
                if (choice.equals("2")) {
                        System.out.println("Choose menu type:");
                        for (var t : restaurant.menu.MenuType.values()) {
                                System.out.printf("%d) %s%n", t.ordinal() + 1, t);
                        }
                        System.out.print("> ");
                        String sel = in.nextLine().trim();
                        int idx = Integer.parseInt(sel) - 1;
                        var type = restaurant.menu.MenuType.values()[idx];
                        MenuFactory factory = registry.getFactories().get(type);
                        System.out.println("======= " + type + " =======");
                        factory.catalog().forEach((cat, items) -> {
                                System.out.println("  - " + cat.toString().replace('_', ' '));
                                items.forEach(i -> System.out.printf("      %s : $%.2f (%s)%n", i.getName(),
                                                i.getBasePrice(), i.getDescription()));
                        });
                        System.out.println("====================");
                } else {
                        printMenu(registry);
                }
        }

        private static MenuItem findItemByName(MenuRegistry registry, String name) {
                return registry.allItems().stream()
                                .filter(item -> item.getName().equalsIgnoreCase(name))
                                .findFirst()
                                .orElse(null);
        }

        private static void printMenu(MenuRegistry registry) {
                System.out.println("======= MENU =======");
                registry.getFactories().forEach((type, factory) -> {
                        System.out.println("* " + type);
                        factory.catalog().forEach((cat, items) -> {
                                System.out.println("  - " + cat.toString().replace('_', ' '));
                                items.forEach(i -> System.out.printf("      %s : $%.2f (%s)%n", i.getName(),
                                                i.getBasePrice(), i.getDescription()));
                        });
                });
                System.out.println("====================");
        }

        private void runWorkflow(OrderingFacade facade,
                        Supplier<String> customerSupplier,
                        List<MenuItem> items,
                        PaymentStrategy paymentStrategy,
                        OrderType orderType) {
                OrderBuilder builder = new OrderBuilder().orderType(orderType);
                StandardOrderWorkflow workflow = new StandardOrderWorkflow(facade, customerSupplier, items);
                OrderResult result = workflow.execute(builder, paymentStrategy,
                                () -> System.out.println("-> Custom add-ons applied where requested"));
                System.out.printf("Charged via %s for $%.2f%n%n",
                                paymentStrategy.name(),
                                result.billingSummary().grandTotal());
        }

        private static MenuItem pickItem(MenuRegistry registry, MenuCategory category, String name) {
                return registry.byCategory(category).stream()
                                .filter(item -> item.getName().equalsIgnoreCase(name))
                                .findFirst()
                                .orElseThrow(() -> new IllegalArgumentException("Item not found: " + name));
        }

        private static MenuItem customizePizza(MenuRegistry registry) {
                MenuItem pizza = pickItem(registry, MenuCategory.MAIN_COURSE, "Margherita Pizza");
                MenuItem cheese = new ExtraCheeseAddon(pizza);
                MenuItem spicy = new SpicySauceAddon(cheese);
                return new PremiumToppingAddon(spicy, "truffle oil drizzle", 2.0);
        }
}
