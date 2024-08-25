package task2;

import task2.exception.*;
import task2.order.Order;
import task2.product.Category;
import task2.product.Product;
import task2.user.User;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class ECom implements Searchable{
    private static final Logger LOGGER = Logger.getLogger(ECom.class.getName());
    private static final String LOG_FILE = "ecom_log.txt";

    private Order[] orders;
    private User[] users;
    private Category[] categories;
    private Product[] products;
    private int orderCount = 0;
    private int userCount = 0;
    private int categoryCount = 0;
    private int productCount = 0;
    private static int EComInstance;

    static {
        logMessage("ECom class loaded");
        EComInstance = 0;
    }

    public ECom(Order[] orders, User[] users, Category[] categories, Product[] products) {
        this.orders = orders;
        this.users = users;
        this.categories = categories;
        this.products = products;
        this.orderCount = orders.length;
        this.userCount = users.length;
        this.categoryCount = categories.length;
        this.productCount = products.length;
        EComInstance++;
        logMessage("ECom class instance created");
    }

    public ECom() {
        this.orders = new Order[10];
        this.users = new User[10];
        this.categories = new Category[10];
        this.products = new Product[10];
        EComInstance++;
        logMessage("ECom class instance created");
    }

    private static void logMessage(String message) {
        LOGGER.info(message);
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logEntry = timeStamp + " - " + message;

        try (FileWriter fileWriter = new FileWriter(LOG_FILE, true)) {
            fileWriter.write(logEntry + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write log to file: " + e.getMessage(), e);
        }
    }



    public void addOrder(Order order) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                orders[i] = order;
                return;
            }
        }
        System.out.println("Order list is full");
    }

    public void addUser(User user) {
        if (user == null) {
            throw new InvalidUserException("User cannot be null ");
        }
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                return;
            }
        }
        logMessage("Failed to add user - User list is full");
        System.out.println("User list is full");
    }

    public void addCategory(Category category) {
        if (category == null || category.getTitle() == null || category.getTitle().isEmpty()) {
            throw new InvalidCategoryException("Category is invalid or has no title");
        }
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] == null) {
                categories[i] = category;
                return;
            }
        }
        logMessage("Failed to add category - Category list is full");
        System.out.println("Category list is full");
    }

    public void addProduct(Product product) throws DuplicateProductException {
        if (product.getStockQuantity() <= 0) {
            throw new ProductOutOfStockException("Product is out of stock");
        }
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null && products[i].getTitle().equals(product.getTitle())) {
                throw new DuplicateProductException("Product with title " + product.getTitle() + " already exists");
            }
            if (products[i] == null) {
                products[i] = product;
                productCount++;
                return;
            }
        }
        logMessage("Failed to add product - Product list is full");
        System.out.println("Product list is full");
    }

    @Override
    public Product[] searchProductByName(String name) {
        Product[] result = new Product[productCount];
        int count = 0;
        for (int i = 0; i < productCount; i++) {
            if (products[i] != null && products[i].getTitle().equalsIgnoreCase(name)) {
                result[count++] = products[i];
            }
        }
        logMessage("Search for product by name: " + name + " found " + count + " results");
        return trimProductArray(result, count);
    }

    @Override
    public Product[] filterProductByCategory(String category) {
        Product[] result = new Product[productCount];
        int count = 0;
        for (int i = 0; i < productCount; i++) {
            if (products[i] != null && products[i].getCategory().getTitle().equalsIgnoreCase(category)) {
                result[count++] = products[i];
            }
        }
        return trimProductArray(result, count);
    }

    private Product[] trimProductArray(Product[] array, int count) {
        Product[] trimmedArray = new Product[count];
        for (int i = 0; i < count; i++) {
            trimmedArray[i] = array[i];
        }
        return trimmedArray;
    }



    public void printAllOrders() {
        boolean ordersExist = false;
        for (Order order : orders) {
            if (order != null) {
                System.out.println(order);
                System.out.println();
                ordersExist = true;
            }
        }
        if (!ordersExist) {
            System.out.println("The list of orders is empty");
        }
    }

    public void printAllUsers() {
        boolean usersExist = false;
        for (User user : users) {
            if (user != null) {
                System.out.println(user);
                System.out.println();
                usersExist = true;
            }
        }
        if (!usersExist) {
            System.out.println("The list of users is empty");
        }
    }

    public void printAllCategories() {
        boolean categoriesExist = false;
        for (Category category : categories) {
            if (category != null) {
                System.out.println(category);
                System.out.println();
                categoriesExist = true;
            }
        }
        if (!categoriesExist) {
            System.out.println("The list of categories is empty");
        }
    }

    public void printAllProducts() {
        boolean productsExist = false;
        for (Product product : products) {
            if (product != null) {
                System.out.println(product);
                System.out.println();
                productsExist = true;
            }
        }
        if (!productsExist) {
            System.out.println("The list of products is empty");
        }
    }

    public double calculateTotalRevenue() {
        double totalRevenue = 0.0;
        for (Order order : orders) {
            if (order != null) {
                totalRevenue += order.getTotalAmount();
            }
        }
        return totalRevenue;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public void setCategoryCount(int categoryCount) {
        this.categoryCount = categoryCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public static void setEComInstance(int EComInstance) {
        ECom.EComInstance = EComInstance;
    }

    public Order[] getOrders() {
        return orders;
    }

    public User[] getUsers() {
        return users;
    }

    public Category[] getCategories() {
        return categories;
    }

    public Product[] getProducts() {
        return products;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public int getUserCount() {
        return userCount;
    }

    public int getCategoryCount() {
        return categoryCount;
    }

    public int getProductCount() {
        return productCount;
    }

    public static int getEComInstance() {
        return EComInstance;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("E-Commerce System Overview\n");
        sb.append("==========================\n");

        sb.append("Users:\n");
        boolean usersExist = false;
        for (User user : users) {
            if (user != null) {
                sb.append(user).append("\n");
                usersExist = true;
            }
        }
        if (!usersExist) {
            sb.append("The list of users is empty\n");
        }

        sb.append("\nOrders:\n");
        boolean ordersExist = false;
        for (Order order : orders) {
            if (order != null) {
                sb.append(order).append("\n");
                ordersExist = true;
            }
        }
        if (!ordersExist) {
            sb.append("The list of orders is empty\n");
        }

        sb.append("\nCategories:\n");
        boolean categoriesExist = false;
        for (Category category : categories) {
            if (category != null) {
                sb.append(category).append("\n");
                categoriesExist = true;
            }
        }
        if (!categoriesExist) {
            sb.append("The list of categories is empty\n");
        }

        sb.append("\nProducts:\n");
        boolean productsExist = false;
        for (Product product : products) {
            if (product != null) {
                sb.append(product).append("\n");
                productsExist = true;
            }
        }
        if (!productsExist) {
            sb.append("The list of products is empty\n");
        }

        sb.append("\nTotal Revenue: $").append(calculateTotalRevenue()).append("\n");
        sb.append("==========================");

        return sb.toString();
    }
}
