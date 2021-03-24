package view;

import model.Client;

import javax.swing.*;

import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created by Bianca on 22/03/2021.
 */
public class ViewClientView extends JFrame{
    private final Client client;
    private JTextField tfName;
    private JTextField tfCnp;
    private JTextField tfAddress;
    private JTextField tfAccountType;
    private JTextField tfAccountAmount;

    private JLabel lName;
    private JLabel lCnp;
    private JLabel lAddress;
    private JLabel lAccountType;
    private JLabel lAccountAmount;

    private JButton btnEdit;
    private JButton btnDone;
    private JButton btnDelete;

    public ViewClientView(Client client) {
        this.client = client;
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(lName);
        add(tfName);
        add(lAddress);
        add(tfAddress);
        add(lCnp);
        add(tfCnp);
        add(lAccountType);
        add(tfAccountType);
        add(lAccountAmount);
        add(tfAccountAmount);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields() {
        lAccountAmount = new JLabel("Amount");
        lAccountType = new JLabel("Type");
        lName = new JLabel("Name");
        lCnp = new JLabel("CNP");
        lAddress = new JLabel("Address");

        tfAccountAmount = new JTextField();
        tfAccountType = new JTextField();
        tfName = new JTextField();
        tfCnp = new JTextField();
        tfAddress = new JTextField();
        setEditable(false);

        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnDone = new JButton("Done");
        btnDone.setVisible(false);
    }

    public void setEditButtonListener(ActionListener editButtonListener){
        btnEdit.addActionListener(editButtonListener);
    }

    public void setDoneButtonListener(ActionListener doneButtonListener){
        btnDone.addActionListener(doneButtonListener);
    }

    public void setDeleteBtnListener(ActionListener deleteBtnListener){
        btnDelete.addActionListener(deleteBtnListener);
    }

    public void setEditable(boolean b){
        tfAccountAmount.setEditable(b);
        tfAccountType.setEditable(b);
        tfName.setEditable(b);
        tfCnp.setEditable(b);
        tfAddress.setEditable(b);

    }

    public void setDoneEditable(boolean b){
        btnDone.setVisible(b);
    }

}
