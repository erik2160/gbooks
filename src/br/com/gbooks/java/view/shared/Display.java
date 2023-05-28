package br.com.gbooks.java.view.shared;

import javax.swing.*;
import java.awt.*;

public class Display extends JTextField {
    private Color displayColor = Color.BLACK;
    private final String displayPlaceHolder;

    public Display(String placeHolder, Color newDisplayColor){
        this.displayColor = newDisplayColor;
        this.displayPlaceHolder = placeHolder;
        this.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN, 20));
        this.setText(placeHolder);
        this.setEnabled(false);
        this.setDisabledTextColor(Color.lightGray);
    }

    public Display(String placeHolder){
        this.displayPlaceHolder = placeHolder;
        this.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN, 20));
        this.setText(placeHolder);
        this.setEnabled(false);
        this.setDisabledTextColor(Color.lightGray);
    }

    @Override
    public void setText(String displayText){
        super.setText(displayText);
        this.setDisabledTextColor(displayColor);
    }

    public void reset(){
        setText(displayPlaceHolder);
        setDisabledTextColor(Color.lightGray);
    }

    public void setFontSize(int size){
     this.setFont( new Font(Constants.DEFAULT_FONT, Font.PLAIN, size));
    }

}
