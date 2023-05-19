package Elements;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Button extends JButton {
    public Button(String text){
        this.setText(text);
        this.setFocusPainted(false);
        this.setFont(new Font(Constants.DEFAULTFONT, Font.PLAIN, 20));
        this.setBackground(new Color(234, 229, 223));
        this.setForeground(Color.black);
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        this.setCursor(cur);
        BevelBorder bevelBorder = new BevelBorder(BevelBorder.RAISED);
        EtchedBorder etchedBorder = new EtchedBorder();
        CompoundBorder compoundBorder = new CompoundBorder(bevelBorder,etchedBorder);
        this.setBorder(compoundBorder);
    }
}
