package br.com.ticotech.gbooks.java.view.report;

import br.com.ticotech.gbooks.java.view.shared.*;

import javax.swing.*;

public class ReportScreen {
    public JPanel insertReportPanel(){
        JPanel reportPanel = new JPanel();
        reportPanel.setLayout(null);
        reportPanel.setBackground(Constants.MID_GRAY);
        reportPanel.setBounds(40,20,950,582);

        Button firstDateButton = new Button("FIRST DATE");
        firstDateButton.setBounds(26,26,150,33);
        firstDateButton.addActionListener(e-> new Calendar("First"));
        reportPanel.add(firstDateButton);

        Button secondDateButton = new Button("SECOND DATE");
        secondDateButton.setBounds(199,26,150,33);
        secondDateButton.addActionListener(e-> new Calendar("Second"));
        reportPanel.add(secondDateButton);


        String[] columnsName = {"CODE","TITLE","AUTHOR","EDITION","PUBLISHER", "UNITS","SELL PRICE","INVOICE PRICE"};
        int [] columnsWidth = {50,50,30,5,30,5,40,40};
        Table table = new Table(columnsName,columnsWidth);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(26,70,899,485);
        reportPanel.add(scrollPane);

        return reportPanel;
    }
}
