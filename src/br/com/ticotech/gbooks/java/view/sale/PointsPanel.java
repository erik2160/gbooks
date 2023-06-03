package br.com.ticotech.gbooks.java.view.sale;

import br.com.ticotech.gbooks.java.controllers.SaleController;
import br.com.ticotech.gbooks.java.view.shared.*;
import br.com.ticotech.gbooks.java.view.shared.TextField;

import javax.swing.*;
import java.awt.*;

public class PointsPanel {
    private Display pointsDisplay;
    private Display newPriceDisplay;

    public JPanel insertPointsPanel(SaleController saleController) {
        JPanel pointsPanel = new JPanel();
        pointsPanel.setBackground(Constants.DARK_GRAY);
        pointsPanel.setBounds(40, 366, 315, 240);
        pointsPanel.setLayout(null);

        TextField cpfText = new TextField("CPF");
        cpfText.setBounds(66, 38, 181, 27);
        pointsPanel.add(cpfText);

        pointsDisplay = new Display("POINTS");
        pointsDisplay.setBounds(66, 80, 181, 27);
        pointsPanel.add(pointsDisplay);

        newPriceDisplay = new Display("NEW PRICE", Constants.CONFIRM_GREEN);
        newPriceDisplay.setBounds(66, 122, 181, 27);
        pointsPanel.add(newPriceDisplay);

        JCheckBox pointsCheckBox = new JCheckBox("USE POINTS");
        pointsCheckBox.setBackground(Color.YELLOW);
        pointsCheckBox.setOpaque(true);
        pointsCheckBox.setFocusPainted(false);
        pointsCheckBox.setBounds(100, 179, 115, 30);
        pointsCheckBox.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 16));
        pointsPanel.add(pointsCheckBox);

        //APENAS PARA TESTE
//        cpfText.addActionListener(e -> {
//            pointsDisplay.setText("300");
//            newPriceDisplay.setText("R$ 20");
//        });

        return pointsPanel;
    }
}
