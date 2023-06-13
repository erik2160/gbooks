package br.com.ticotech.gbooks.java.view.shared;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Popups extends JOptionPane{
    private boolean response;
    public Popups(String message, int type) {
        JDialog dialog = new JDialog((Frame) super.getParent(), "", true);
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setLayout(new BorderLayout());

        JLabel contentLabel = new JLabel(message);
        contentLabel.setForeground(Color.BLACK);
        contentLabel.setHorizontalAlignment(JLabel.CENTER);
        contentLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));
        contentPanel.add(contentLabel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(100, 50));
        buttonPanel.setBackground(new Color(255, 255, 255));

        JPanel marginWestPanel = new JPanel();
        marginWestPanel.setBackground(new Color(255, 255, 255));
        marginWestPanel.setLayout(new BorderLayout());
        marginWestPanel.setPreferredSize(new Dimension(20, 60));

        JPanel marginEastPanel = new JPanel();
        marginEastPanel.setBackground(new Color(255, 255, 255));
        marginEastPanel.setLayout(new BorderLayout());
        marginEastPanel.setPreferredSize(new Dimension(25, 60));

        switch (type) {
            case 1 -> {
                dialog.setTitle("ERROR");

                contentLabel.setIcon(new ImageIcon(Objects.requireNonNull(Popups.class.getResource(Constants.ERROR_ICON))));

                Button okButton = new Button(Constants.OK_BUTTON);
                okButton.setPreferredSize(new Dimension(86, 36));
                okButton.addActionListener(closeWindow -> dialog.dispose());
                buttonPanel.add(okButton, BorderLayout.CENTER);
            }
            case 2 -> {
                dialog.setTitle("WARNING");

                contentLabel.setIcon(new ImageIcon(Objects.requireNonNull(Popups.class.getResource(Constants.WARNING_ICON))));

                Button confirmButton = new Button(Constants.YES_BUTTON);
                confirmButton.setPreferredSize(new Dimension(86, 36));
                confirmButton.addActionListener(confirm -> {
                    response = true;
                    dialog.dispose();
                });
                buttonPanel.add(confirmButton);

                Button cancelButton = new Button(Constants.NO_BUTTON);
                cancelButton.setPreferredSize(new Dimension(86, 36));
                cancelButton.addActionListener(cancel -> {
                    response = false;
                    dialog.dispose();
                });
                buttonPanel.add(confirmButton);
                buttonPanel.add(cancelButton);
            }
        }

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.add(marginEastPanel, BorderLayout.EAST);
        dialog.add(marginWestPanel, BorderLayout.WEST);

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
