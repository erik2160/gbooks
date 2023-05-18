package Home;

import javax.swing.*;
import java.awt.*;
import Elements.Button;
import Sale.SaleScreen;
import Sale.Sale;
import Elements.Constants;

public class HomeScreen {
    private JFrame frame;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel centerPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;

    public HomeScreen() {
        frame = new JFrame("G-Books System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        createPanels(); //chamada do método createPanels() no construtor
        createButtons(); //chamada do método createButtons() no construtor
        showDefaultScreen(); //chamada do método showDefaultScreen() no construtor
    }

    public void createPanels() {
        topPanel = new JPanel();
        leftPanel = new JPanel();
        centerPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();

        leftPanel.setLayout(null);
        centerPanel.setLayout(null);

        topPanel.setBackground(Constants.DARKBROWN);
        leftPanel.setBackground(Constants.DARKGRAY);
        centerPanel.setBackground(Constants.LIGHTGRAY);
        rightPanel.setBackground(Constants.LIGHTGRAY);
        bottomPanel.setBackground(Constants.DARKBROWN);

        topPanel.setPreferredSize(new Dimension(100, 30));
        leftPanel.setPreferredSize(new Dimension(250, 100));
        centerPanel.setPreferredSize(new Dimension(100, 100));
        rightPanel.setPreferredSize(new Dimension(30, 100));
        bottomPanel.setPreferredSize(new Dimension(100, 30));

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void createButtons() {
        Button cashierButton = new Button("Cashier");
        Button stockButton = new Button("Stock");
        Button reportButton = new Button("Report");
        Button usersButton = new Button("Users");
        Button logoutButton = new Button("Logout");

        cashierButton.setBounds(30, 60, 190, 40);
        stockButton.setBounds(30, 130, 190, 40);
        reportButton.setBounds(30, 200, 190, 40);
        usersButton.setBounds(30, 270, 190, 40);
        logoutButton.setBounds(30, 340, 190, 40);

        cashierButton.addActionListener(e -> new SaleScreen());
        logoutButton.addActionListener(e -> showDefaultScreen());

        leftPanel.add(cashierButton);
        leftPanel.add(stockButton);
        leftPanel.add(reportButton);
        leftPanel.add(usersButton);
        leftPanel.add(logoutButton);
    }

    public void configureLeftPanel() {
        //configurações específicas do painel lateral
    }

    private void showMainFrame() {
        frame.setVisible(true);
    }

    public void showDefaultScreen() {
        centerPanel.removeAll();
        centerPanel.setVisible(false);
        centerPanel.setVisible(true);

        JLabel titleLabel = new JLabel("G-BOOKS");
        titleLabel.setBounds(280,200,440,100);
        titleLabel.setOpaque(false);
        titleLabel.setFont(new Font("Ubuntu", Font.PLAIN,100));
        titleLabel.setForeground(Constants.DARKBROWN);
        centerPanel.add(titleLabel);

        Button enterButton = new Button("Login");
        enterButton.setBounds(455,330, 90,40);
        centerPanel.add(enterButton);
    }

    public void insertSaleSection() {
        SaleScreen screenSale = new SaleScreen();
        Sale sale = new Sale();
        centerPanel.setLayout(null);
        centerPanel.add(screenSale.insertCartPanel(sale));
        centerPanel.add(screenSale.insertPointsPanel(sale));
        centerPanel.add(screenSale.insertFinishPanel(sale));
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }
}
