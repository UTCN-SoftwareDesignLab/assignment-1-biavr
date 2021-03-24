package repositories.client;

import model.Client;
import repositories.EntityNotFoundException;

import java.util.List;

public interface ClientRepository {
    List<Client> findAll();

    Client findById(Long id) throws EntityNotFoundException;

    boolean save(Client client);

    boolean deleteClient(Long id);

    boolean updateClient(Long id, Client newClient);

    void removeAll();

    public Client findByName(String name) throws Exception;
}
