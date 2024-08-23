package task2;

import task2.order.Order;
import task2.user.User;

public class ECom {
    private Order[] orders;
    private User[] users;

    public ECom(Order[] orders, User[] users) {
        this.orders = orders;
        this.users = users;
    }

    public ECom() {
        this.orders = new Order[10];
        this.users = new User[10];
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

    public void addOrder(Order order) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                orders[i] = order;
                return;
            }
        }
        System.out.println("Order list is full");
    }

    public void printAllOrders() {
        for (Order order : orders) {
            if (order != null) {
                System.out.println(order);
            }
        }
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


    public void printAllUsers() {
        for (User user : users) {
            if (user != null) {
                System.out.println(user);
            }
        }
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public Order[] getOrders() {
        return orders;
    }

    public User[] getUsers() {
        return users;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("E-Commerce System Overview\n");
        sb.append("==========================\n");

        sb.append("Users:\n");
        for (User user : users) {
            if (user != null) {
                sb.append(user).append("\n");
            }
        }

        sb.append("\nOrders:\n");
        for (Order order : orders) {
            if (order != null) {
                sb.append(order).append("\n");
            }
        }

        sb.append("\nTotal Revenue: $").append(calculateTotalRevenue()).append("\n");
        sb.append("==========================");

        return sb.toString();
    }
}
