package task2.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String title;
    private double price;
    private int stockQuantity;
    private Category category;
    private Review[] reviews;

    public Product(String title, double price, int stockQuantity, Category category) {
        this.title = title;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.reviews = new Review[10];
    }

    public Product(String title, double price, Category category) {
        this.title = title;
        this.price = price;
        this.stockQuantity = 0;
        this.category = category;
        this.reviews = new Review[10];
    }

    public void addReview(String reviewerName, String comment, int rating) {
        for (int i = 0; i < reviews.length; i++) {
            if (reviews[i] == null) {
                reviews[i] = new Review(reviewerName, comment, rating);
                return;
            }
        }
        System.out.println("Review limit reached. Cannot add more reviews.");
    }

    public double getAverageRating() {
        double totalRating = 0.0;
        int count = 0;
        for (Review review : reviews) {
            if (review != null) {
                totalRating += review.getRating();
                count++;
            }
        }
        return count == 0 ? 0.0 : totalRating / count;
    }

    public void updateStock(int quantity) {
        if (this.stockQuantity + quantity < 0) {
            System.out.println("You cannot reduce stock below zero");
        } else {
            this.stockQuantity += quantity;
            System.out.println("Stock updated successfully. New stock quantity: " + this.stockQuantity);
        }
    }

    public double calculatePriceWithVAT() {
        double vatAmount = price * (category.getVat() / 100);
        return price + vatAmount;
    }

    @Override
    public String toString() {
        StringBuilder productInfo = new StringBuilder();
        productInfo.append("Title: ").append(title).append('\n')
                .append("Price: ").append(price).append(" (without VAT)").append('\n')
                .append("Price with VAT: ").append(calculatePriceWithVAT()).append('\n')
                .append("Stock Quantity: ").append(stockQuantity).append('\n')
                .append(category.toString()).append('\n')
                .append("Average Rating: ").append(getAverageRating()).append("/5").append('\n')
                .append("Reviews:\n");

        for (Review review : reviews) {
            if (review != null) {
                productInfo.append(review).append('\n').append('\n');
            }
        }

        return productInfo.toString();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setReviews(Review[] reviews) {
        this.reviews = reviews;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public Review[] getReviews() {
        return reviews;
    }

    public class Review {
        private String title;
        private String comment;
        private int rating;
        private final LocalDate reviewDate;

        public Review(String title, String comment, int rating) {
            this.title = title;
            this.comment = comment;
            this.rating = rating;
            this.reviewDate = LocalDate.now();
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public LocalDate getReviewDate() {
            return reviewDate;
        }

        @Override
        public String toString() {
            return "Title: " + title + '\n' +
                    "Rating: " + rating + "/5" + '\n' +
                    "Comment: " + comment + '\n' +
                    "Date: " + reviewDate;
        }
    }
}