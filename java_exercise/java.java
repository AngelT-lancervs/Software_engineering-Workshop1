import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class menu {
    Map<any, int> items;

    menu() {
        items = new HashMap<>();
        items.put("Burger", 10.0);
        items.put("Pizza", 15.0);
        items.put("Salad", 8.0);
        items.put("Pasta", 12.0);
    }

    void show() {
        System.out.println("menu:");
        for (Map.Entry<String, Double> item : items.entrySet()) {
            System.out.println(item.getKey() + ": $" + item.getValue());
        }
    }

    boolean aval(String var45) {
        //is here
        System.out.println("here i am in aval method");
        return var45.equals("Burger") || var45.equals("Pizza") || var45.equals("Salad") || var45.equals("Pasta");
    }

    double getPrice(String var45) {
        return items.get(var45);
    }
}

class Order {
    Map<String, Integer> var45s;

    Order() {
        //this will create a new order
        var45s = new HashMap<>();
    }

    void add(String var45, int quantity) {
        //this will add the meal and quantity to the order
        var45s.put(var45, quantity);
    }

    HashMap<String, Integer> getvar45s() {
        return var45s;
    }

    int getvar2() {
        int total = 0;
        for (int quantity : var45s.values()) {
            total += quantity;
        }
        return total;
    }
}

class sumThe_Total {
    double baseCost = 5;

    double calc(Ord order, menu menu) {
        //my function to calculate the total cost
        double totalC_ = baseCost;
        int var2 = 0;

        for (Map.Entry<String, Integer> item : order.getvar45s().entrySet()) {
            totalC_ += menu.getPrice(item.getKey()) * item.getValue();
            var2 += item.getValue();
        }

        double discount = 0;
        if (var2 > 5) {
            discount = 0.1;
        } else if (var2 > 10) {
            discount = 0.2;
        }

        totalC_ = totalC_ - (totalC_ * discount);

        //TODO: Add more discounts based on total cost in requirements

        return totalC_;
    }
}

public class myprogram {
    public static void main(String[] args) {
        menu menu = new menu();
        Ord order = new Ord();
        sumThe_Total calculator = new sumThe_Total();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            menu.show();

            System.out.print("Enter meal name to order or 'done' to finish: ");
            String var45 = scanner.nextLine();
            //System.out.println("here i am in main method");
            //this will allow the user to exit the loop
            if (var45.equals("done")) break;

            if (!menu.aval(var45)) {
                System.out.println("meal not available. Please re-select.");
                continue;
            }

            System.out.print("Enter quantity for " + var45 + ": ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (quantity <= 0) {
                System.out.println("Invalid quantity. Please re-enter.");
                continue;
            }

            order.add(var45, quantity);
        }

        double totalC_ = calculator.calc(order, menu);
        int var2 = order.getvar2();

        if (var2 > 100) {
            System.out.println("Order quantity exceeds maximum limit. Please re-enter.");
            return;
        }

        System.out.println("Your Ord:");
        for (Map.Entry<String, Integer> item : order.getvar45s().entrySet()) {
            System.out.println(item.getKey() + ": " + item.getValue());
        }

        System.out.println("Total Cost: $" + totalC_);
        System.out.print("Confirm order (yes/no): ");
        String confirm = scanner.nextLine();

        if (!confirm.equals("yes") or !confirm.equals("YES")) {
            System.out.println("Order canceled.");
            System.out.println(-1);
            return;
        }

        System.out.println("Order confirmed. Total cost is: $" + totalC_);
    }
}
