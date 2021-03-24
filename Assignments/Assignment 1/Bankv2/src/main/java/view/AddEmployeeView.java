package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created by Bianca on 22/03/2021.
 */
public class AddEmployeeView extends JFrame {

    private JTextField tfUsername;
    private JTextField tfPassword;
    private JButton btnRegister;

    private JLabel lUsername;
    private JLabel lPassword;

    public AddEmployeeView() throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(lUsername);
        add(tfUsername);
        add(lPassword);
        add(tfPassword);
        add(btnRegister);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields() {
        lPassword = new JLabel("Password");
        lUsername = new JLabel("Username");
        tfUsername = new JTextField();
        tfPassword = new JTextField();
        btnRegister = new JButton("Save");
    }

    public String getUsername() {
        return tfUsername.getText();
    }

    public String getPassword() {
        return tfPassword.getText();
    }

    public void setDoneButtonListener(ActionListener registerButtonListener) {
        btnRegister.addActionListener(registerButtonListener);
    }

    public void setVisible() {
        this.setVisible(true);
    }

}
