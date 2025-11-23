import java.util.Scanner;
import java.util.List;

interface MenuItem {
    String getName();
    double getPrice();
    String getDescription();
}//we mn hena nshof el menu childs n3melohom abstract



class SimpleMenuItem implements MenuItem {
    public SimpleMenuItem(String name, double price, String description) { }
    public String getName() {return ""; }
    public double getPrice() { return 0; }
    public String getDescription() { return ""; }
    public String toString() { return "";  }
}
abstract class MenuType {// all menus will extend this class?
    public abstract List<MenuItem> mainCourse();
    public abstract List<MenuItem> toppings();

}

class MenuDisplay {
    public static void showMenuTypes() {
        System.out.println("===================================");
        System.out.println("     WELCOME TO OUR RESTAURANT     ");
        System.out.println("===================================");
        System.out.println("Choose your menu:");
        System.out.println("1. Vegetarian");
        System.out.println("2. Non-Vegetarian");
        System.out.println("3. Kids Menu");
        System.out.println("4. Desserts");
        System.out.println("5. Beverages");

        System.out.print("Enter choice (1-3): ");
    }
    public static void fullMenu() {
        System.out.println("\n--- Full Menu ---");
        for (MenuItem item : getMenuItems()) {

            System.out.println(item);

        }
    }


    public static MenuItem[] getMenuItems() {
        return new MenuItem[0];
    }
}

public class Restaurant {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuDisplay.showMenuTypes();
        String choice = sc.nextLine().trim();
        String menuType = switch (choice) {
            case "1" -> "vegetarian";
            case "2" -> "non-vegetarian";
            case "3" -> "vegan";
            case "4" -> "desserts";
            case "5" -> "beverages";    

            default -> { System.out.println("Invalid."); yield ""; }
        };

        MenuDisplay.fullMenu();

        System.out.println("\nMenu displayed successfully!");
        sc.close();
    }
}