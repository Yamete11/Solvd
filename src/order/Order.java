package order;

import user.Customer;
import product.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private LocalDate orderDate;
    private double totalAmount;
    private Customer customer;
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;

    public Order(Customer customer) {
        this.orderDate = LocalDate.now();
        this.totalAmount = 0.0;
        this.customer = customer;
        this.orderItems = new ArrayList<>();
        this.orderStatus = OrderStatus.PROCESSING;
    }

    public void addItem(Product product, int quantity) {
        OrderItem orderItem = new OrderItem(product, quantity);
        orderItems.add(orderItem);
        calculateTotalAmount();
    }

    private void calculateTotalAmount() {
        totalAmount = 0.0;
        for (OrderItem item : orderItems) {
            totalAmount += item.getTotalPrice();
        }
    }

    public void updateOrderStatus(OrderStatus newStatus) {
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public OrderStatus getOrderStatus() {
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
            orderInfo.append(item.toString()).append('\n');
        }

        return orderInfo.toString();
    }
}
