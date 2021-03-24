package services.account;

import launcher.ComponentFactory;
import model.Account;
import model.builder.AccountBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import repositories.EntityNotFoundException;
import repositories.account.AccountRepository;
import repositories.account.AccountRepositoryMySQL;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceMySQLTest {

    private AccountService accountService;


    @BeforeClass
    public void setup(){
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        accountService = new AccountServiceMySQL(componentFactory.getAccountRepository());
    }

    @Test
    void findAll() {
        assertEquals(0,accountService.findAll().size());

    }

    @Test
    void findById() throws Exception {
        accountService.findById(1L);
    }

    @Test
    void save() {
        assertTrue(accountService.save(new Account()));
    }

    @Test
    void deleteById() {
        accountService.save(new AccountBuilder()
        .setCreation_date(new Date())
        .setAmount(342L)
        .setType("Credit")
        .build());

        List<Account> accounts = accountService.findAll();

        Long idToDelete = accounts.get(0).getId();
        Long idClient = accounts.get(0).getClientId();

        assertTrue(accountService.deleteById(idToDelete,idClient));
    }
}