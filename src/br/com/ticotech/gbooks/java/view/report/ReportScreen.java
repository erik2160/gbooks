package br.com.ticotech.gbooks.java.view.report;

import br.com.ticotech.gbooks.java.controllers.ReportController;
import br.com.ticotech.gbooks.java.view.shared.*;
import br.com.ticotech.gbooks.java.view.shared.Button;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ReportScreen{

    private final JPanel reportPanel;
    private Table table;
    private ReportController reportController;

    public ReportScreen(ReportController reportController){
        this.reportController = reportController;
        reportPanel = new JPanel();
        reportPanel.setLayout(null);
        reportPanel.setBackground(Constants.BABY_BLUE);
        reportPanel.setBounds(40,20,1540,915);

        Display firstDateDisplay = new Display("   DD/MM/AAAA");
        firstDateDisplay.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        firstDateDisplay.setBackground(Color.WHITE);
        firstDateDisplay.setBounds(266, 20, 200, 50);
        reportPanel.add(firstDateDisplay);

        Button firstDateButton = new Button();
        firstDateButton.setBounds(26,20,240,50);
        firstDateButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.INITIAL_DATE_BUTTON))));
        firstDateButton.setBorderPainted(false);
        firstDateButton.setContentAreaFilled(false);
        firstDateButton.setEnabled(true);

        Calendar calendarFirstDate = new Calendar("INITIAL DATE");
        firstDateButton.addActionListener(e -> calendarFirstDate.show(firstDateButton, firstDateDisplay));
        reportPanel.add(firstDateButton);

        Display secondDateDisplay = new Display("   DD/MM/AAAA");
        secondDateDisplay.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        secondDateDisplay.setBackground(Color.WHITE);
        secondDateDisplay.setBounds(843, 20, 200, 50);
        reportPanel.add(secondDateDisplay);

        Button secondDateButton = new Button();
        secondDateButton.setBounds(600,20,240,50);
        secondDateButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.FINAL_DATE_BUTTON))));
        secondDateButton.setBorderPainted(false);
        secondDateButton.setContentAreaFilled(false);
        secondDateButton.setEnabled(true);

        Calendar calendarFinalDate = new Calendar("FINAL DATE");
        secondDateButton.addActionListener(e -> calendarFinalDate.show(secondDateButton, secondDateDisplay));
        reportPanel.add(secondDateButton);


        Button resetFilter = new Button();
        resetFilter.setBounds(1200,20,66,50);
        resetFilter.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.RESET_FILTER))));
        resetFilter.setBorderPainted(false);
        resetFilter.setContentAreaFilled(false);
        resetFilter.setEnabled(true);
        //resetFilter.addActionListener(e-> ); //TODO
        reportPanel.add(resetFilter);

        Button searchButton = new Button();
        searchButton.setBounds(1270,20,245,50);
        searchButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.SEARCH_BUTTON))));
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setEnabled(true);
        //searchButton.addActionListener(e-> ); //TODO
        reportPanel.add(searchButton);

        int [] columnsWidth = {50,50,30,5,30,5,40,40};
        table = new Table(reportController.getReportTableModel(), columnsWidth);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBounds(26,80,1488,810);
        reportPanel.add(scrollPane);

    }
    public void setVisible(boolean isVisible){
        reportPanel.setVisible(isVisible);
        table.setVisible(false);
        table.setVisible(true);
            reportController.getReportTableModel().updateList();

    }

    public JPanel getReportPanel() {
        return reportPanel;
    }
}


