package product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private String title;
    private double price;
    private int stockQuantity;
    private Category category;
    private List<Review> reviews;

    public Product(String title, double price, int stockQuantity, Category category) {
        this.title = title;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.reviews = new ArrayList<>();
    }

    public void addReview(String reviewerName, String comment, int rating) {
        Review review = new Review(reviewerName, comment, rating);
        reviews.add(review);
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) {
            return 0.0;
        } else {
            double rating = 0.0;
            for (Review review : reviews) {
                rating += review.getRating();
            }
            return rating / reviews.size();
        }
    }

    public void updateStock(int quantity) {
        if (this.stockQuantity + quantity < 0) {
            System.out.println("You cannot reduce stock below zero");
        } else {
            this.stockQuantity += quantity;
            System.out.println("Stock updated successfully. New stock quantity: " + this.stockQuantity);
        }
    }

    public void updateStock() {
        this.stockQuantity = 0;
        System.out.println("Stock has been reset to zero.");
    }

    public double calculatePriceWithVAT() {
        double vatAmount = price * (category.getVat() / 100);
        return price + vatAmount;
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
            StringBuilder reviewInfo = new StringBuilder();
            reviewInfo.append("Title: ").append(title).append('\n')
                    .append("Rating: ").append(rating).append("/5").append('\n')
                    .append("Comment: ").append(comment).append('\n')
                    .append("Date: ").append(reviewDate);

            return reviewInfo.toString();
        }
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

    public void setReviews(List<Review> reviews) {
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

    public List<Review> getReviews() {
        return reviews;
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
            productInfo.append(review.toString()).append('\n').append('\n');
        }

        return productInfo.toString();
    }
}
