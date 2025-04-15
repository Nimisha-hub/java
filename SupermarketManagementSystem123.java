import java.util.*;

class Product {
    int productId;
    String productName;
    String category;
    int quantity;
    double price;

    public Product(int productId, String productName, String category, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public void updateStock(int qty) {
        this.quantity -= qty;
    }

    public String toString() {
        return String.format("%-12d%-20s%-10s%-10d₹%-10.2f", productId, productName, category, quantity, price);
    }
}

class Supermarket {
    Product[] products = new Product[4];
    boolean isPaymentDone = false;

    public void addProduct(int index, Product product) {
        products[index] = product;
    }

    public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.productId == productId) {
                return product;
            }
        }
        return null;
    }

    public synchronized void processPayment(double amount) {
        while (isPaymentDone) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " - Processing payment: ₹" + amount);
        
        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " - Payment Successful!");
        isPaymentDone = true;
        notify();
    }

    public synchronized void updateStock(int productId, int quantity) {
        while (!isPaymentDone) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (isProductAvailable(productId, quantity)) {
            Product product = getProductById(productId);
            
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            product.updateStock(quantity);
            System.out.println(Thread.currentThread().getName() + " - Stock updated for Product ID: " + productId);
        }
        isPaymentDone = false;
        notify();
    }

    private boolean isProductAvailable(int productId, int quantity) {
        Product product = getProductById(productId);
        return product != null && product.quantity >= quantity;
    }

    public void printReceipt(Product product, int quantity, double totalAmount) {
        System.out.println("\n================================== RECEIPT ==================================");
        System.out.println("Product ID | Product Name       | Category   | Quantity | Price     ");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-12d%-20s%-10s%-10d₹%-10.2f\n", product.productId, product.productName, product.category, quantity, product.price);
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("Total Amount: ₹%.2f\n", totalAmount);
        System.out.println("=========================================================================");
    }
}

class PaymentThread implements Runnable {
    Supermarket supermarket;
    double amount;
    Thread t;

    public PaymentThread(Supermarket supermarket, double amount) {
        this.supermarket = supermarket;
        this.amount = amount;
        t = new Thread(this, "Payment Thread");
        t.setPriority(Thread.MAX_PRIORITY); // High priority for payment
        t.start();
    }

    @Override
    public void run() {
        supermarket.processPayment(amount);
    }
}

class StockUpdateThread implements Runnable {
    private Supermarket supermarket;
    private int productId;
    private int quantity;
    Thread t;

    public StockUpdateThread(Supermarket supermarket, int productId, int quantity) {
        this.supermarket = supermarket;
        this.productId = productId;
        this.quantity = quantity;
        t = new Thread(this, "Stock Update Thread");
        t.setPriority(Thread.MIN_PRIORITY); 
        t.start();
    }

    @Override
    public void run() {
        supermarket.updateStock(productId, quantity);
    }
}

public class SupermarketManagementSystem123 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Supermarket supermarket = new Supermarket();
        supermarket.addProduct(0, new Product(101, "Apple", "Fruits", 100, 20.0));
        supermarket.addProduct(1, new Product(102, "Banana", "Fruits", 150, 10.0));
        supermarket.addProduct(2, new Product(103, "Milk", "Dairy", 50, 40.0));
        supermarket.addProduct(3, new Product(104, "Bread", "Bakery", 200, 15.0));

        System.out.println("\nAvailable Products:");
        System.out.println("Product ID | Product Name       | Category   | Quantity | Price     ");
        System.out.println("--------------------------------------------------------------------------");
        for (Product product : supermarket.products) {
            System.out.println(product);
        }

        System.out.print("\nEnter Product ID for purchase: ");
        int productId = scanner.nextInt();

        Product product = supermarket.getProductById(productId);
        if (product == null) {
            System.out.println("Invalid Product ID. Exiting...");
            return;
        }

        System.out.print("Enter quantity to purchase: ");
        int quantity = scanner.nextInt();

        if (quantity <= 0 || quantity > product.quantity) {
            System.out.println("Invalid quantity. Exiting...");
            return;
        }

        double totalAmount = product.price * quantity;
        System.out.println("\nProcessing payment...");

        PaymentThread paymentThread = new PaymentThread(supermarket, totalAmount);
        StockUpdateThread stockUpdateThread = new StockUpdateThread(supermarket, productId, quantity);

        try {
            paymentThread.t.join(); 
            stockUpdateThread.t.join(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        supermarket.printReceipt(product, quantity, totalAmount);

        scanner.close();
    }
}
