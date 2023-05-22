package Elements;

import javax.swing.*;
import java.awt.*;

public class Display extends JTextField {
    private Color displayColor = Color.BLACK;

    public Display(String placeHolder, Color newDisplayColor){
        this.displayColor = newDisplayColor;
        this.setFont(new Font(Constants.DEFAULTFONT, Font.PLAIN, 20));
        this.setText(placeHolder);
        this.setEnabled(false);
        this.setDisabledTextColor(Color.lightGray);
    }

    public Display(String placeHolder){
        this.setFont(new Font(Constants.DEFAULTFONT, Font.PLAIN, 20));
        this.setText(placeHolder);
        this.setEnabled(false);
        this.setDisabledTextColor(Color.lightGray);
    }

    @Override
    public void setText(String displayText){
        super.setText(displayText);
        this.setDisabledTextColor(displayColor);
    }

    public void setFontSize(int size){
     this.setFont( new Font(Constants.DEFAULTFONT, Font.PLAIN, size));
    }

}
