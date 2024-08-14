package task2.product;

import java.time.LocalDate;
import java.util.Objects;

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
        String productInfo = "Title: " + title + '\n' +
                "Price: " + price + " (without VAT)" + '\n' +
                "Price with VAT: " + calculatePriceWithVAT() + '\n' +
                "Stock Quantity: " + stockQuantity + '\n' +
                category.toString() + '\n' +
                "Average Rating: " + getAverageRating() + "/5" + '\n' +
                "Reviews:\n";

        for (Review review : reviews) {
            if (review != null) {
                productInfo += review.toString() + '\n' + '\n';
            }
        }

        return productInfo;
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

    @Override
    public int hashCode() {
        return Objects.hash(title, price, stockQuantity, category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                stockQuantity == product.stockQuantity &&
                title.equals(product.title) &&
                category.equals(product.category);
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

        @Override
        public int hashCode() {
            return Objects.hash(title, comment, rating, reviewDate);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Review review = (Review) o;
            return rating == review.rating &&
                    title.equals(review.title) &&
                    comment.equals(review.comment) &&
                    reviewDate.equals(review.reviewDate);
        }
    }
}
