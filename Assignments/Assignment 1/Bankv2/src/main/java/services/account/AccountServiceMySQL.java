package services.account;

import model.Account;
import repositories.EntityNotFoundException;
import repositories.account.AccountRepository;

import java.util.List;

/**
 * Created by Bianca on 22/03/2021.
 */

public class AccountServiceMySQL implements AccountService{

    private final AccountRepository accountRepository;

    public AccountServiceMySQL(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        return accountRepository.findById(id);
    }

    @Override
    public boolean save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public boolean deleteById(Long idAccount, Long idClient) {
        return accountRepository.deleteAccount(idAccount, idClient);
    }

    @Override
    public boolean updateClient(Long id, Account newAccount) {
        return accountRepository.updateAccount(id,newAccount);
    }
}
