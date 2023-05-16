import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Testing {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,600);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.lightGray);
        topPanel.setPreferredSize(new Dimension(1, 50));
        frame.add(topPanel, BorderLayout.NORTH);

        JTextField text1 = new JTextField(16);
        topPanel.add(text1);

        JTextField text2 = new JTextField(16);
        topPanel.add(text2);

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("José", 12));
        personList.add(new Person("Fernando", 99));

        JButton button = new JButton("Submit");
        topPanel.add(button);
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        button.setCursor(cur);
        button.addActionListener(e -> {
            String name = text1.getText();
            int age = Integer.parseInt(text1.getText());
            personList.add(new Person(name, age));
        });

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.darkGray);
        frame.add(centerPanel, BorderLayout.CENTER);

        Table table = new Table(personList);
        JTable jtable = new JTable(table);
        centerPanel.add(new JScrollPane(jtable));

        frame.setVisible(true);
    }
}
