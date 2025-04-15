import java.util.Scanner;

class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double calculateTotal() {
        return price * quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}

class DiscountedItem extends Item {
    private double discount;

    public DiscountedItem(String name, double price, int quantity, double discount) {
        super(name, price, quantity);
        this.discount = discount;
    }

    @Override
    public double calculateTotal() {
        double totalBeforeDiscount = super.calculateTotal();
        return totalBeforeDiscount - (totalBeforeDiscount * discount / 100);
    }
}

final class Bill {
    private Item[] items;

    public Bill(Item[] items) {
        this.items = items;
    }

    public double calculateBill() {
        double totalBill = 0;

        System.out.println("Product Name\tPrice");

        for (Item item : items) {
            double itemTotal = item.calculateTotal();
            System.out.println(item.getName() + " " + itemTotal);
            totalBill += itemTotal;
        }

        return totalBill;
    }
}

public class SupermarketSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t\t\t\t\t\t\t\t\033[0;97m===============================================");

        System.out.println("\t\t\t\t\t\t\t\t\033[0;95mWelcome to SuperMarket Management System :);)");

        System.out.println("\t\t\t\t\t\t\t\t\033[0;97m===============================================");


        System.out.println("\033[0;92mEnter number of items: ");
        System.out.println("\033[0;97m**********************");
        int numItems = scanner.nextInt();
        scanner.nextLine();

        Item[] items = new Item[numItems];

        for (int i = 0; i < numItems; i++) {
    System.out.println("\033[0;92mEnter details for item " + (i + 1));
    System.out.println("\033[0;97m************************");
    System.out.println("");


    System.out.println("\033[0;92mEnter item name: ");
    System.out.println("\033[0;97m****************");
    String name = scanner.nextLine();
    if (name.isEmpty()) {
        System.out.println("\033[0;91mItem name cannot be empty. Please enter a valid name.");
        name = scanner.nextLine(); 
    }


    System.out.println("\033[0;92mEnter item price: ");
    System.out.println("\033[0;97m*****************");
    double price = scanner.nextDouble();
    if (price <= 0) {
        System.out.println("\033[0;91mPrice must be greater than 0. Please enter a valid price.");
        price = scanner.nextDouble(); 
    }

    
    System.out.println("\033[0;92mEnter item quantity: ");
    System.out.println("\033[0;97m*******************");
    int quantity = scanner.nextInt();
    if (quantity <= 0) {
        System.out.println("\033[0;91mQuantity must be greater than 0. Please enter a valid quantity.");
        quantity = scanner.nextInt(); 
    }

    
    scanner.nextLine();  
    System.out.println("\033[0;92mIs this item discounted? (yes/no): ");
    System.out.println("\033[0;97m*********************************");
    String isDiscounted = scanner.nextLine().toLowerCase();
    if (!(isDiscounted.equals("yes") || isDiscounted.equals("no"))) {
        System.out.println("\033[0;91mPlease enter 'yes' or 'no'.");
        isDiscounted = scanner.nextLine().toLowerCase();
    }

    if (isDiscounted.equals("yes")) {
        System.out.print("\033[0;92mEnter discount percentage (0-100): ");
        System.out.println("\033[0;97m*****************************");

        double discount = scanner.nextDouble();
        if (discount < 0 || discount > 100) {
            System.out.println("\033[0;91mDiscount percentage must be between 0 and 100. Please enter a valid discount.");
            discount = scanner.nextDouble(); 
        }
        items[i] = new DiscountedItem(name, price, quantity, discount);
        scanner.nextLine();  // Consume newline
    } else {
        items[i] = new Item(name, price, quantity);
    }
}

        Bill bill = new Bill(items);

        double total = bill.calculateBill();
        System.out.println("\n\033[1;93mTotal Bill: Rupees" + total);
        System.out.println("\033[1;96mThank You Visit Again !!");

        scanner.close();
    }
}
