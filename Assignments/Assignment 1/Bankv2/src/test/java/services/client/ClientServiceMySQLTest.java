package services.client;

import launcher.ComponentFactory;
import model.Client;
import model.builder.ClientBuilder;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import repositories.EntityNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceMySQLTest {

    private ClientService clientService;

    @BeforeClass
    void setup(){
        ComponentFactory componentFactory = ComponentFactory.instance(true);
        clientService = new ClientServiceMySQL(componentFactory.getClientRepository());
    }

    @Test
    void findAll() {
        assertEquals(0,clientService.findAll().size());
    }

    @Test
    void save() throws EntityNotFoundException {
        clientService.save(new ClientBuilder()
                .setName("Someone")
                .setCnp("1254112101")
                .setAddress("somewhere")
                .build());

        List<Client> clients = clientService.findAll();

        Long idToFind = clients.get(0).getId();

        Client foundClient = clientService.findById(idToFind);
        assertEquals(idToFind,foundClient.getId());
    }

    @Test
    void deleteById() {
        clientService.save(new ClientBuilder()
        .setName("Someone")
        .setCnp("1254112101")
        .setAddress("somewhere")
        .build());

        List<Client> clients = clientService.findAll();

        Long idToDelete = clients.get(0).getId();

        assertTrue(clientService.deleteById(idToDelete));
    }
}