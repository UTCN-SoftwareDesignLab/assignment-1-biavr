package repositories.user;

import database.Constants;
import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import repositories.security.RightsRolesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.USER;

/**
 * Created by Bianca on 20/03/2021.
 */
public class UserRepositoryMySQL implements UserRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;


    public UserRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from user";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                users.add(getUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public Notification<List<User>> findAllEmployees() {
        Notification<List<User>> findAllEmployeesNotification = new Notification<>();
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from user, user_roles, roles where `roles`.`role_id`=`user_roles`.`role_id` and `role`.`role` = \'" + Constants.Roles.EMPLOYEE + "\'";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                users.add(getUserFromResultSet(rs));
            }
            findAllEmployeesNotification.setResult(users);
            return findAllEmployeesNotification;
        } catch (SQLException e) {
            findAllEmployeesNotification.addError("An error was encountered");
            return findAllEmployeesNotification;
        }
    }

    @Override
    public Notification<User> findByUsername(String username) {
        Notification<User> findByUsernameNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + USER + "` where `username`=\'" + username + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                User user = new UserBuilder()
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                        .build();
                findByUsernameNotification.setResult(user);
                return findByUsernameNotification;
            } else {
                findByUsernameNotification.addError("Invalid email or password!");
                return findByUsernameNotification;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            findByUsernameNotification.addError("Something is wrong with the Database");
        }
        return findByUsernameNotification;
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                User user = new UserBuilder()
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                        .build();
                findByUsernameAndPasswordNotification.setResult(user);
                return findByUsernameAndPasswordNotification;
            } else {
                findByUsernameAndPasswordNotification.addError("Invalid email or password!");
                return findByUsernameAndPasswordNotification;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            findByUsernameAndPasswordNotification.addError("Something is wrong with the Database");
        }
        return findByUsernameAndPasswordNotification;
    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            user.setId(userId);

            rightsRolesRepository.addRolesToUser(user, user.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Notification<Boolean> deleteByUsername(String username) {
        Notification<Boolean> deleteNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where `username` = \'" + username + "\'";
            statement.executeUpdate(sql);
            deleteNotification.setResult(true);
        } catch (SQLException e) {
            deleteNotification.setResult(false);
            return deleteNotification;
        }
        return deleteNotification;
    }

    @Override
    public Notification<Boolean> updateUsername(String newUsername, Long id) {
        Notification<Boolean> updateNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "UPDATE user set `username` = \'" + newUsername + "\'" + " where `id` = " + id ;
            statement.executeUpdate(sql);
            updateNotification.setResult(true);
        } catch (SQLException e) {
            updateNotification.setResult(false);
            return updateNotification;
        }
        return updateNotification;
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        return new UserBuilder()
                .setId(rs.getLong("id"))
                .setUsername(rs.getString("username"))
                .setPassword(rs.getString("password"))
                .build();
    }

}
