import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,720);
        frame.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.red);
        panel2.setBackground(Color.green);
        panel3.setBackground(Color.yellow);
        panel4.setBackground(Color.magenta);
        panel5.setBackground(Color.blue);

        panel1.setPreferredSize(new Dimension(100,30));
        panel2.setPreferredSize(new Dimension(150,100));
        panel3.setPreferredSize(new Dimension(100,100));
        panel4.setPreferredSize(new Dimension(30,100));
        panel5.setPreferredSize(new Dimension(100,30));

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel3, BorderLayout.CENTER);
        frame.add(panel4, BorderLayout.EAST);
        frame.add(panel5, BorderLayout.SOUTH);

        JButton jButton1 = new JButton("1");
        JButton jButton2 = new JButton("2");
        JButton jButton3 = new JButton("3");
        JButton jButton4 = new JButton("4");
        JButton jButton5 = new JButton("5");

        panel2.setLayout(new GridLayout(5,1, 0, 50));

        panel2.add(jButton1);
        panel2.add(jButton2);
        panel2.add(jButton3);
        panel2.add(jButton4);
        panel2.add(jButton5);

        frame.setVisible(true);
    }
}
