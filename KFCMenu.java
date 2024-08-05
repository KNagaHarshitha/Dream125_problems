package Dream125_problems;

import java.util.Scanner;

class KFCBillingCounter {
    private String[] menuItems = {"Chicken Bucket", "Zinger Burger", "Fries", "Coke"};
    private double[] menuPrices = {500.0, 200.0, 100.0, 50.0};
    private String[][] orders;
    private int tokenNumber;
    private int orderCount;

    //initializing the token and order number
    public KFCBillingCounter() {
        orders = new String[100][2]; // assuming max 100 orders
        tokenNumber = 1;
        orderCount = 0;
    }

    //display the mennu along with the prices in "$"
    public void displayMenu() {
        System.out.println("Menu:");
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println(menuItems[i] + ": $" + menuPrices[i]);
        }
    }

    //taking the order from the customer
    public String[] takeOrder() {
        String[] order = new String[2];
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the item you want to order (or 'done' to finish): ");
            String item = scanner.nextLine();
            if (item.equalsIgnoreCase("done")) {
                break;
            }
            boolean found = false;
            for (int i = 0; i < menuItems.length; i++) {
                if (item.equals(menuItems[i])) {
                    found = true;
                    System.out.print("Enter the quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    order[0] = item;
                    order[1] = String.valueOf(quantity);
                    return order;
                }
            }
            if (!found) {
                System.out.println("Item not found in the menu. Please try again.");
            }
        }
        return order;
    }

    //calculating the bill
    public double calculateBill(String[] order) {
        double total = 0;
        for (int i = 0; i < menuItems.length; i++) {
            //checks if the first item in the order array is equal to the i-th item in the menuItems array
            if (order[0].equals(menuItems[i])) {
                total += menuPrices[i] * Integer.parseInt(order[1]); //order[1] represents the quantity
                return total;
            }
        }
        return total;
    }

    public void makePayment(double bill) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please pay $" + String.format("%.2f", bill) + ": ");
            double payment = scanner.nextDouble();
            scanner.nextLine(); // Consume newline left-over
            if (payment >= bill) {
                System.out.println("Payment successful!");
                return;
            } else {
                System.out.println("Insufficient payment. Please try again.");
            }
        }
    }

    public void serveCustomer() {
        System.out.println("\nToken Number: " + tokenNumber);
        displayMenu();
        String[] order = takeOrder();
        double bill = calculateBill(order);
        System.out.println("\nYour bill is: $" + String.format("%.2f", bill));
        makePayment(bill);
        System.out.println("Please wait for your order...");
        orders[orderCount][0] = order[0]; //order[0] represents the name of the menu item
        orders[orderCount][1] = order[1]; //order[1] represents the quantity
        orderCount++;
        tokenNumber++;
    }
}

public class KFCMenu {
    public static void main(String[] args) {
        KFCBillingCounter counter1 = new KFCBillingCounter();
        KFCBillingCounter counter2 = new KFCBillingCounter();
        KFCBillingCounter counter3 = new KFCBillingCounter();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nSelect a counter:");
            System.out.println("1. Counter 1");
            System.out.println("2. Counter 2");
            System.out.println("3. Counter 3");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            if (choice == 1) {
                counter1.serveCustomer();
            } else if (choice == 2) {
                counter2.serveCustomer();
            } else if (choice == 3) {
                counter3.serveCustomer();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
