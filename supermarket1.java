import inventory.Inventory;
import java.util.Scanner;

public class supermarket1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\033[94m\t\t\t\t\t\t================================================================");
        System.out.println("\033[94m\t\t\t\t\t\tProgram to Implement Packages for Supermarket Management System");
        System.out.println("\033[94m\t\t\t\t\t\t=================================================================");

        System.out.println("\033[97m");
        System.out.println();

        System.out.println("\033[32mEnter the number of products: ");
        System.out.println("\033[97m------------------------------");
        int numProducts = scanner.nextInt();
        scanner.nextLine();


if (numProducts <= 0) {
    System.out.println("\033[91mError: The number of products must be greater than zero!\033[97m");
    return;
}
        Inventory[] products = new Inventory[numProducts];

        for (int i = 0; i < numProducts; i++) {
            System.out.println("\n\033[32mEnter details for product " + (i + 1) + ":");
            System.out.println("\033[97m-------------------------------------\033[97m");

            System.out.println("\033[32mProduct Name: ");
            System.out.println("\033[97m-------------\033[97m");
            String productName = scanner.nextLine().trim();
            if (productName.isEmpty()) {
                System.out.println("\033[91mError: Product name cannot be empty!\033[97m");
                continue;
            }

            System.out.println("\033[32mProduct ID: ");
            System.out.println("\033[97m-------------\033[97m");
            int productId = scanner.nextInt();
            scanner.nextLine();
            if (productId <= 0) {
                System.out.println("\033[91mError: Product ID must be a positive integer!\033[97m");
                continue;
            }

            System.out.println("\033[32mPrice: ");
            System.out.println("\033[97m-------\033[97m");
            double price = scanner.nextDouble();
            if (price <= 0) {
                System.out.println("\033[91mError: Price must be a positive number!\033[97m");
                continue;
            }

            System.out.println("\033[32mQuantity: ");
            System.out.println("\033[97m----------\033[97m");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            if (quantity < 0) {
                System.out.println("\033[91mError: Quantity cannot be negative!\033[97m");
                continue;
            }

            products[i] = new Inventory(productName, productId, price, quantity);
        }

        System.out.println("\nProduct Inventory:");
        for (Inventory product : products) {
            if (product != null) {
                product.displayInfo();
            }
        }

        scanner.close();
    }
}
