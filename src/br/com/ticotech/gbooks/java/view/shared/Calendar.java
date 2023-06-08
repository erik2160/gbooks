package br.com.ticotech.gbooks.java.view.shared;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Calendar extends Component {

    private JCalendar calendar;
    private JButton selectButton;

    public Calendar(String title) {
        calendar = new JCalendar();
        calendar.setDate(java.util.Calendar.getInstance().getTime());

        selectButton = new JButton("Selecionar data");
        selectButton.addActionListener(e -> {
            Date selectedDate = calendar.getDate();
            JOptionPane.showMessageDialog(super.getParent(), "Data selecionada: " + selectedDate);
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(calendar, BorderLayout.CENTER);
        panel.add(selectButton, BorderLayout.SOUTH);

        JFrame frame = new JFrame(title);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(super.getParent());
        frame.setVisible(true);

    }
}

