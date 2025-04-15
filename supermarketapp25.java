import java.util.*;

class Stores {
    private int id;
    private double price;
    private String category;
    private int quantity;

    public Stores(int id, double price, String category, int quantity) {
        this.id = id;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int soldQuantity) {
        this.quantity -= soldQuantity;
    }

    public void displayInfo() {
        System.out.println("\033[0;32mProduct ID: " + id);  // Green color
        System.out.println("Price: $" + price);
        System.out.println("Category: " + category);
        System.out.println("Quantity in stock: " + quantity);
        System.out.println("----------------------------------\033[0m");  // Reset color
    }
}

public class supermarketapp25 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[94m\t\t\t\t\t\t=========================================================================c");
        System.out.println("\033[94m\t\t\t\t\t\tProgram to Implement Exception Handling for Supermarket Management System");
        System.out.println("\033[94m\t\t\t\t\t\t=========================================================================");
        int numProducts = 0;

        try {
            System.out.print("\033[0;34mEnter the number of products: \033[0m");
            numProducts = scanner.nextInt();
            if (numProducts <= 0) {
                System.out.println("\033[0;31mPlease enter a positive number for the number of products.\033[0m");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("\033[0;31mInvalid input. Please enter a valid integer for the number of products.\033[0m");
            scanner.nextLine();
            return;
        }

        scanner.nextLine();
        Stores[] products = new Stores[numProducts];

        for (int i = 0; i < numProducts; i++) {
            System.out.println("\n\033[0;36mEnter details for product " + (i + 1) + ":\033[0m");

            int id = 0;
            double price = 0.0;
            int quantity = 0;
            String category = "";

            try {
                System.out.print("\033[0;33mProduct ID: \033[0m");
                id = scanner.nextInt();
                if (id <= 0) {
                    System.out.println("\033[0;31mProduct ID should be a positive integer.\033[0m");
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[0;31mInvalid input. Please enter a valid integer for the Product ID.\033[0m");
                scanner.nextLine();
                return;
            }

            try {
                System.out.print("\033[0;33mPrice: \033[0m");
                price = scanner.nextDouble();
                if (price <= 0) {
                    throw new InvalidProductPriceException("Product price cannot be zero or negative.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[0;31mInvalid input. Please enter a valid number for the price.\033[0m");
                scanner.nextLine();
                return;
            } catch (InvalidProductPriceException e) {
                System.out.println("\033[0;31mCustom Error: " + e.getMessage() + "\033[0m");
                return;
            }

            scanner.nextLine();

            System.out.print("\033[0;33mCategory: \033[0m");
            category = scanner.nextLine();

            try {
                System.out.print("\033[0;33mQuantity in stock: \033[0m");
                quantity = scanner.nextInt();
                if (quantity <= 0) {
                    System.out.println("\033[0;31mQuantity cannot be negative.\033[0m");
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[0;31mInvalid input. Please enter a valid integer for quantity.\033[0m");
                scanner.nextLine();
                return;
            }

            products[i] = new Stores(id, price, category, quantity);
        }

        List<Stores> orderList = new ArrayList<>();
        List<Integer> orderQuantities = new ArrayList<>();
        double totalAmount = 0;

        System.out.println("\n\033[0;36mEnter order details:\033[0m");
        while (true) {
            System.out.print("\033[0;33mEnter Product ID to purchase (or 0 to finish): \033[0m");
            int orderId = scanner.nextInt();
            if (orderId == 0) break;

            Stores selectedProduct = null;
            for (Stores product : products) {
                if (product.getId() == orderId) {
                    selectedProduct = product;
                    break;
                }
            }

            if (selectedProduct == null) {
                System.out.println("\033[0;31mInvalid Product ID. Try again.\033[0m");
                continue;
            }

            System.out.print("\033[0;33mEnter quantity to purchase: \033[0m");
            int orderQuantity = scanner.nextInt();

            if (orderQuantity > selectedProduct.getQuantity()) {
                System.out.println("\033[0;31mNot enough stock available. Try again.\033[0m");
                continue;
            }

            selectedProduct.reduceQuantity(orderQuantity);
            orderList.add(selectedProduct);
            orderQuantities.add(orderQuantity);
            totalAmount += selectedProduct.getPrice() * orderQuantity;
        }

        if (!orderList.isEmpty()) {
            System.out.println("\n\033[0;32m================ ORDER BILL =================\033[0m");
            System.out.println("Product ID | Category       | Price  | Quantity | Total");
            System.out.println("----------------------------------------------------------");
            for (int i = 0; i < orderList.size(); i++) {
                Stores product = orderList.get(i);
                int qty = orderQuantities.get(i);
                double itemTotal = product.getPrice() * qty;

                System.out.printf("%-10d | %-13s | $%-6.2f | %-8d | $%-6.2f\n",
                        product.getId(), product.getCategory(), product.getPrice(), qty, itemTotal);
            }
            System.out.println("----------------------------------------------------------");
            System.out.printf("\033[0;34mTOTAL AMOUNT: $%.2f\n\033[0m", totalAmount);
            System.out.println("\033[0;32m==========================================================\033[0m");
        } else {
            System.out.println("\033[0;31mNo products purchased.\033[0m");
        }

        scanner.close();
    }

    static class InvalidProductPriceException extends Exception {
        public InvalidProductPriceException(String message) {
            super(message);
        }
    }
}
