package task2.user;

import java.util.Objects;
import java.util.Scanner;

public class Customer extends User {
    private Address address;
    private String paymentMethod;
    private CardDetails cardDetails;

    public Customer(String email, String password, String login, Address address) {
        super(email, password, login);
        this.address = address;
        this.paymentMethod = PaymentMethod.CASH;
        this.cardDetails = null;
    }

    public Customer(String email, String password, String login, Address address, CardDetails cardDetails) {
        super(email, password, login);
        this.address = address;
        this.paymentMethod = PaymentMethod.CARD;
        this.cardDetails = cardDetails;
    }
    @Override
    public String getAccountType() {
        return "Customer Account";
    }

    @Override
    public void resetPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your email address: ");
        String inputEmail = scanner.nextLine();

        if (inputEmail.equals(getEmail())) {
            System.out.print("Enter your new password: ");
            String newPassword = scanner.nextLine();
            setPassword(newPassword);
            System.out.println("Password has been successfully reset.");
        } else {
            System.out.println("Email address is incorrect. Cannot reset password.");
        }
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public CardDetails getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(CardDetails cardDetails) {
        this.cardDetails = cardDetails;
    }

    @Override
    public String toString() {
        StringBuilder customerInfo = new StringBuilder(super.toString())
                .append("\nCustomer Details:\n")
                .append("  Address: ").append(address).append("\n")
                .append("  Payment Method: ").append(paymentMethod);

        if (PaymentMethod.CARD.equalsIgnoreCase(paymentMethod) && cardDetails != null) {
            customerInfo.append("\n  Card Details: ").append(cardDetails);
        }

        return customerInfo.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), address, paymentMethod);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(address, customer.address) &&
                Objects.equals(paymentMethod, customer.paymentMethod);
    }
}
