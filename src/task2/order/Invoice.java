package task2.order;

import task2.user.Address;

import java.time.LocalDate;
import java.util.Objects;

public class Invoice {
    private String invoiceNumber;
    private Order order;
    private Address billingAddress;
    private LocalDate issueDate;
    private double totalAmount;

    public Invoice(Order order) {
        this.invoiceNumber = generateInvoiceNumber();
        this.order = order;
        this.billingAddress = order.getCustomer().getAddress();
        this.issueDate = LocalDate.now();
        this.totalAmount = calculateTotalAmount();
    }

    private String generateInvoiceNumber() {
        return "INV-" + System.currentTimeMillis();
    }

    private double calculateTotalAmount() {
        return order.getTotalAmount();
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public Order getOrder() {
        return order;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        StringBuilder invoiceDetails = new StringBuilder();
        invoiceDetails.append("Invoice Number: ").append(invoiceNumber).append('\n')
                .append("Issue Date: ").append(issueDate).append('\n')
                .append("Billing Address: ").append(billingAddress).append('\n')
                .append("Order Details: \n").append(order.toString()).append('\n')
                .append("Total Amount Due: $").append(totalAmount).append('\n');
        return invoiceDetails.toString();
    }

    public void printInvoice() {
        System.out.println(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceNumber, order, billingAddress, issueDate, totalAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Double.compare(invoice.totalAmount, totalAmount) == 0 &&
                invoiceNumber.equals(invoice.invoiceNumber) &&
                order.equals(invoice.order) &&
                billingAddress.equals(invoice.billingAddress) &&
                issueDate.equals(invoice.issueDate);
    }
}
