package repositories.user;

import model.User;
import model.validation.Notification;

import java.util.List;

/**
 * Created by Bianca on 20/03/2021.
 */
public interface UserRepository {

    List<User> findAll();

    Notification<List<User>> findAllEmployees();

    Notification<User> findByUsername(String username);

    Notification<User> findByUsernameAndPassword(String username, String password);

    boolean save(User user);

    Notification<Boolean> deleteByUsername(String username);

    Notification<Boolean> updateUsername(String newUsername, Long id);

    void removeAll();

}
