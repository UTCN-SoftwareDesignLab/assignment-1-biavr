package view;

import database.Constants;
import model.User;

import javax.swing.*;

import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class AdminView extends JFrame {

    private JTable employeeTable;
    private JButton addEmployee;
    private JButton viewEmployee;

    public AdminView(){
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setVisible(true);
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields() {
        addEmployee = new JButton("Add employee");
        viewEmployee = new JButton("View employee");
    }

    public JTable initializeEmployeeTable(Object[][] data) {
        JTable table = new JTable(data, Constants.Columns.COLUMN_NAMES_EMPLOYEES);
        return table;
    }

    public void setViewButtonListener(ActionListener viewButtonListener) {
        viewEmployee.addActionListener(viewButtonListener);
    }

    public void setAddButtonListener(ActionListener addButtonListener) {
        addEmployee.addActionListener(addButtonListener);
    }

    public String getSelectedUser(){
        int selection = employeeTable.getSelectedRow();
        return (String)employeeTable.getModel().getValueAt(selection,1);
    }
}
