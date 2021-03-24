package controller;

import model.validation.Notification;
import services.employee.EmployeeService;
import view.AddEmployeeView;
import view.EmployeeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Bianca on 22/03/2021.
 */

public class AddEmployeeController {
    private final AddEmployeeView employeeView;
    private final EmployeeService employeeService;

    public AddEmployeeController(AddEmployeeView employeeView, EmployeeService employeeService) {
        this.employeeView = employeeView;
        this.employeeService = employeeService;
        employeeView.setDoneButtonListener(new RegisterButtonListener());
    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = employeeView.getUsername();
            String password = employeeView.getPassword();

            Notification<Boolean> registerNotification = employeeService.addEmployee(username,password);

            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(employeeView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Registration not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Registration successful!");
                    employeeView.setVisible(false);
                    employeeView.dispose();
                }
            }
        }
    }
}
