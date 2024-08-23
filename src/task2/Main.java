package task2;

import task2.product.*;
import task2.user.*;
import task2.order.*;
import task2.utils.UserUtils;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static boolean run = true;
    public static Category[] categories = new Category[10];
    private static int categoryCount = 0;
    public static Product[] products = new Product[10];
    private static int productCount = 0;

    public static void main(String[] args) {
        ECom eCom = new ECom();

        printInfo();
        while (run) {
            System.out.print("Enter command: ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.length() == 0) {
                System.out.println("You have to enter a command");
            } else {
                switch (command) {
                    case "-cnc":
                    case "--create-new-customer":
                        Customer newCustomer = UserUtils.createNewCustomer();
                        if (UserUtils.validateUser(newCustomer)) {
                            eCom.addUser(newCustomer);
                            System.out.println("Customer is valid");
                        } else {
                            System.out.println("Invalid customer data");
                        }
                        break;

                    case "-cna":
                    case "--create-new-admin":
                        Admin newAdmin = UserUtils.createNewAdmin();
                        if (UserUtils.validateUser(newAdmin)) {
                            eCom.addUser(newAdmin);
                            System.out.println("Admin is valid");
                        } else {
                            System.out.println("Invalid admin data");
                        }
                        break;

                    case "-cc":
                    case "--create-category":
                        if (categoryCount < categories.length) {
                            categories[categoryCount++] = UserUtils.createCategory();
                        } else {
                            System.out.println("Category storage is full");
                        }
                        break;

                    case "-cl":
                    case "--category-list":
                        UserUtils.printCategoryList(categories);
                        break;

                    case "-cp":
                    case "--create-product":
                        if (productCount < products.length) {
                            products[productCount++] = UserUtils.createProduct(categories);
                        } else {
                            System.out.println("Product storage is full");
                        }
                        break;

                    case "-po":
                    case "--print-orders":
                        eCom.printAllOrders();
                        break;

                    case "-pu":
                    case "--print-users":
                        eCom.printAllUsers();
                        break;

                    case "-q":
                    case "--quit":
                        run = false;
                        System.out.println("Exiting the program");
                        break;

                    default:
                        System.out.println("Unknown command: " + command);
                        break;
                }
            }
        }


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
