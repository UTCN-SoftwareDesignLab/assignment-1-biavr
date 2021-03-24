package controller;

import services.account.AccountService;
import services.client.ClientService;
import view.ViewClientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Bianca on 21/03/2021.
 */
public class ViewClientController {
    private final ViewClientView viewClientView;
    private final ClientService clientService;
    private final AccountService accountService;

    public ViewClientController(ViewClientView viewClientView, ClientService clientService, AccountService accountService) {
        this.viewClientView = viewClientView;
        this.clientService = clientService;
        this.accountService = accountService;
        viewClientView.setDeleteBtnListener(new DeleteButtonListener());
        viewClientView.setEditButtonListener(new EditButtonListener());
        viewClientView.setDoneButtonListener(new DoneButtonListener());
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewClientView.setEditable(true);
            viewClientView.setDoneEditable(true);
        }
    }

    private class DoneButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
