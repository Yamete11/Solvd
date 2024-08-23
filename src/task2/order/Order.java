package task2.order;

import task2.utils.Discountable;
import task2.product.Product;
import task2.user.Customer;
import task2.utils.Taxable;

import java.time.LocalDate;
import java.util.Objects;

public class Order implements Taxable, Discountable {
    private LocalDate orderDate;
    private double totalAmount;
    private Customer customer;
    private OrderItem[] orderItems;
    private String orderStatus;
    private double discountPercentage;

    public Order(Customer customer) {
        this.orderDate = LocalDate.now();
        this.totalAmount = 0.0;
        this.customer = customer;
        this.orderItems = new OrderItem[10];
        this.orderStatus = OrderStatus.PROCESSING;
        this.discountPercentage = 0.0;
    }

    @Override
    public void applyDiscount(double percentage) {
        if (percentage < 0 || percentage > 100) {
            System.out.println("Invalid discount percentage. Must be between 0 and 100");
        } else {
            discountPercentage = percentage;
            double discountAmount = totalAmount * (discountPercentage / 100);
            totalAmount -= discountAmount;
            System.out.println("Applied " + percentage + "% discount. New total amount: $" + totalAmount);
        }
    }

    @Override
    public void removeDiscount() {
        double discountAmount = totalAmount * (discountPercentage / 100);
        totalAmount += discountAmount;
        discountPercentage = 0;
        System.out.println("Discount removed. Total amount reset to original: $" + totalAmount);
    }

    @Override
    public double calculateTax() {
        double totalTax = 0.0;
        for (OrderItem item : orderItems) {
            if (item != null) {
                totalTax += item.getProduct().calculateTax() * item.getQuantity();
            }
        }
        return totalTax;
    }

    @Override
    public double getAverageTaxRate() {
        double totalTaxRate = 0.0;
        int itemCount = 0;

        for (OrderItem item : orderItems) {
            if (item != null) {
                totalTaxRate += item.getProduct().getAverageTaxRate();
                itemCount++;
            }
        }
        return itemCount > 0 ? totalTaxRate / itemCount : 0.0;
    }

    public void addItem(Product product, int quantity) {
        for (int i = 0; i < orderItems.length; i++) {
            if (orderItems[i] == null) {
                orderItems[i] = new OrderItem(product, quantity);
                calculateTotalAmount();
                return;
            }
        }
        System.out.println("Order is full");
    }

    public double calculateTotalAmount() {
        totalAmount = 0.0;
        for (OrderItem item : orderItems) {
            if (item != null) {
                totalAmount += item.getTotalPrice();
            }
        }
        return totalAmount;
    }

    public void updateOrderStatus(String newStatus) {
        this.orderStatus = newStatus;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderItem[] getOrderItems() {
        return orderItems;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    @Override
    public String toString() {
        StringBuilder orderInfo = new StringBuilder("Order Date: " + orderDate + '\n' +
                "Customer: " + customer.getEmail() + '\n' +
                "Total Amount: $" + totalAmount + '\n' +
                "Order Status: " + orderStatus + '\n' +
                "Items: \n");

        for (OrderItem item : orderItems) {
            if (item != null) {
                orderInfo.append(item).append('\n');
            }
        }

        return orderInfo.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDate, totalAmount, customer, orderItems, orderStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.totalAmount, totalAmount) == 0 &&
                orderDate.equals(order.orderDate) &&
                customer.equals(order.customer) &&
                Objects.equals(orderItems, order.orderItems) &&
                orderStatus.equals(order.orderStatus);
    }
}
