package br.com.ticotech.gbooks.java.view.shared;

import br.com.ticotech.gbooks.java.view.MainFrame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PasswordField {

    private final TextField passwordPlaceholder;
    private final JPasswordField passwordEntry;

    public JTextField getPasswordPlaceholder() {
        return passwordPlaceholder;
    }

    public JPasswordField getPasswordEntry() {
        return passwordEntry;
    }
    public PasswordField(String placeholder){
        passwordPlaceholder = new TextField(placeholder);
        passwordEntry = new JPasswordField();
        passwordEntry.setBorder(new BevelBorder(BevelBorder.RAISED));
        passwordEntry.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN, 22));
        passwordEntry.setVisible(false);
        LineBorder border = new LineBorder(new Color(184,207,229),1);
        passwordEntry.setBorder(border);

        passwordPlaceholder.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                passwordPlaceholder.setVisible(false);
                passwordEntry.setVisible(true);
                passwordEntry.requestFocus();
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        passwordEntry.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if(passwordEntry.getPassword().length == 0){
                    passwordEntry.setVisible(false);
                    passwordPlaceholder.setVisible(true);
                }
            }
        });
    }

    public void setBounds(int x, int y, int w, int h){
        passwordPlaceholder.setBounds(x,y,w,h);
        passwordEntry.setBounds(x,y,w,h);
    }

    public void setVisible(boolean isVisible){
        passwordPlaceholder.setVisible(isVisible);
        passwordEntry.setVisible(isVisible);
    }

    public String getText(){
        return String.valueOf(passwordEntry.getPassword());
    }

    public void reset(){
        passwordEntry.setText("");
        passwordEntry.setVisible(false);
        passwordPlaceholder.setVisible(true);
    }
}
