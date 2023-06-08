package br.com.ticotech.gbooks.java.view.shared;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendar extends JFrame {

    private JCalendar calendar;
    private JButton selectButton;
    private JPanel panel;
    private JFrame frame;
    private Date selectedDate;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private String formattedDate;

    public Calendar(String title) {
        calendar = new JCalendar();
        calendar.setDate(java.util.Calendar.getInstance().getTime());

        selectButton = new JButton("Select date");

        panel = new JPanel();
        frame = new JFrame(title);
        frame.setResizable(false);


    }
    public void show (Component componentRelative, Display display){
        selectButton.addActionListener(e -> {
            selectedDate = calendar.getDate();
            formattedDate = dateFormat.format(selectedDate);
            display.setText("      "+formattedDate);
            frame.dispose();
        });
        panel.setLayout(new BorderLayout());
        panel.add(calendar, BorderLayout.CENTER);
        panel.add(selectButton, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocation(componentRelative.getX()+343, componentRelative.getY()+163);
        frame.setVisible(true);

    }
}

