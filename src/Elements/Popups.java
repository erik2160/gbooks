package Elements;
import javax.swing.*;
import java.awt.*;

public class Popups extends JOptionPane{
    private boolean response;
    public Popups(String message, int type) {
        JDialog dialog = new JDialog((Frame) super.getParent(), "title", true);
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(234, 229, 223));
        contentPanel.setLayout(new BorderLayout());

        JLabel contentLabel = new JLabel(message);
        contentLabel.setForeground(Color.BLACK);
        contentLabel.setHorizontalAlignment(JLabel.CENTER);
        contentLabel.setFont(new Font("Ubuntu", Font.BOLD, 16));
        contentPanel.add(contentLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(100, 40));
        buttonPanel.setBackground(new Color(234, 229, 223));

        JPanel iconPanel = new JPanel();
        iconPanel.setBackground(new Color(234, 229, 223));
        iconPanel.setLayout(new BorderLayout());
        iconPanel.setPreferredSize(new Dimension(60, 60));

        JLabel iconLabel = new JLabel();
        iconLabel.setHorizontalAlignment(JLabel.CENTER);
        iconPanel.add(iconLabel, BorderLayout.CENTER);

        JPanel marginPanel = new JPanel();
        marginPanel.setBackground(new Color(234, 229, 223));
        marginPanel.setLayout(new BorderLayout());
        marginPanel.setPreferredSize(new Dimension(20, 60));

        switch (type) {
            case 1 -> {
                dialog.setTitle("ERROR");

                iconLabel.setIcon(new ImageIcon(Popups.class.getResource("resources/ErrorIcon.png")));

                Button okButton = new Button("OK");
                okButton.addActionListener(closeWindow -> dialog.dispose());
                buttonPanel.add(okButton);
            }
            case 2 -> {
                dialog.setTitle("WARNING");

                iconLabel.setIcon(new ImageIcon(Popups.class.getResource("resources/WarningIcon.png")));

                Button confirmButton = new Button("CONFIRM");
                confirmButton.addActionListener(confirm -> {
                    response = true;
                    dialog.dispose();
                });
                buttonPanel.add(confirmButton);

                Button cancelButton = new Button("CANCEL");
                cancelButton.addActionListener(cancel -> {
                    response = false;
                    dialog.dispose();
                });
                buttonPanel.add(confirmButton);
                buttonPanel.add(cancelButton);
            }
            case 3 -> {
            }
        }

        contentPanel.add(iconPanel, BorderLayout.WEST);
        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.add(marginPanel, BorderLayout.EAST);

        dialog.pack();

        int messageLabelWidth = contentLabel.getPreferredSize().width;
        dialog.setPreferredSize(new Dimension(messageLabelWidth, contentPanel.getPreferredSize().height));
        dialog.setLocationRelativeTo(super.getParent());
        dialog.setVisible(true);
    }

    public boolean getResponse(){
        return response;
    }
}