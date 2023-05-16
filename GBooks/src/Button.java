import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    public Button(String text){
        this.setText(text);
        this.setFocusPainted(false);
        this.setFont(new Font("Ubuntu", Font.PLAIN, 20));
        this.setBackground(new Color(234, 229, 223));
        this.setForeground(Color.black);
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        this.setCursor(cur);

    }
}
