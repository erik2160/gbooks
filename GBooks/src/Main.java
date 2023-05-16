import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,720);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        Color darkBrown = new Color(115, 75, 52);
        Color darkGray = new Color(140,128,120);
        Color lightGray = new Color(179,162,153);

        topPanel.setBackground(darkBrown);
        leftPanel.setBackground(darkGray);
        centerPanel.setBackground(lightGray);
        rightPanel.setBackground(lightGray);
        bottomPanel.setBackground(darkBrown);

        topPanel.setPreferredSize(new Dimension(100,30));
        leftPanel.setPreferredSize(new Dimension(250,100));
        centerPanel.setPreferredSize(new Dimension(100,100));
        rightPanel.setPreferredSize(new Dimension(30,100));
        bottomPanel.setPreferredSize(new Dimension(100,30));

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        Button jButton1 = new Button("Cashier");
        Button jButton2 = new Button("Stock");
        Button jButton3 = new Button("Report");
        Button jButton4 = new Button("Users");
        Button jButton5 = new Button("Logout");

        jButton1.setBounds(30,30,190,40);
        jButton2.setBounds(30,80,190,40);
        jButton3.setBounds(30,130,190,40);
        jButton4.setBounds(30,180,190,40);
        jButton5.setBounds(30,230,190,40);

        leftPanel.setLayout(null);
        leftPanel.add(jButton1);
        leftPanel.add(jButton2);
        leftPanel.add(jButton3);
        leftPanel.add(jButton4);
        leftPanel.add(jButton5);

        frame.setVisible(true);
    }
}
