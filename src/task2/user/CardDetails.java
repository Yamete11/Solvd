package task2.user;

import java.time.LocalDate;

public class CardDetails {
    private String cardNumber;
    private String cardholderName;
    private LocalDate expirationDate;
    private String cvv;

    public CardDetails(String cardNumber, String cardholderName, LocalDate expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        StringBuilder cardInfo = new StringBuilder();
        cardInfo.append("Cardholder: ").append(cardholderName)
                .append(", Card Number: **** **** **** ")
                .append(cardNumber.substring(cardNumber.length() - 4));
        return cardInfo.toString();
    }
}
