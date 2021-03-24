package services.user;

import model.User;
import model.validation.Notification;

/**
 * Created by Bianca on 20/03/2021.
 */
public interface AuthenticationService {

    Notification<Boolean> register(String username, String password);

    Notification<User> login(String username, String password);

    boolean logout(User user);

}
