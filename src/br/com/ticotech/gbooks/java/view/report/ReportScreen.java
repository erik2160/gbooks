package br.com.ticotech.gbooks.java.view.report;

import br.com.ticotech.gbooks.java.controllers.ReportController;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.Constants;
import br.com.ticotech.gbooks.java.view.shared.Display;
import br.com.ticotech.gbooks.java.view.shared.Table;

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

        Button firstDateButton = new Button();
        firstDateButton.setBounds(26,26,240,50);
        firstDateButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.INITIAL_DATE_BUTTON))));
        firstDateButton.setBorderPainted(false);
        firstDateButton.setContentAreaFilled(false);
        firstDateButton.setEnabled(true);
        //firstDateButton.addActionListener(); TODO
        reportPanel.add(firstDateButton);

        Display firstDateDisplay = new Display("   DD/MM/AAAA");
        firstDateDisplay.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        firstDateDisplay.setBackground(Color.WHITE);
        firstDateDisplay.setBounds(266, 26, 200, 50);
        reportPanel.add(firstDateDisplay);

        Button secondDateButton = new Button();
        secondDateButton.setBounds(600,26,240,50);
        secondDateButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.FINAL_DATE_BUTTON))));
        secondDateButton.setBorderPainted(false);
        secondDateButton.setContentAreaFilled(false);
        secondDateButton.setEnabled(true);
        //secondDateButton.addActionListener(e-> new Calendar("Second")); //TODO
        reportPanel.add(secondDateButton);

        Display secondDateDisplay = new Display("   DD/MM/AAAA");
        secondDateDisplay.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD,23));
        secondDateDisplay.setBackground(Color.WHITE);
        secondDateDisplay.setBounds(843, 26, 200, 50);
        reportPanel.add(secondDateDisplay);

        Button resetFilter = new Button();
        resetFilter.setBounds(1200,26,66,50);
        resetFilter.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.RESET_FILTER))));
        resetFilter.setBorderPainted(false);
        resetFilter.setContentAreaFilled(false);
        resetFilter.setEnabled(true);
        //resetFilter.addActionListener(e-> ); //TODO
        reportPanel.add(resetFilter);

        Button searchButton = new Button();
        searchButton.setBounds(1270,26,245,50);
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
        scrollPane.setBounds(26,100,1488,790);
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


