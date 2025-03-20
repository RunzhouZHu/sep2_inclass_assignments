import java.util.*;

public class ShoppingCartCalculator {
    // Method to calculate the total cost
    public static double calculateTotalCost(HashMap<Double, Integer> shoppingCart) {
        double totalCost = 0.0;
        for (HashMap.Entry<Double, Integer> entry : shoppingCart.entrySet()) {
            double price = entry.getKey() * entry.getValue();
            totalCost += price;
        }
        return totalCost;
    }

    public static void main(String[] args) {

        // Localization
        System.out.println("Select the language: ");
        System.out.println("1. English");
        System.out.println("2. Swedish");
        System.out.println("3. Finnish");
        System.out.println("4. Japanese");

        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            System.out.println("Please enter a valid option!");
        }
        Locale locale = switch (choice) {
            case 1 -> new Locale.Builder().setLanguage("en").setRegion("US").build();
            case 2 -> new Locale.Builder().setLanguage("sv").setRegion("SE").build();
            case 3 -> new Locale.Builder().setLanguage("fi").setRegion("FI").build();
            case 4 -> new Locale.Builder().setLanguage("ja").setRegion("JP").build();
            default -> null;
        };

        ResourceBundle rb;
        try {
            assert locale != null;
            rb = ResourceBundle.getBundle("messages", locale);
        } catch (Exception e) {
            System.out.println("Invalid language is not in the list");
            rb = ResourceBundle.getBundle("messages", new Locale.Builder().setLanguage("en").setRegion("US").build());
        }

        // Functions
        // 1. Prompt the user to enter the number of items they want to purchase.
        HashMap<Double, Integer> shoppingCart = new HashMap<>();
        System.out.print(rb.getString("1"));
        int numberOfItems = scanner.nextInt();

        // 2. For each item, ask the user for the price and quantity.
        for (int i = 0; i < numberOfItems; i++) {
            System.out.print(rb.getString("2") + " " + i + 1 + " :  ");
            double price = scanner.nextDouble();
            System.out.print(rb.getString("3") + " " + i + 1 + " :  ");
            int quantity = scanner.nextInt();
            shoppingCart.put(price, quantity);
        }

        // 3. Calculate the total cost of each item (price × quantity).
        // 4. Calculate the total cost of all items in the shopping cart.
        double totalCost = ShoppingCartCalculator.calculateTotalCost(shoppingCart);

        // 5. Display the total cost of the shopping cart to the user.
        System.out.printf(rb.getString("4"), totalCost);

        scanner.close();
    }
}