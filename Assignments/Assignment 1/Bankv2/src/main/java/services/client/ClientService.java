package services.client;

import model.Client;
import repositories.EntityNotFoundException;

import java.util.List;

public interface ClientService {
    List<Client> findAll();

    Client findById(Long id) throws EntityNotFoundException;

    boolean save(Client client);

    boolean deleteById(Long id);

    boolean updateClient(Long id, Client newClient);

    Client findByName(String name) throws Exception;

}
