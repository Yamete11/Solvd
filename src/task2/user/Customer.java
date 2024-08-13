package task2.user;

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
}
