import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,720);
        frame.setLayout(new BorderLayout());
        frame.setResizable (false);

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

        JPanel cartPanel = new JPanel();
        cartPanel.setBackground(darkGray);
        centerPanel.setLayout(null);
        cartPanel.setBounds (40, 20,950, 335);
        centerPanel.add(cartPanel);

        JLabel cartLabel = new JLabel ();
        cartPanel.setLayout(null);
        cartLabel.setText ("BARCODE: ");
        cartLabel.setForeground (Color.WHITE);
        cartLabel.setBounds (26, 26,130,33);
        cartLabel.setFont (new Font("Ubuntu", Font.PLAIN,25));
        cartLabel.setOpaque (false);
        cartPanel.add(cartLabel);

        JTextField codeBarText = new JTextField ();
        codeBarText.setBounds (154,26, 300, 33);
        cartPanel.add(codeBarText);

        Button buttonAdd = new Button ("ADD");
        buttonAdd.setBounds (460,26, 110, 33);
        cartPanel.add(buttonAdd);

        Button buttonRemove = new Button ("REMOVE");
        buttonRemove.setBounds (680,26, 120, 33);
        cartPanel.add(buttonRemove);
        buttonRemove.setBackground (new Color (139, 0, 0));
        buttonRemove.setForeground (Color.WHITE);

        Button buttonCancel = new Button ("CANCEL");
        buttonCancel.setBounds (808,26, 120, 33);
        cartPanel.add(buttonCancel);
        buttonCancel.setBackground (new Color (139, 0, 0));
        buttonCancel.setForeground (Color.WHITE);

        JPanel pointsPanel = new JPanel();
        pointsPanel.setBackground(darkGray);
        pointsPanel.setBounds (40, 366,315, 240);
        centerPanel.add(pointsPanel);

        JPanel finishPanel = new JPanel();
        finishPanel.setBackground(darkGray);
        finishPanel.setBounds (370, 366,620, 240);
        centerPanel.add(finishPanel);

        frame.setVisible(true);
    }
}
