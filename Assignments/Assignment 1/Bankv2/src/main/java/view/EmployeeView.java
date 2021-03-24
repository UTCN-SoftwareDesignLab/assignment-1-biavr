package view;

import database.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

import static javax.swing.BoxLayout.Y_AXIS;

public class EmployeeView extends JFrame{

    private JTable clientsTable;
    private JButton addClient;
    private JButton viewClient;

    public EmployeeView(){
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setVisible();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields() {
        addClient = new JButton("Add client");
        viewClient = new JButton("View client");
    }

    public void setVisible() {
        this.setVisible(true);
    }

    public JTable initializeClientTable(Object data[][]){
        JTable table = new JTable(data, Constants.Columns.COLUMN_NAMES_CLIENTS);
        return table;
    }

    public Long getSelectedClient(){
        int selection = clientsTable.getSelectedRow();
        return Long.getLong((String)clientsTable.getModel().getValueAt(selection,0));
    }

    public void setViewClientListener(ActionListener viewClientListener){
        viewClient.addActionListener(viewClientListener);
    }

    public void setAddClientListener(ActionListener addClientListener){
        addClient.addActionListener(addClientListener);
    }

}
