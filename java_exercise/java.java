package java_exercise;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Menu {
    Map<String, Double> items;

    Menu() {
        items = new HashMap<>();
        items.put("Burger", 10.0);
        items.put("Pizza", 15.0);
        items.put("Salad", 8.0);
        items.put("Pasta", 12.0);
    }

    void show() {
        System.out.println("Menu:");
        for (Map.Entry<String, Double> item : items.entrySet()) {
            System.out.println(item.getKey() + ": $" + item.getValue());
        }
    }

    boolean aval(String delivery) {
        return items.containsKey(delivery);
    }

    double getPrice(String meal) {
        return items.get(meal);
    }
}

class Order {
    Map<String, Integer> delivery;

    Order() {
        delivery = new HashMap<>();
    }

    void add(String product, int quantity) {
        delivery.put(product, delivery.getOrDefault(product, 0) + quantity);
    }

    Map<String, Integer> getDelivery() {
        return delivery;
    }

    int getTotalQuantity() {
        int total = 0;
        for (int quantity : delivery.values()) {
            total += quantity;
        }
        return total;
    }
}

class PriceCalculator {
    double baseCost = 5;

    double calculateFinalPrice(Order order, Menu menu) {
        double totalCost = baseCost;
        int totalQuantity = order.getTotalQuantity();

        for (Map.Entry<String, Integer> item : order.getDelivery().entrySet()) {
            totalCost += menu.getPrice(item.getKey()) * item.getValue();
        }

        double discount = 0;
        if (totalQuantity > 10) {
            discount = 0.2;
        } else if (totalQuantity > 5) {
            discount = 0.1;
        }

        totalCost = totalCost - (totalCost * discount);

        if (totalCost > 100) {
            totalCost -= 25;
        } else if (totalCost > 50) {
            totalCost -= 10;
        }

        return totalCost;
    }
}

public class Taller {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Order order = new Order();
        PriceCalculator calculator = new PriceCalculator();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            menu.show();

            System.out.print("Enter meal name to order or 'done' to finish: ");
            String mealName = scanner.nextLine();
            if (mealName.equalsIgnoreCase("done")) {
                break;
            }

            if (!menu.aval(mealName)) {
                System.out.println("Meal not available. Please re-select.");
                continue;
            }

            System.out.print("Enter quantity for " + mealName + ": ");
            int quantity;
            try {
                quantity = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity. Please enter a positive integer.");
                continue;
            }

            if (quantity <= 0) {
                System.out.println("Invalid quantity. Please enter a positive integer.");
                continue;
            }

            order.add(mealName, quantity);

            if (order.getTotalQuantity() > 100) {
                System.out.println("Order quantity exceeds maximum limit of 100 meals. Please re-enter.");
                order = new Order();
            }
        }

        double totalCost = calculator.calculateFinalPrice(order, menu);

        System.out.println("Your Order:");
        for (Map.Entry<String, Integer> item : order.getDelivery().entrySet()) {
            System.out.println(item.getKey() + ": " + item.getValue());
        }

        System.out.println("Total Cost: $" + totalCost);
        System.out.print("Confirm order (yes/no): ");
        String confirm = scanner.nextLine();

        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Order canceled.");
            System.out.println(-1);
            scanner.close();
            return;
        }

        System.out.println("Order confirmed. Total cost is: $" + totalCost);
        scanner.close();
    }
}