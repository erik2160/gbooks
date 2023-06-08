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

    public Button(String text, Color backgroundColor, Color foregroundColor){
        this.setText(text);
        this.setFocusPainted(false);
        this.setBackground(backgroundColor);
        this.setForeground(foregroundColor);
        this.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN, 20));
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        this.setCursor(cur);
        BevelBorder bevelBorder = new BevelBorder(BevelBorder.RAISED);
        EtchedBorder etchedBorder = new EtchedBorder();
        CompoundBorder compoundBorder = new CompoundBorder(bevelBorder,etchedBorder);
        this.setBorder(compoundBorder);
    }
}
