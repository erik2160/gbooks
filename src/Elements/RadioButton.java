package Elements;

import javax.swing.*;
import java.awt.*;

public class RadioButton extends JRadioButton {

    public RadioButton(String text){
        this.setText(text);
        this.setOpaque(false);
        this.setFocusPainted(false);
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Ubuntu", Font.PLAIN, 20));
    }

}
