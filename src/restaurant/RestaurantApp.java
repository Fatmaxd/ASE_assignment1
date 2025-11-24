package restaurant;

import restaurant.addon.ExtraCheeseAddon;
import restaurant.addon.PremiumToppingAddon;
import restaurant.addon.SpicySauceAddon;
import restaurant.discount.DiscountEngine;
import restaurant.menu.MenuCategory;
import restaurant.menu.MenuItem;
import restaurant.menu.MenuRegistry;
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
import java.util.function.Supplier;

public final class RestaurantApp {

    public static void main(String[] args) {
        RestaurantApp app = new RestaurantApp();
        app.runDemo();
    }

    private void runDemo() {
        MenuRegistry menuRegistry = new MenuRegistry();
        OrderPublisher publisher = new OrderPublisher();
        publisher.register(new KitchenDisplay());
        publisher.register(new WaiterNotifier());

        DiscountEngine discountEngine = new DiscountEngine();
        BillingService billingService = new BillingService(0.14, discountEngine);
        ReceiptPrinter receiptPrinter = new ReceiptPrinter();

        OrderingFacade facade = new OrderingFacade(menuRegistry, publisher, billingService, receiptPrinter);

        System.out.println("=== Sample Dine-in Order ===");
        runWorkflow(
                facade,
                () -> "Omar",
                List.of(
                        customizePizza(menuRegistry),
                        pickItem(menuRegistry, MenuCategory.BEVERAGE, "House Lemonade")
                ),
                new CashPayment(),
                OrderType.DINE_IN
        );

        System.out.println("=== Sample Delivery Order ===");
        runWorkflow(
                facade,
                () -> "Layla",
                List.of(
                        pickItem(menuRegistry, MenuCategory.MAIN_COURSE, "Classic Beef Burger"),
                        pickItem(menuRegistry, MenuCategory.DESSERT, "Kunafa Cheesecake")
                ),
                new CreditCardPayment("5246123412341234"),
                OrderType.DELIVERY
        );

        System.out.println("=== Sample Takeaway with Wallet ===");
        runWorkflow(
                facade,
                () -> "Noor",
                List.of(
                        pickItem(menuRegistry, MenuCategory.MAIN_COURSE, "Paneer Tikka Masala"),
                        pickItem(menuRegistry, MenuCategory.BEVERAGE, "Iced Hibiscus Tea")
                ),
                new MobileWalletPayment("PayWave"),
                OrderType.TAKEAWAY
        );
    }

    private void runWorkflow(OrderingFacade facade,
                             Supplier<String> customerSupplier,
                             List<MenuItem> items,
                             PaymentStrategy paymentStrategy,
                             OrderType orderType) {
        OrderBuilder builder = new OrderBuilder().orderType(orderType);
        StandardOrderWorkflow workflow = new StandardOrderWorkflow(facade, customerSupplier, items);
        OrderResult result = workflow.execute(builder, paymentStrategy, () ->
                System.out.println("-> Custom add-ons applied where requested"));
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

