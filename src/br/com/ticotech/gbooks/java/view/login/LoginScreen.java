package br.com.ticotech.gbooks.java.view.login;

import br.com.ticotech.gbooks.java.controllers.UserController;
import br.com.ticotech.gbooks.java.entities.User;
import br.com.ticotech.gbooks.java.view.MainFrame;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.Constants;
import br.com.ticotech.gbooks.java.view.shared.PasswordField;
import br.com.ticotech.gbooks.java.view.shared.TextField;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class LoginScreen{

    private final UserController userController;
    private final MainFrame mainFrame;
    private final JPanel loginPanel;
    private final TextField idField;
    private final JLabel invalidUserLabel;
    private final JLabel invalidPassLabel;
    private final PasswordField passwordField;

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public LoginScreen(UserController userController, MainFrame mainFrame) {
        this.userController = userController;
        this.mainFrame = mainFrame;
        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBounds(0,0,1920,880);
        loginPanel.setBackground(Constants.BABY_BLUE);

        invalidUserLabel = new JLabel();
        invalidUserLabel.setBounds(880, 650, 171, 25);
        invalidUserLabel.setOpaque(false);
        invalidUserLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.INVALID_USER))));
        loginPanel.add(invalidUserLabel);

        invalidPassLabel = new JLabel();
        invalidPassLabel.setBounds(840, 650, 245, 25);
        invalidPassLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.INVALID_PASSWORD))));
        invalidPassLabel.setVisible(false);
        loginPanel.add(invalidPassLabel);

        JLabel titleLabel = new JLabel();
        titleLabel.setBounds(790, 90, 340, 339);
        titleLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.LOGIN_ICON))));
        loginPanel.add(titleLabel);

        TextField ghostField = new TextField("");
        loginPanel.add(ghostField);

        idField = new TextField("USER ID");
        idField.setBackground(Color.WHITE);
        idField.setBounds(713, 480, 499, 63);
        loginPanel.add(idField);

        passwordField = new PasswordField("PASSWORD");
        passwordField.setBounds(713, 573, 499, 63);
        passwordField.getPasswordEntry().addActionListener(e -> doLogin());
        loginPanel.add(passwordField.getPasswordPlaceholder());
        loginPanel.add(passwordField.getPasswordEntry());

        Button enterButton = new Button(Constants.LOGIN_BUTTON);
        enterButton.setBounds(866, 685, 197, 74);
        enterButton.addActionListener(e -> doLogin());
        loginPanel.add(enterButton);
    }

    private void doLogin(){
        String enteredId = idField.getText();
        String enteredPassword = passwordField.getText();
        int verifyReturn = userController.verify(enteredId, enteredPassword);
        if (verifyReturn==1) {
            mainFrame.doLogin(userController.getAccessType());
        } else if (verifyReturn==0) {
            invalidUserLabel.setVisible(false);
            invalidPassLabel.setVisible(true);
        } else if (verifyReturn==-1) {
            invalidPassLabel.setVisible(false);
            invalidUserLabel.setVisible(true);
        }
    }

    public void setVisible(boolean isVisible) {
        idField.reset();
        passwordField.reset();
        invalidUserLabel.setVisible(false);
        invalidPassLabel.setVisible(false);
        loginPanel.setVisible(isVisible);
    }
}
