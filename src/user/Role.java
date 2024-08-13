package user;

public enum Role {
    SYSTEM_ADMIN("System Administrator"),
    MANAGER("Manager");

    private String title;

    Role(String title) {
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
