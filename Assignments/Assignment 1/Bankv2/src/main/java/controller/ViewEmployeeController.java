package controller;

import model.Action;
import services.employee.EmployeeService;
import services.report.ReportService;
import view.ViewEmployeeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewEmployeeController {

    private final ViewEmployeeView viewEmployeeView;
    private final EmployeeService employeeService;
    private final ReportService reportService;

    public ViewEmployeeController(ViewEmployeeView viewEmployeeView, EmployeeService employeeService, ReportService reportService) {
        this.viewEmployeeView = viewEmployeeView;
        this.employeeService = employeeService;
        this.reportService = reportService;
        viewEmployeeView.setEditButtonListener(new EditButtonListener());
        viewEmployeeView.setDoneButtonListener(new DoneButtonListener());
        viewEmployeeView.setDeleteBtnListener(new DeleteButtonListener());
        viewEmployeeView.setReportBtnListener(new ReportButtonListener());
    }

    private class EditButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            viewEmployeeView.setDoneVisible();
            viewEmployeeView.setUsernameEditable();
        }
    }

    public class DoneButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = viewEmployeeView.getUsername();
            Long id = viewEmployeeView.getId();
            //update statement from service
            employeeService.updateEmployee(username,id);
            viewEmployeeView.setVisible(false);
            viewEmployeeView.dispose();
        }
    }

    public class DeleteButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = viewEmployeeView.getUsername();
            employeeService.deleteEmployee(username);
            viewEmployeeView.setVisible(false);
            viewEmployeeView.dispose();
        }
    }

    private class ReportButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewEmployeeView.initializeEmployeeTable(getAllActions(viewEmployeeView.getId()));
            viewEmployeeView.setTableVisible(true);
        }
    }

    public Object[][] getAllActions(Long employeeId){
        List<Action> actions = reportService.getActions(employeeId);
        String[][] result = new String[actions.size()][];
        int i = 0;
        for (Action e: actions) {
            result[i][0] = e.getId().toString();
            result[i][1] = e.getType();
            result[i][2] = e.getDate().toString();
            i++;
        }
        return result;
    }
}
