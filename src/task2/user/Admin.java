package task2.user;

public class Admin extends User {
    private String role;
    private static final double DISCOUNT_RATE = 0.50;

    public Admin(String email, String password, String login, String role) {
        super(email, password, login);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static double getDiscountRate() {
        return DISCOUNT_RATE;
    }

    @Override
    public String toString() {
        StringBuilder adminInfo = new StringBuilder();
        adminInfo.append(super.toString()).append("\n")
                .append("Admin Details:\n")
                .append("  Role: ").append(role).append("\n")
                .append("  Discount Rate: ").append(DISCOUNT_RATE * 100).append("%");
        return adminInfo.toString();
    }
}
