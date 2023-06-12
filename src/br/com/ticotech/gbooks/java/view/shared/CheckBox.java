package br.com.ticotech.gbooks.java.view.shared;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CheckBox extends JButton {
    private boolean checked;
    private final String imageChecked;
    private final String imageNotChecked;
    public CheckBox(String checked, String notChecked){
        this.imageChecked = checked;
        this.imageNotChecked = notChecked;

        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        this.setCursor(cur);
        this.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(notChecked))));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setBorder(null);
    }

    public void alterCheck() {
        if(!checked){
            checked=true;
            this.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(imageChecked))));
        }
        else{
            checked=false;
            this.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(imageNotChecked))));
        }
    }
}
