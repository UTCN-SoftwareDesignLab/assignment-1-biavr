package repositories.account;

import model.Account;
import repositories.EntityNotFoundException;

import java.util.List;

public interface AccountRepository {
    List<Account> findAll();

    Account findById(Long id) throws EntityNotFoundException;

    Account findByClientId(Long id) throws EntityNotFoundException;

    boolean save(Account client);

    boolean updateAccount(Long id, Account newAccount);

    boolean deleteAccount(Long idAccount, Long idClient);

    void removeAll();
}
