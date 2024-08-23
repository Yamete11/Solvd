package task2.service;

import task2.user.User;

public class NotificationService {
    public void notifyUser(User user) {
        System.out.println("Notifying user: " + user.getEmail());
    }
}
