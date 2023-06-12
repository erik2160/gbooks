package br.com.ticotech.gbooks.java.view.shared;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Objects;

public class Button extends JButton {

    public Button(String imageConstant) {
        setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(imageConstant))));
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        this.setCursor(cur);
        setBorderPainted(false);
        setContentAreaFilled(false);
    }
}
