package task2.utils;

import task2.product.Category;
import task2.product.Product;
import task2.user.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class UserUtils {

    public static boolean validateUser(User user) {
        boolean isEmailValid = user.getEmail().contains("@") && user.getEmail().contains(".");
        boolean isPasswordValid = user.getPassword().length() >= 8;
        return isEmailValid && isPasswordValid;
    }

    public static Customer createNewCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        System.out.print("Enter customer password: ");
        String password = scanner.nextLine();
        System.out.print("Enter customer login: ");
        String login = scanner.nextLine();

        Address address = null;
        while (address == null) {
            System.out.print("Enter customer address (street, city, postal code, country): ");
            String addressInput = scanner.nextLine();
            String[] addressParts = addressInput.split(", ");
            if (addressParts.length == 4) {
                address = new Address(addressParts[0], addressParts[1], addressParts[2], addressParts[3]);
            } else {
                System.out.println("Invalid address format. Please enter in the format: street, city, postal code, country.");
            }
        }

        System.out.print("Would you like to add card details? (yes/no): ");
        String addCardDetails = scanner.nextLine();

        CardDetails cardDetails = null;
        if (addCardDetails.equalsIgnoreCase("yes")) {
            System.out.print("Enter card number: ");
            String cardNumber = scanner.nextLine();
            System.out.print("Enter cardholder name: ");
            String cardholderName = scanner.nextLine();
            System.out.print("Enter expiration date (YYYY-MM-DD): ");
            LocalDate expirationDate = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter CVV: ");
            String cvv = scanner.nextLine();

            cardDetails = new CardDetails(cardNumber, cardholderName, expirationDate, cvv);
        }

        double accountBalance = 0.0;
        while (true) {
            System.out.print("Enter initial account balance (or leave blank for 0.0): ");
            String balanceInput = scanner.nextLine();
            if (balanceInput.isEmpty()) {
                break;
            }
            try {
                accountBalance = Double.parseDouble(balanceInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        Customer newCustomer;
        if (cardDetails != null) {
            newCustomer = new Customer(email, password, login, address, cardDetails, accountBalance);
        } else {
            newCustomer = new Customer(email, password, login, address, accountBalance);
        }

        System.out.println("Customer created: " + newCustomer);
        return newCustomer;
    }

    public static Admin createNewAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter admin email: ");
        String email = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        System.out.print("Enter admin login: ");
        String login = scanner.nextLine();

        String role = null;
        while (role == null) {
            System.out.print("Enter admin role (SYSTEM_ADMIN, MANAGER): ");
            String inputRole = scanner.nextLine();
            if ("SYSTEM_ADMIN".equalsIgnoreCase(inputRole) || "MANAGER".equalsIgnoreCase(inputRole)) {
                role = inputRole.toUpperCase();
            } else {
                System.out.println("Invalid role. Please enter 'SYSTEM_ADMIN' or 'MANAGER'.");
            }
        }

        System.out.print("Enter admin secret word: ");
        String secretWord = scanner.nextLine();

        Admin newAdmin = new Admin(email, password, login, role, secretWord);
        System.out.println("Admin data: " + newAdmin);
        return newAdmin;
    }

    public static Product createProduct(Set<Category> categories) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product title: ");
        String title = scanner.nextLine();

        double price = 0.0;
        while (true) {
            System.out.print("Enter product price: ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid price.");
            }
        }

        int stockQuantity = 0;
        while (true) {
            System.out.print("Enter stock quantity: ");
            try {
                stockQuantity = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid stock quantity.");
            }
        }

        System.out.println("Choose a category:");
        List<Category> categoryList = new ArrayList<>(categories);
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.println(i + ": " + categoryList.get(i));
        }

        Category category = null;
        while (category == null) {
            System.out.print("Enter category index: ");
            try {
                int categoryIndex = Integer.parseInt(scanner.nextLine());
                if (categoryIndex >= 0 && categoryIndex < categoryList.size()) {
                    category = categoryList.get(categoryIndex);
                } else {
                    System.out.println("Invalid index. Please enter a number between 0 and " + (categoryList.size() - 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        Product product = new Product(title, price, stockQuantity, category);
        System.out.println("Product created: " + product);
        return product;
    }

    public static Category createCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();

        double vat = 0.0;
        while (true) {
            System.out.print("Enter VAT percentage: ");
            try {
                vat = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for VAT.");
            }
        }

        Category category = new Category(categoryName, vat);
        System.out.println("Category created: " + category);
        return category;
    }

    public static void printCategoryList(Category[] categories) {
        System.out.println("Category List:");
        for (Category category : categories) {
            if (category != null) {
                System.out.println(category);
            }
        }
    }
}
