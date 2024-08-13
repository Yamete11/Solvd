package task2.utils;

import task2.user.User;

public class UserUtils {

    public static boolean validateUser(User user) {
        if (user.getClass() != User.class) {
            throw new IllegalArgumentException("Only User instances are allowed.");
        }

        boolean isEmailValid = user.getEmail().contains("@") && user.getEmail().contains(".");

        boolean isPasswordValid = user.getPassword().length() >= 8;

        return isEmailValid && isPasswordValid;
    }
}
