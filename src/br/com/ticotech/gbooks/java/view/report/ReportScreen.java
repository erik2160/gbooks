package br.com.ticotech.gbooks.java.view.report;

import br.com.ticotech.gbooks.java.controllers.ReportController;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.Constants;
import br.com.ticotech.gbooks.java.view.shared.Table;

import javax.swing.*;

public class ReportScreen{

    private final JPanel reportPanel;
    private Table table;
    private ReportController reportController;

    public ReportScreen(ReportController reportController){
        this.reportController = reportController;
        reportPanel = new JPanel();
        reportPanel.setLayout(null);
        reportPanel.setBackground(Constants.MID_GRAY);
        reportPanel.setBounds(40,20,1540,915);

        Button firstDateButton = new Button("FIRST DATE");
        firstDateButton.setBounds(26,26,150,33);
        firstDateButton.setEnabled(false);
        //firstDateButton.addActionListener(); TODO
        reportPanel.add(firstDateButton);

        Button secondDateButton = new Button("SECOND DATE");
        secondDateButton.setBounds(199,26,150,33);
        secondDateButton.setEnabled(false);
        //secondDateButton.addActionListener(e-> new Calendar("Second")); //TODO
        reportPanel.add(secondDateButton);

        int [] columnsWidth = {50,50,30,5,30,5,40,40};
        table = new Table(reportController.getReportTableModel(), columnsWidth);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(26,70,1488,820);
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


