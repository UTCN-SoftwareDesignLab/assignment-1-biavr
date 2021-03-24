package view;

import database.Constants;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created by Bianca on 22/03/2021.
 */
public class ViewEmployeeView extends JFrame{
    private final User user;
    private JButton editBtn;
    private JButton doneBtn;
    private JButton deleteBtn;
    private JButton btnReport;
    private JLabel idLabel;
    private JLabel usernameLabel;
    private JTextField tfId;
    private JTextField tfUsername;
    private JTable reportTable;

    public ViewEmployeeView(User user) throws HeadlessException{
        this.user = user;
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(idLabel);
        add(tfId);
        add(usernameLabel);
        add(tfUsername);
        add(editBtn);
        add(doneBtn);
        add(deleteBtn);
        add(btnReport);
        add(reportTable);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields() {
        idLabel = new JLabel("ID");
        tfId = new JTextField(user.getId().toString());
        tfId.setEditable(false);
        usernameLabel = new JLabel("Username");
        tfUsername = new JTextField(user.getUsername());
        tfUsername.setEditable(false);
        editBtn = new JButton("Edit");
        doneBtn = new JButton("Done");
        doneBtn.setVisible(false);
        deleteBtn = new JButton("Delete");
        btnReport = new JButton("Report");
        btnReport.setVisible(false);
    }

    public void setEditButtonListener(ActionListener editButtonListener){
        editBtn.addActionListener(editButtonListener);
    }

    public void setDoneButtonListener(ActionListener doneButtonListener){
        doneBtn.addActionListener(doneButtonListener);
    }

    public void setDeleteBtnListener(ActionListener deleteBtnListener){
        deleteBtn.addActionListener(deleteBtnListener);
    }

    public void setReportBtnListener(ActionListener reportBtnListener){
        btnReport.addActionListener(reportBtnListener);
    }

    public JTable initializeEmployeeTable(Object[][] data) {
        JTable table = new JTable(data, Constants.Columns.COLUMN_NAMES_REPORT);
        return table;
    }

    public void setDoneVisible(){
        editBtn.setVisible(true);
    }

    public String getUsername(){
        return tfUsername.getText();
    }

    public void setUsernameEditable(){
        tfUsername.setEditable(true);
    }

    public Long getId() {
        return Long.getLong(tfId.getText());
    }

    public void setTableVisible(boolean b) {
        reportTable.setVisible(true);
    }

}
