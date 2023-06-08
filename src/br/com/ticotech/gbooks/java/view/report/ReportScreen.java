package br.com.ticotech.gbooks.java.view.report;

import br.com.ticotech.gbooks.java.controllers.ReportController;
import br.com.ticotech.gbooks.java.view.shared.*;
import br.com.ticotech.gbooks.java.view.shared.Button;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

public class ReportScreen{

    private final JPanel reportPanel;
    private final Table table;
    private final ReportController reportController;
    private Display firstDateDisplay;
    private final Display secondDateDisplay;

    public ReportScreen(ReportController reportController){
        this.reportController = reportController;
        reportPanel = new JPanel();
        reportPanel.setLayout(null);
        reportPanel.setBackground(Constants.BABY_BLUE);
        reportPanel.setBounds(40,20,1540,915);

        firstDateDisplay = new Display("   DD/MM/YYYY");
        firstDateDisplay.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        firstDateDisplay.setBackground(Color.WHITE);
        firstDateDisplay.setBounds(266, 20, 200, 50);
        reportPanel.add(firstDateDisplay);

        Button firstDateButton = new Button(Constants.INITIAL_DATE_BUTTON);
        firstDateButton.setBounds(26,20,240,50);
        firstDateButton.setEnabled(true);

        Calendar calendarFirstDate = new Calendar("INITIAL DATE");
        firstDateButton.addActionListener(e -> calendarFirstDate.show(firstDateButton, firstDateDisplay));
        reportPanel.add(firstDateButton);

        secondDateDisplay = new Display("   DD/MM/YYYY");
        secondDateDisplay.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        secondDateDisplay.setBackground(Color.WHITE);
        secondDateDisplay.setBounds(843, 20, 200, 50);
        reportPanel.add(secondDateDisplay);

        Button secondDateButton = new Button(Constants.FINAL_DATE_BUTTON);
        secondDateButton.setBounds(600,20,240,50);
        secondDateButton.setEnabled(true);

        Calendar calendarFinalDate = new Calendar("FINAL DATE");
        secondDateButton.addActionListener(e -> calendarFinalDate.show(secondDateButton, secondDateDisplay));
        reportPanel.add(secondDateButton);


        Button resetFilter = new Button(Constants.RESET_FILTER);
        resetFilter.setBounds(1200,20,66,50);
        resetFilter.setEnabled(true);
        resetFilter.addActionListener(e-> {
            firstDateDisplay.reset();
            secondDateDisplay.reset();
        });
        reportPanel.add(resetFilter);

        Button searchButton = new Button(Constants.SEARCH_BUTTON);
        searchButton.setBounds(1270,20,245,50);
        searchButton.setEnabled(true);
        searchButton.addActionListener(e-> doSearch());
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

    private void doSearch(){
        String firstDate = firstDateDisplay.getText();
        String secondDate = secondDateDisplay.getText();
        reportController.doSearch(firstDate, secondDate);
        table.setVisible(false);
        table.setVisible(true);
    }
}


