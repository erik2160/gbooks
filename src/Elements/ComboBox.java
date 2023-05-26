package Elements;

import javax.swing.*;
import java.awt.*;

public class ComboBox extends JComboBox<String> {

    static class ComboBoxRenderer extends DefaultListCellRenderer {
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (isSelected) {
                label.setForeground(Color.WHITE);
                label.setBackground(Constants.DARK_BROWN);
            } else {
                label.setForeground(Color.BLACK);
                label.setBackground(Color.WHITE);
            }

            return label;
        }
    }
    public ComboBox(String[] items){
        for(String item:items){
            addItem(item);
        }
        setBackground(Color.WHITE);
        setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN, 20));
        setRenderer(new ComboBoxRenderer());

    }
}
