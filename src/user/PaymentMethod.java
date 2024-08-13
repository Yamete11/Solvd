package user;

public enum PaymentMethod {
    CARD("Card Payment"),
    CASH("Cash Payment");

    private String title;

    PaymentMethod(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
