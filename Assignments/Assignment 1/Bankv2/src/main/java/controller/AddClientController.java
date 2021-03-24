package controller;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import services.account.AccountService;
import services.client.ClientService;
import view.AddClientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by Bianca on 22/03/2021.
 */
public class AddClientController {
    private final AddClientView addClientView;
    private final ClientService clientService;
    private final AccountService accountService;

    public AddClientController(AddClientView addClientView, ClientService clientService, AccountService accountService) {
        this.addClientView = addClientView;
        this.clientService = clientService;
        this.accountService = accountService;
        addClientView.setSaveButtonListener(new SaveButtonListener());
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = addClientView.getName();
            String address = addClientView.getAddress();
            String cnp = addClientView.getCnp();
            String type = addClientView.getAccountType();
            Long amount = addClientView.getAccountAmount();

            Client client = new ClientBuilder()
                    .setAddress(address)
                    .setCnp(cnp)
                    .setName(name)
                    .build();

            Account account = new AccountBuilder()
                    .setAmount(amount)
                    .setType(type)
                    .setCreation_date(new Date())
                    .setClientId(client.getId())
                    .build();

            clientService.save(client);
            try {
                Client clientExtracted = clientService.findByName(client.getName());
                account.setClientId(clientExtracted.getId());
                accountService.save(account);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            addClientView.setVisible(false);
            addClientView.dispose();

        }
    }
}
