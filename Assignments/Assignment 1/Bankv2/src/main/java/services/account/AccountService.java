package services.account;

import model.Account;
import model.Client;
import repositories.EntityNotFoundException;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(Long id) throws EntityNotFoundException;

    boolean save(Account account);

    boolean deleteById(Long idAccount, Long idClient);

    boolean updateClient(Long id, Account newAccount);

}
