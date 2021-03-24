package services.employee;

import model.User;
import model.validation.Notification;

import java.util.List;

/**
 * Created by Bianca on 22/03/2021.
 */
public interface EmployeeService {
    Notification<User> getEmployee(String username);

    Notification<List<User>> getEmployees();

    Notification<Boolean> addEmployee(String username, String password);

    Notification<Boolean> deleteEmployee(String username);

    Notification<Boolean> updateEmployee(String newUsername, Long id);
}
