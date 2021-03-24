package controller;

import model.User;
import services.employee.EmployeeService;
import view.AddEmployeeView;
import view.AdminView;
import view.ViewEmployeeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminController {

    private final AdminView adminView;
    private final EmployeeService employeeService;

    public AdminController(AdminView adminView, EmployeeService employeeService) {
        this.adminView = adminView;
        this.employeeService = employeeService;
        adminView.setViewButtonListener(new ViewButtonListener());
        adminView.setAddButtonListener(new AddButtonListener());
        adminView.initializeEmployeeTable(getAllEmployees());
    }

    public Object[][] getAllEmployees(){
        List<User> employees = employeeService.getEmployees().getResult();
        String[][] result = new String[employees.size()][];
        int i = 0;
        for (User e: employees) {
            result[i][0] = e.getId().toString();
            result[i][1] = e.getUsername();
            i++;
        }
        return result;
    }

    private class ViewButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = adminView.getSelectedUser();
            User user = employeeService.getEmployee(username).getResult();
            ViewEmployeeView viewEmployeeView = new ViewEmployeeView(user);
        }
    }

    private class AddButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            AddEmployeeView addEmployeeView = new AddEmployeeView();
        }
    }
}
