package services.client;

import model.Client;
import model.validation.Notification;
import repositories.EntityNotFoundException;
import repositories.client.ClientRepository;

import java.util.List;

public class ClientServiceMySQL implements ClientService{

    private final ClientRepository clientRepository;

    public ClientServiceMySQL(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        return clientRepository.findById(id);
    }

    @Override
    public boolean save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public boolean deleteById(Long id) {
        return clientRepository.deleteClient(id);
    }

    @Override
    public boolean updateClient(Long id, Client newClient) {
        return clientRepository.updateClient(id, newClient);
    }

    @Override
    public Client findByName(String name) throws Exception {
        return clientRepository.findByName(name);
    }
}
