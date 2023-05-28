package Elements;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;

public class TextField extends JTextField {

    String fieldPlaceHolder;
    boolean isFocused;
    public TextField(String placeHolder) {
        fieldPlaceHolder = placeHolder;
        this.setText(placeHolder);
        this.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN, 20));
        this.setForeground(Color.lightGray);
        BevelBorder border = new BevelBorder(BevelBorder.RAISED);
        this.setBorder(border);

        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setForeground(Color.BLACK);
                isFocused = true;
                if (Objects.equals(getText(), placeHolder)) {
                    setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                isFocused = false;
                if (Objects.equals(getText(), "")) {
                    setText(placeHolder);
                    setForeground(Color.lightGray);
                }
            }
        });
    }

    public void reset(){
        if (!isFocused) {
            setText(fieldPlaceHolder);
            setForeground(Color.lightGray);
        }
        else{
            setText("");
        }
    }
    public void setFontSize(int size){
            this.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN, size));
        }
}
