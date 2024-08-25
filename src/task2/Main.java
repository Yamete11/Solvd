package task2;

import task2.exception.*;
import task2.product.*;
import task2.user.*;
import task2.order.*;
import task2.utils.UserUtils;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static boolean run = true;

    public static void main(String[] args) {

        ECom eCom = new ECom();

        try {
            eCom.addUser(null);
        } catch (InvalidUserException e) {
            System.err.println("Error: " + e.getMessage());
        }

        try {
            eCom.addProduct(new Product("Laptop", 0, new Category("Electronics", 15)));
        } catch (ProductOutOfStockException | DuplicateProductException e) {
            System.err.println("Error: " + e.getMessage());
        }

        try {
            eCom.addCategory(new Category("", 15));
        } catch (InvalidCategoryException e) {
            System.err.println("Error: " + e.getMessage());
        }

        try {
            eCom.addProduct(new Product("Laptop", 1000, new Category("Electronics", 15)));
            eCom.addProduct(new Product("Laptop", 1000, new Category("Electronics", 15)));
        } catch (DuplicateProductException | ProductOutOfStockException e) {
            System.err.println("Error: " + e.getMessage());
        }

        Customer customer = new Customer("john.marston@example.com", "password11215125", "john_marston", new Address("Sesame street", "City", "12345", "Country"), 50.0);
        Product product = new Product("Smartphone", 500, 1, new Category("Electronics", 15));

        Order order = new Order(customer);
        order.addItem(product, 1);

        try {
            customer.processPayment(order.calculateTotalAmount());
        } catch (InsufficientFundsException e) {
            System.err.println("Error: " + e.getMessage());
        }

        /*Category electronics = new Category("Electronics", 15.0);
        Category books = new Category("Books", 5.0);

        eCom.addCategory(electronics);
        eCom.addCategory(books);

        Product laptop = new Product("Laptop", 1000.00, 10, electronics);
        Product novel = new Product("Novel", 15.00, 100, books);
        Product phone = new Product("Phone", 500.00, 50, electronics);

        eCom.addProduct(laptop);
        eCom.addProduct(novel);
        eCom.addProduct(phone);

        Address customerAddress = new Address("Hi there street", "City", "12345", "Country");

        Customer customer1 = new Customer("john@example.com", "password123", "john_marst", customerAddress, 200.00);
        Customer customer2 = new Customer("art@example.com", "password456", "art_mor", customerAddress, new CardDetails("1234567812345678", "Arthur Morgan", LocalDate.of(2025, 12, 31), "123"), 500.00);

        eCom.addUser(customer1);
        eCom.addUser(customer2);

        User admin = new Admin("admin@example.com", "adminpass", "adminLogin", "System Administrator", "secretWord");

        eCom.addUser(admin);

        Order order1 = new Order(customer1);
        Order order2 = new Order(customer2);

        order1.addItem(laptop, 1);
        order1.addItem(novel, 2);
        order2.addItem(phone, 1);

        eCom.addOrder(order1);
        eCom.addOrder(order2);

        System.out.println("All Users:");
        eCom.printAllUsers();

        System.out.println("\nAll Orders:");
        eCom.printAllOrders();

        System.out.println("\nAll Categories:");
        eCom.printAllCategories();

        System.out.println("\nAll Products:");
        eCom.printAllProducts();

        System.out.println("\nSearch Results for 'Laptop':");
        Product[] searchResults = eCom.searchProductByName("Laptop");
        for (Product product : searchResults) {
            System.out.println(product);
        }

        System.out.println("\nFilter Results for 'Electronics':");
        Product[] filterResults = eCom.filterProductByCategory("Electronics");
        for (Product product : filterResults) {
            System.out.println(product);
        }

        System.out.println("\nTotal Revenue: $" + eCom.calculateTotalRevenue());*/

        /*printInfo();
        while (run) {
            System.out.print("Enter command: ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.length() == 0) {
                System.out.println("You have to enter a command");
            } else {
                switch (command) {
                    case "-cnc", "--create-new-customer" -> {
                        Customer newCustomer = UserUtils.createNewCustomer();
                        if (UserUtils.validateUser(newCustomer)) {
                            eCom.addUser(newCustomer);
                            System.out.println("Customer is valid");
                        } else {
                            System.out.println("Invalid customer data");
                        }
                    }
                    case "-cna", "--create-new-admin" -> {
                        Admin newAdmin = UserUtils.createNewAdmin();
                        if (UserUtils.validateUser(newAdmin)) {
                            eCom.addUser(newAdmin);
                            System.out.println("Admin is valid");
                        } else {
                            System.out.println("Invalid admin data");
                        }
                    }
                    case "-cc", "--create-category" -> {
                        Category newCategory = UserUtils.createCategory();
                        eCom.addCategory(newCategory);
                        System.out.println("Category added.");
                    }
                    case "-cl", "--category-list" -> eCom.printAllCategories();
                    case "-cp", "--create-product" -> {
                        Product newProduct = UserUtils.createProduct(eCom.getCategories());
                        eCom.addProduct(newProduct);
                        System.out.println("Product added.");
                    }
                    case "-po", "--print-orders" -> eCom.printAllOrders();
                    case "-pu", "--print-users" -> eCom.printAllUsers();
                    case "-q", "--quit" -> {
                        run = false;
                        System.out.println("Exiting the program");
                    }
                    default -> System.out.println("Unknown command: " + command);
                }
            }
        }*/

/*
           //Example for task 2
        ECom eCom = new ECom();

        Category electronics = new Category("Electronics", 23.0);
        Category books = new Category("Books", 5.0);

        Product laptop = new Product("Laptop", 1000.00, 5, electronics);
        Product book = new Product("Novel", 20.00, 50, books);

        Admin admin = new Admin("admin@example.com", "adminpass", "adminLogin", Role.SYSTEM_ADMIN, "secret");

        Address customerAddress = new Address("123 Marszalkowska", "Warsaw", "10001", "Poland");
        CardDetails cardDetails = new CardDetails("1234567812345678", "John Marston", LocalDate.of(2025, 12, 31), "123");
        Customer customer = new Customer("customer@example.com", "customerpass", "customerLogin", customerAddress, cardDetails);
        System.out.println(customer);

        Order customerOrder = new Order(customer);
        Order customerOrder1 = new Order(customer);


        customerOrder.addItem(laptop, 5);
        customerOrder.addItem(book, 2);

        customerOrder1.addItem(laptop, 1);
        customerOrder1.addItem(book, 3);



        laptop.addReview("John Marston", "Great laptop", 5);
        laptop.addReview("Arthur Morgan", "Good value", 4);

        book.addReview("King Back", "Good to read", 5);
        book.addReview("Charles Colt", "Decent book", 3);

        eCom.addOrder(customerOrder);
        eCom.addOrder(customerOrder1);
        System.out.println(eCom);


        System.out.println("\nLaptop Reviews:");
        System.out.println(laptop);

        System.out.println("\nBook Reviews:");
        System.out.println(book);

        customer.setPaymentMethod(PaymentMethod.CARD);

        Invoice invoice = new Invoice(customerOrder);

        invoice.printInvoice();*/
    }

    private static void printInfo() {
        System.out.println("========================== SolvdApp ==========================");
        System.out.println("This program allows you to manage orders, products, and invoices.");
        System.out.println("Commands:");
        System.out.println("-cnc : --create-new-customer : Creates a new customer");
        System.out.println("-cna : --create-new-admin : Creates a new admin");
        System.out.println("-cc : --create-category : Creates a new category");
        System.out.println("-cl : --category-list : Print all the categories");
        System.out.println("-cp : --create-product : Create a new product");
        System.out.println("-po : --print-orders : Print all orders");
        System.out.println("-pu : --print-users : Print all users");
        System.out.println("-q : --quit : Quits the program.");
    }
}
