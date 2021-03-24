package services.employee;

import model.Role;
import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import model.validation.UserValidator;
import repositories.security.RightsRolesRepository;
import repositories.user.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;

import static database.Constants.Roles.EMPLOYEE;

/**
 * Created by Bianca on 22/03/2021.
 */
public class EmployeeServiceMySQL implements EmployeeService{

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;

    public EmployeeServiceMySQL(UserRepository userRepository, RightsRolesRepository rightsRolesRepository) {
        this.userRepository = userRepository;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public Notification<User> getEmployee(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Notification<List<User>> getEmployees() {
        return userRepository.findAllEmployees();
    }

    @Override
    public Notification<Boolean> addEmployee(String username, String password) {
        Role employeeRole = rightsRolesRepository.findRoleByTitle(EMPLOYEE);
        User user = new UserBuilder()
                .setUsername(username)
                .setPassword(password)
                .setRoles(Collections.singletonList(employeeRole))
                .build();

        UserValidator userValidator = new UserValidator(user);
        boolean userValid = userValidator.validate();
        Notification<Boolean> userRegisterNotification = new Notification<>();

        if (!userValid) {
            userValidator.getErrors().forEach(userRegisterNotification::addError);
            userRegisterNotification.setResult(Boolean.FALSE);
        } else {
            user.setPassword(encodePassword(password));
            userRegisterNotification.setResult(userRepository.save(user));
        }
        return userRegisterNotification;
    }

    @Override
    public Notification<Boolean> deleteEmployee(String username) {
        return userRepository.deleteByUsername(username);
    }

    @Override
    public Notification<Boolean> updateEmployee(String newUsername, Long id) {
        return userRepository.updateUsername(newUsername,id);
    }

    private String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
