package view;

import javax.swing.*;

import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created by Bianca on 22/03/2021.
 */
public class AddClientView extends JFrame{
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

    private JButton btnSave;

    public AddClientView(){
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(lName);
        add(tfName);
        add(lCnp);
        add(tfCnp);
        add(lAddress);
        add(tfAddress);
        add(lAccountType);
        add(tfAccountAmount);
        add(btnSave);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields() {
        tfAccountAmount = new JTextField();
        tfAddress = new JTextField();
        tfCnp = new JTextField();
        tfAccountType = new JTextField();
        tfName = new JTextField();
        lAccountAmount = new JLabel("Amount");
        lAccountType = new JLabel("Type");
        lName = new JLabel("Name");
        lCnp = new JLabel("CNP");
        lAddress = new JLabel("Address");
        btnSave = new JButton("Save");
    }

    public String getAccountName(){
        return tfName.getText();
    }

    public String getCnp(){
        return tfCnp.getText();
    }

    public String getAddress(){
        return tfAddress.getText();
    }

    public String getAccountType(){
        return tfAccountType.getText();
    }

    public Long getAccountAmount(){
        return Long.getLong(tfAccountAmount.getText().trim());
    }

    public void setSaveButtonListener(ActionListener saveButtonListener){
        btnSave.addActionListener(saveButtonListener);
    }
}
