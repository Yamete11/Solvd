package task2.user;

public interface Payable {

    boolean processPayment(double amount);
    boolean refundPayment(double amount);
    String generateReceipt();
}
