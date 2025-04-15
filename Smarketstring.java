import java.util.*;

class Production {
    final int orderId;
    final int customerId;
    final int[] productList;
    final String orderDate;
    final String orderStatus;
    final double totalAmount;

    public Production(int orderId, int customerId, int[] productList, String orderDate, String orderStatus, double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productList = productList;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
    }

    public void display_ord() {
        StringBuffer details = new StringBuffer();
        details.append("\n\033[96m=================== ORDER DETAILS ===================\033[97m\n");
        details.append("Order ID: ").append(orderId).append("\n");
        details.append("Customer ID: ").append(customerId).append("\n");
        details.append("Product List: ");
        
        for (int product : productList) {
            details.append(product).append(" ");
        }
        
        details.append("\nOrder Date: ").append(orderDate);
        details.append("\nOrder Status: ").append(orderStatus);
        details.append("\nTotal Amount: $").append(totalAmount);

        if (details.length() > 100) {
            details.append("\n\033[93m(Note: This order contains multiple items.)\033[97m");
        }

        System.out.println(details.toString());
    }
}

class ProductionWithDiscount extends Production {
    final double discountApplied;
    final String paymentMethod;
    final String orderPriority;
    final String customerFeedback;

    public ProductionWithDiscount(int orderId, int customerId, int[] productList, String orderDate, String orderStatus, double totalAmount,
                                  double discountApplied, String paymentMethod, String orderPriority, String customerFeedback) {
        super(orderId, customerId, productList, orderDate, orderStatus, totalAmount);
        this.discountApplied = discountApplied;
        this.paymentMethod = paymentMethod;
        this.orderPriority = orderPriority;
        this.customerFeedback = customerFeedback;
    }

    public void displayOrderWithDiscount() {
        display_ord();
        StringBuffer discountDetails = new StringBuffer();
        discountDetails.append("\n\033[93m============= Additional Details =============\033[97m\n");
        discountDetails.append("Discount Applied: $").append(discountApplied).append("\n");
        discountDetails.append("Payment Method: ").append(paymentMethod).append("\n");
        discountDetails.append("Order Priority: ").append(orderPriority).append("\n");
        discountDetails.append("Customer Feedback: ").append(customerFeedback);
        
        if (orderPriority.equalsIgnoreCase("High")) {
            discountDetails.replace(0, discountDetails.length(), discountDetails.toString().replace("Order Priority: High", "🚀 Order Priority: HIGH"));
        }

        if (discountApplied == 0) {
            int startIndex = discountDetails.indexOf("Discount Applied:");
            int endIndex = discountDetails.indexOf("\n", startIndex);
            discountDetails.delete(startIndex, endIndex + 1);
        }

        System.out.println(discountDetails.toString());
    }
}

public class Smarketstring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\t\t\t\033[32m==========================================================");
        System.out.println("\t\t\t\033[34m  Super Market Management System with StringBuffer Methods");
        System.out.println("\t\t\t\033[32m==========================================================");
        System.out.println();

        System.out.println("\033[32mEnter the number of orders: \033[97m");
        int numOrders = scanner.nextInt();

        if (numOrders <= 0) {
            System.out.println("\033[31mInvalid number of orders. Please enter a positive integer.\033[97m");
            scanner.close();
            return;
        }

        ProductionWithDiscount[] orders = new ProductionWithDiscount[numOrders];

        for (int i = 0; i < orders.length; i++) {
            System.out.println("\n\033[96m===== Enter details for Order " + (i + 1) + " =====\033[97m");

            System.out.println("\033[32mEnter Order ID: \033[97m");
            int orderId = scanner.nextInt();
            if (orderId <= 0) {
                System.out.println("\033[31mOrder ID must be a positive number.\033[97m");
                return;
            }

            System.out.println("\033[32mEnter Customer ID: \033[97m");
            int customerId = scanner.nextInt();
            if (customerId <= 0) {
                System.out.println("\033[31mCustomer ID must be a positive number.\033[97m");
                return;
            }

            System.out.println("\033[32mEnter number of products: \033[97m");
            int numProducts = scanner.nextInt();
            if (numProducts <= 0) {
                System.out.println("\033[31mNumber of products must be greater than zero.\033[97m");
                return;
            }
            int[] productList = new int[numProducts];

            System.out.println("\033[32mEnter product IDs: \033[97m");
            for (int j = 0; j < numProducts; j++) {
                productList[j] = scanner.nextInt();
                if (productList[j] <= 0) {
                    System.out.println("\033[31mProduct ID must be a positive number.\033[97m");
                    return;
                }
            }

            scanner.nextLine();

            System.out.println("\033[32mEnter Order Date (YYYY-MM-DD): \033[97m");
            String orderDate = scanner.nextLine();
            if (!orderDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("\033[31mInvalid date format. Please use YYYY-MM-DD.\033[97m");
                return;
            }

            System.out.println("\033[32mEnter Order Status: \033[97m");
            String orderStatus = scanner.nextLine();
            if (orderStatus.isEmpty()) {
                System.out.println("\033[31mOrder status cannot be empty.\033[97m");
                return;
            }

            System.out.println("\033[32mEnter Total Amount: \033[97m");
            double totalAmount = scanner.nextDouble();
            if (totalAmount <= 0) {
                System.out.println("\033[31mTotal amount must be greater than zero.\033[97m");
                return;
            }

            System.out.println("\033[32mEnter Discount Applied: \033[97m");
            double discountApplied = scanner.nextDouble();
            if (discountApplied < 0) {
                System.out.println("\033[31mDiscount cannot be negative.\033[97m");
                return;
            }
            scanner.nextLine();

            System.out.println("\033[32mEnter Payment Method: \033[97m");
            String paymentMethod = scanner.nextLine();
            if (paymentMethod.isEmpty()) {
                System.out.println("\033[31mPayment method cannot be empty.\033[97m");
                return;
            }

            System.out.println("\033[32mEnter Order Priority (High, Normal, Low): \033[97m");
            String orderPriority = scanner.nextLine();
            if (!orderPriority.equalsIgnoreCase("High") && !orderPriority.equalsIgnoreCase("Normal") && !orderPriority.equalsIgnoreCase("Low")) {
                System.out.println("\033[31mInvalid priority. Enter High, Normal, or Low.\033[97m");
                return;
            }

            System.out.println("\033[32mEnter Customer Feedback: \033[97m");
            String customerFeedback = scanner.nextLine();
            if (customerFeedback.isEmpty()) {
                System.out.println("\033[31mFeedback cannot be empty.\033[97m");
                return;
            }

            orders[i] = new ProductionWithDiscount(
                    orderId,
                    customerId,
                    productList,
                    orderDate,
                    orderStatus,
                    totalAmount,
                    discountApplied,
                    paymentMethod,
                    orderPriority,
                    customerFeedback
            );
        }

        System.out.println("\n\033[96m============= Displaying Order Details =============\033[97m");
        for (int i = 0; i < orders.length; i++) {
            orders[i].displayOrderWithDiscount();
            System.out.println("\n\033[96m------------------------------------------------------\033[97m");
        }

        scanner.close();
    }
}
