package task2;

import task2.product.*;
import task2.user.*;
import task2.order.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Category electronics = new Category("Electronics", 23.0);
        Category books = new Category("Books", 5.0);

        Product laptop = new Product("Laptop", 1000.00, 5, electronics);
        Product book = new Product("Novel", 20.00, 50, books);

        Admin admin = new Admin("admin@example.com", "adminpass", "adminLogin", Role.SYSTEM_ADMIN, "secret");

        Address customerAddress = new Address("123 Marszalkowska", "Warsaw", "10001", "Poland");
        CardDetails cardDetails = new CardDetails("1234567812345678", "John Marston", LocalDate.of(2025, 12, 31), "123");
        Customer customer = new Customer("customer@example.com", "customerpass", "customerLogin", customerAddress);

        Order customerOrder = new Order(customer);

        customerOrder.addItem(laptop, 5);
        customerOrder.addItem(book, 2);

        laptop.addReview("John Marston", "Great laptop", 5);
        laptop.addReview("Arthur Morgan", "Good value", 4);

        book.addReview("King Back", "Good to read", 5);
        book.addReview("Charles Colt", "Decent book", 3);

        System.out.println("\nLaptop Reviews:");
        System.out.println(laptop);

        System.out.println("\nBook Reviews:");
        System.out.println(book);

        customer.setPaymentMethod(PaymentMethod.CARD);

        Invoice invoice = new Invoice(customerOrder);

        invoice.printInvoice();
    }
}
