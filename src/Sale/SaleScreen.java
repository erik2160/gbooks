package Sale;

import Elements.Button;
import Elements.Constants;
import Home.HomeScreen;

import javax.swing.*;
import java.awt.*;
public class SaleScreen extends HomeScreen {
    private JPanel cartPanel;
    private JPanel pointsPanel;
    private JPanel finishPanel;
    private JLabel cartLabel;
    private JTextField codeBarText;
    private Button buttonAdd;
    private Button buttonRemove;
    private Button buttonCancel;

    public JPanel getCartPanel() {
        return cartPanel;
    }

    public void setCartPanel(JPanel cartPanel) {
        this.cartPanel = cartPanel;
    }

    public JPanel getPointsPanel() {
        return pointsPanel;
    }

    public void setPointsPanel(JPanel pointsPanel) {
        this.pointsPanel = pointsPanel;
    }

    public JPanel getFinishPanel() {
        return finishPanel;
    }

    public void setFinishPanel(JPanel finishPanel) {
        this.finishPanel = finishPanel;
    }

    public JLabel getCartLabel() {
        return cartLabel;
    }

    public void setCartLabel(JLabel cartLabel) {
        this.cartLabel = cartLabel;
    }

    public JTextField getCodeBarText() {
        return codeBarText;
    }

    public void setCodeBarText(JTextField codeBarText) {
        this.codeBarText = codeBarText;
    }

    public Button getButtonAdd() {
        return buttonAdd;
    }

    public void setButtonAdd(Button buttonAdd) {
        this.buttonAdd = buttonAdd;
    }

    public Button getButtonRemove() {
        return buttonRemove;
    }

    public void setButtonRemove(Button buttonRemove) {
        this.buttonRemove = buttonRemove;
    }

    public Button getButtonCancel() {
        return buttonCancel;
    }

    public void setButtonCancel(Button buttonCancel) {
        this.buttonCancel = buttonCancel;
    }

    public JPanel insertCartPanel(Sale sale) {
        cartPanel = new JPanel();
        cartPanel.setLayout(null);
        cartPanel.setBackground(Constants.DARKGRAY);
        cartPanel.setBounds(40, 20,950, 335);

        cartLabel = new JLabel("BARCODE: ");
        cartLabel.setLayout(null);
        cartLabel.setForeground(Color.WHITE);
        cartLabel.setBounds(26, 26,130,33);
        cartLabel.setFont(new Font("Ubuntu", Font.PLAIN,25));
        cartLabel.setOpaque(false);
        cartPanel.add(getCartLabel());

        codeBarText = new JTextField();
        codeBarText.setBounds(154, 26, 300, 33);
        cartPanel.add(codeBarText);

        buttonAdd = new Button ("ADD");
        buttonAdd.setBounds(460,26, 110, 33);
        cartPanel.add(buttonAdd);

        buttonRemove = new Button("REMOVE");
        buttonRemove.setBounds(678,26, 120, 33);
        buttonRemove.setBackground(new Color (139, 0, 0));
        buttonRemove.setForeground(Color.WHITE);
        cartPanel.add(buttonRemove);

        buttonCancel = new Button("CANCEL");
        buttonCancel.setBounds(806,26, 120, 33);
        buttonCancel.setBackground(new Color (139, 0, 0));
        buttonCancel.setForeground(Color.WHITE);
        cartPanel.add(buttonCancel);

        return cartPanel;
    }

    public JPanel insertPointsPanel(Sale sale) {
        pointsPanel = new JPanel();
        pointsPanel.setBackground(Constants.DARKGRAY);
        pointsPanel.setBounds(40, 366,315, 240);

        return pointsPanel;
    }

    public JPanel insertFinishPanel(Sale sale) {
        finishPanel = new JPanel();
        finishPanel.setBackground(Constants.DARKGRAY);
        finishPanel.setBounds(370, 366,620, 240);

        return finishPanel;
    }
}
