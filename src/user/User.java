package user;

import java.time.LocalDate;

public abstract class User {
    private LocalDate registrationDate;
    private String email;
    private String password;
    private String login;

    public User(String email, String password, String login) {
        this.registrationDate = LocalDate.now();
        this.email = email;
        this.password = password;
        this.login = login;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        StringBuilder userInfo = new StringBuilder();
        userInfo.append("User Information:\n")
                .append("  Registration Date: ").append(registrationDate).append("\n")
                .append("  Email: ").append(email).append("\n")
                .append("  Login: ").append(login);
        return userInfo.toString();
    }
}
