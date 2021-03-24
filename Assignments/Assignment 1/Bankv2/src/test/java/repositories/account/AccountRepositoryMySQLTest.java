package repositories.account;

import launcher.ComponentFactory;
import model.Account;
import model.builder.AccountBuilder;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import repositories.EntityNotFoundException;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountRepositoryMySQLTest {

    private static AccountRepository accountRepository;

    @BeforeClass
    public static void setup() {
        ComponentFactory componentFactory = ComponentFactory.instance(true);

        accountRepository = componentFactory.getAccountRepository();
    }

    @Test
    void findAll() {
        List<Account> accounts = accountRepository.findAll();
        assertEquals(accounts.size(),0);
    }

    @Test
    void findById() {
        Account account = new AccountBuilder()
                .setType("Credit")
                .setAmount(12423L)
                .setCreation_date(new Date())
                .build();
        accountRepository.save(account);

        List<Account> accounts = accountRepository.findAll();
        assertEquals(accounts.size(),1);
    }

    @Test
    void findByClientId() throws EntityNotFoundException {
        Account account1 = new AccountBuilder()
                .setType("Credit")
                .setAmount(12423L)
                .setCreation_date(new Date())
                .setClientId(1L)
                .build();
        accountRepository.save(account1);

        Account account2 = new AccountBuilder()
                .setType("Credit")
                .setAmount(156L)
                .setCreation_date(new Date())
                .setClientId(2L)
                .build();
        accountRepository.save(account2);

        List<Account> accounts = accountRepository.findAll();
        Long idToFind = accounts.get(1).getId();
        Account foundAccount = accountRepository.findById(idToFind);
        assertEquals(foundAccount.getId(),idToFind);
    }

    @Test
    void save() {
        assertTrue(accountRepository.save(
                new AccountBuilder()
                        .setClientId(10L)
                        .setAmount(4561L)
                        .setCreation_date(new Date())
                        .setType("Debt")
                        .build()));
    }
}