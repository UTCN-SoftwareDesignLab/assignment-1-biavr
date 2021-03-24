package controller;

import model.Client;
import model.User;
import repositories.EntityNotFoundException;
import services.client.ClientService;
import services.employee.EmployeeService;
import view.AddClientView;
import view.EmployeeView;
import view.ViewClientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Bianca on 21/03/2021.
 */
public class EmployeeController {

    private final EmployeeView employeeView;
    private final ClientService clientService;

    public EmployeeController(EmployeeView employeeView, EmployeeService employeeService, ClientService clientService) {
        this.employeeView = employeeView;
        this.clientService = clientService;
        employeeView.setViewClientListener(new ViewButtonListener());
        employeeView.setAddClientListener(new AddButtonListener());
        employeeView.initializeClientTable(getAllClients());
    }

    private Object[][] getAllClients() {
        List<Client> clients = clientService.findAll();
        String[][] result = new String[clients.size()][];
        int i = 0;
        for (Client e: clients) {
            result[i][0] = e.getId().toString();
            result[i][1] = e.getName();
            result[i][2] = e.getCnp();
            result[i][3] = e.getAddress();
            result[i][4] = e.getAccount().getId().toString();
            result[i][5] = e.getAccount().getType();
            result[i][6] = e.getAccount().getAmount().toString();
            i++;
        }
        return result;
    }

    private class ViewButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Long id = employeeView.getSelectedClient();
            Client client = null;
            try {
                client = clientService.findById(id);
                ViewClientView viewClientView = new ViewClientView(client);
            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
        }
    }

    private class AddButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            AddClientView addClientView = new AddClientView();
        }
    }
}
