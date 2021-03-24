package repositories.account;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import repositories.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bianca on 21/03/2021.
 */

public class AccountRepositoryMySQL implements AccountRepository{

    private final Connection connection;

    public AccountRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Account current = getAccountFromResultSet(rs);
                if (current.getId() == id){
                    return current;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Account findByClientId(Long id) throws EntityNotFoundException {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Account current = getAccountFromResultSet(rs);
                if (current.getClientId() == id){
                    return current;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Account account) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, account.getType());
            insertUserStatement.setLong(2, account.getAmount());
            insertUserStatement.setDate(3, (Date) account.getCreation_date());
            insertUserStatement.setLong(4, account.getClientId());

            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            account.setId(userId);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAccount(Long id, Account newAccount) {
        try {
            Statement statement = connection.createStatement();
            String sql = "UPDATE account set `type` = \'" + newAccount.getType() + "\', amount = " + newAccount.getAmount().toString() + " where `id` = " + newAccount.getId().toString();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteAccount(Long idAccount, Long idClient) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from `account` where id = " + idAccount.toString();
            statement.executeUpdate(sql);

            sql = "DELETE from `client` where id = " + idClient.toString();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from `account` where id >= 0";
            statement.executeUpdate(sql);

            sql = "DELETE from `client` where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setId(rs.getLong("id"))
                .setType(rs.getString("type"))
                .setAmount(rs.getLong("amount"))
                .setCreation_date(new Date(rs.getDate("creation_date").getTime()))
                .setClientId(rs.getLong("client_id"))
                .build();
    }
}
