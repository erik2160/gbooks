package Home;

import javax.swing.*;
import java.awt.*;
import Elements.Button;
import Sale.SaleScreen;
import Sale.Sale;

public class HomeScreen {
    private JFrame frame;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel centerPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private Color darkBrown;
    private Color darkGray;
    private Color lightGray;
    private Button jButton1;
    private Button jButton2;
    private Button jButton3;
    private Button jButton4;
    private Button jButton5;

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public void setTopPanel(JPanel topPanel) {
        this.topPanel = topPanel;
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public void setLeftPanel(JPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }

    public void setCenterPanel(JPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public void setRightPanel(JPanel rightPanel) {
        this.rightPanel = rightPanel;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public void setBottomPanel(JPanel bottomPanel) {
        this.bottomPanel = bottomPanel;
    }

    public Color getDarkBrown() {
        return darkBrown;
    }

    public void setDarkBrown(Color darkBrown) {
        this.darkBrown = darkBrown;
    }

    public Color getDarkGray() {
        return darkGray;
    }

    public void setDarkGray(Color darkGray) {
        this.darkGray = darkGray;
    }

    public Color getLightGray() {
        return lightGray;
    }

    public void setLightGray(Color lightGray) {
        this.lightGray = lightGray;
    }

    public Button getJButton1() {
        return jButton1;
    }

    public void setJButton1(Button jButton1) {
        this.jButton1 = jButton1;
    }

    public Button getJButton2() {
        return jButton2;
    }

    public void setJButton2(Button jButton2) {
        this.jButton2 = jButton2;
    }

    public Button getJButton3() {
        return jButton3;
    }

    public void setJButton3(Button jButton3) {
        this.jButton3 = jButton3;
    }

    public Button getJButton4() {
        return jButton4;
    }

    public void setJButton4(Button jButton4) {
        this.jButton4 = jButton4;
    }

    public Button getJButton5() {
        return jButton5;
    }

    public void setJButton5(Button jButton5) {
        this.jButton5 = jButton5;
    }

    public HomeScreen() {
        frame = new JFrame();

        topPanel = new JPanel();
        leftPanel = new JPanel();
        centerPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();

        darkBrown = new Color(115, 75, 52);
        darkGray = new Color(140,128,120);
        lightGray = new Color(179,162,153);

        topPanel.setBackground(darkBrown);
        leftPanel.setBackground(darkGray);
        centerPanel.setBackground(lightGray);
        rightPanel.setBackground(lightGray);
        bottomPanel.setBackground(darkBrown);

        topPanel.setPreferredSize(new Dimension(100,30));
        leftPanel.setPreferredSize(new Dimension(250,100));
        centerPanel.setPreferredSize(new Dimension(100,100));
        rightPanel.setPreferredSize(new Dimension(30,100));
        bottomPanel.setPreferredSize(new Dimension(100,30));

        jButton1 = new Button("Cashier");
        jButton2 = new Button("Stock");
        jButton3 = new Button("Report");
        jButton4 = new Button("Users");
        jButton5 = new Button("Logout");

        jButton1.setBounds(30,30,190,40);
        jButton2.setBounds(30,80,190,40);
        jButton3.setBounds(30,130,190,40);
        jButton4.setBounds(30,180,190,40);
        jButton5.setBounds(30,230,190,40);

//        jButton1.addActionListener(showCashier -> centerPanel.setVisible(true));

        leftPanel.setLayout(null);
        leftPanel.add(jButton1);
        leftPanel.add(jButton2);
        leftPanel.add(jButton3);
        leftPanel.add(jButton4);
        leftPanel.add(jButton5);
    }

    public void insertSaleSection() {
        SaleScreen screenSale = new SaleScreen();
        Sale sale = new Sale();
        centerPanel.setLayout(null);
        centerPanel.add(screenSale.insertCartPanel(sale));
        centerPanel.add(screenSale.insertPointsPanel(sale));
        centerPanel.add(screenSale.insertFinishPanel(sale));
    }
}