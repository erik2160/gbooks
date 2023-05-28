package br.com.gbooks.java.view.shared;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Button extends JButton {
    public Button(String text){
        this.setText(text);
        this.setFocusPainted(false);
        this.setBackground(Constants.YELLOWED_WHITE);
        this.setForeground(Color.black);
        this.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN, 20));
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        this.setCursor(cur);
        BevelBorder bevelBorder = new BevelBorder(BevelBorder.RAISED);
        EtchedBorder etchedBorder = new EtchedBorder();
        CompoundBorder compoundBorder = new CompoundBorder(bevelBorder,etchedBorder);
        this.setBorder(compoundBorder);
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
