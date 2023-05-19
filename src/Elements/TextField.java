package Elements;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;

public class TextField extends JTextField {

    public TextField(String placeHolder){
        this.setText(placeHolder);
        this.setFont(new Font(Constants.DEFAULTFONT, Font.PLAIN, 20));
        this.setForeground(Color.lightGray);
        //TEST
        BevelBorder border = new BevelBorder(BevelBorder.RAISED);
        this.setBorder(border);

        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                setForeground(Color.BLACK);
                if (Objects.equals(getText(), placeHolder)) {
                    setText(null);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(Objects.equals(getText(), "")){
                    setText(placeHolder);
                    setForeground(Color.lightGray);
                }
            }
        });
    }

    public void setFontSize(int size){
        this.setFont( new Font(Constants.DEFAULTFONT, Font.PLAIN, size));
    }
}
