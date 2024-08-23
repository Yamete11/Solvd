package task2.utils;

import task2.product.Category;
import task2.product.Product;
import task2.user.Address;
import task2.user.Admin;
import task2.user.Customer;
import task2.user.User;

import java.util.Scanner;

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
        System.out.print("Enter customer address (street, city, postal code, country): ");
        String addressInput = scanner.nextLine();
        String[] addressParts = addressInput.split(", ");
        Address address = new Address(addressParts[0], addressParts[1], addressParts[2], addressParts[3]);

        Customer newCustomer = new Customer(email, password, login, address);
        System.out.println("New customer created: " + newCustomer);
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
        System.out.print("Enter admin role (SYSTEM_ADMIN, MANAGER): ");
        String role = scanner.nextLine();
        System.out.print("Enter admin secret word: ");
        String secretWord = scanner.nextLine();

        Admin newAdmin = new Admin(email, password, login, role, secretWord);
        System.out.println("New admin created: " + newAdmin);
        return newAdmin;
    }

    public static Product createProduct(Category[] categories) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product title: ");
        String title = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter stock quantity: ");
        int stockQuantity = scanner.nextInt();

        System.out.println("Choose a category:");
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] != null) {
                System.out.println(i + ": " + categories[i].getTitle());
            }
        }
        int categoryIndex = scanner.nextInt();
        Category category = categories[categoryIndex];

        Product product = new Product(title, price, stockQuantity, category);
        System.out.println("Product created: " + product);
        return product;
    }

    public static Category createCategory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        System.out.print("Enter VAT percentage: ");
        double vat = scanner.nextDouble();
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
