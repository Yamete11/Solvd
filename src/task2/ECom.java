package task2;

import task2.order.Order;
import task2.product.Category;
import task2.product.Product;
import task2.user.User;

public class ECom implements Searchable{
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
        System.out.println("ECom class loaded.");
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
    }

    public ECom() {
        this.orders = new Order[10];
        this.users = new User[10];
        this.categories = new Category[10];
        this.products = new Product[10];
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
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                return;
            }
        }
        System.out.println("User list is full");
    }

    public void addCategory(Category category) {
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] == null) {
                categories[i] = category;
                return;
            }
        }
        System.out.println("Category list is full");
    }

    public void addProduct(Product product) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = product;
                return;
            }
        }
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
