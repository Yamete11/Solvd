package task2.order;

import task2.product.Product;
import task2.user.Customer;

import java.time.LocalDate;
import java.util.Objects;

public class Order {
    private LocalDate orderDate;
    private double totalAmount;
    private Customer customer;
    private OrderItem[] orderItems;
    private String orderStatus;

    public Order(Customer customer) {
        this.orderDate = LocalDate.now();
        this.totalAmount = 0.0;
        this.customer = customer;
        this.orderItems = new OrderItem[10];
        this.orderStatus = OrderStatus.PROCESSING;
    }

    public void addItem(Product product, int quantity) {
        for (int i = 0; i < orderItems.length; i++) {
            if (orderItems[i] == null) {
                orderItems[i] = new OrderItem(product, quantity);
                calculateTotalAmount();
                return;
            }
        }
        System.out.println("Cannot add more items. Order is full.");
    }

    private void calculateTotalAmount() {
        totalAmount = 0.0;
        for (OrderItem item : orderItems) {
            if (item != null) {
                totalAmount += item.getTotalPrice();
            }
        }
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
        StringBuilder orderInfo = new StringBuilder();
        orderInfo.append("Order Date: ").append(orderDate).append('\n')
                .append("Customer: ").append(customer.getEmail()).append('\n')
                .append("Total Amount: $").append(totalAmount).append('\n')
                .append("Order Status: ").append(orderStatus).append('\n')
                .append("Items: \n");

        for (OrderItem item : orderItems) {
            if (item != null) {
                orderInfo.append(item.toString()).append('\n');
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
                orderItems.equals(order.orderItems) &&
                orderStatus.equals(order.orderStatus);
    }
}
