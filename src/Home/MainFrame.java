package Home;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import Elements.Button;
import Sale.SaleScreen;
import Sale.Sale;
import Elements.Constants;
import Storage.Storage;

public class MainFrame {
    private final JFrame frame;
    private final SaleScreen screenSale = new SaleScreen();
    private final List<Storage> storage = new ArrayList<>();
    private final Sale sale = new Sale(storage);
    private JPanel leftPanel;
    private JPanel centerPanel;
    private Button cashierButton;
    private Button stockButton;
    private Button reportButton;
    private Button usersButton;
    private Button logoutButton;


    public MainFrame() {
        frame = new JFrame("G-Books System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - 1280) / 2;
        int y = (screenSize.height - 720) / 2;
        frame.setLocation(x, y);
        createPanels();
        createButtons();
        configureLeftPanel();
        showHomeScreen();
        frame.setVisible(true);
    }

    private void createPanels() {
        JPanel topPanel = new JPanel();
        leftPanel = new JPanel();
        centerPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        leftPanel.setLayout(null);
        centerPanel.setLayout(null);

        topPanel.setBackground(Constants.DARK_BROWN);
        leftPanel.setBackground(Constants.DARK_GRAY);
        centerPanel.setBackground(Constants.LIGHT_GRAY);
        rightPanel.setBackground(Constants.LIGHT_GRAY);
        bottomPanel.setBackground(Constants.DARK_BROWN);

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

    private void createButtons() {
        cashierButton = new Button("Cashier");
        stockButton = new Button("Stock");
        reportButton = new Button("Report");
        usersButton = new Button("Users");
        logoutButton = new Button("Logout");

        cashierButton.addActionListener(e -> showSaleSection());
        stockButton.setEnabled(false);
        reportButton.setEnabled(false);
        usersButton.setEnabled(false);
        logoutButton.addActionListener(e -> showHomeScreen());
    }

    private void configureLeftPanel() {
        JLabel menuLabel = new JLabel("MENU");
        menuLabel.setBounds(87,26,80,30);
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.BOLD, 26));

        cashierButton.setBounds(30, 80, 190, 40);
        stockButton.setBounds(30, 150, 190, 40);
        reportButton.setBounds(30, 220, 190, 40);
        usersButton.setBounds(30, 290, 190, 40);
        logoutButton.setBounds(30, 360, 190, 40);

        leftPanel.add(menuLabel);
        leftPanel.add(cashierButton);
        leftPanel.add(stockButton);
        leftPanel.add(reportButton);
        leftPanel.add(usersButton);
        leftPanel.add(logoutButton);
    }

    private void showHomeScreen() {
        centerPanel.removeAll();
        centerPanel.setVisible(false);
        centerPanel.setVisible(true);

        JLabel titleLabel = new JLabel("G-BOOKS");
        titleLabel.setBounds(280,200,440,100);
        titleLabel.setOpaque(false);
        titleLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN,100));
        titleLabel.setForeground(Constants.DARK_BROWN);
        centerPanel.add(titleLabel);

        Button enterButton = new Button("Enter");
        enterButton.setBounds(455,330, 90,40);
        enterButton.addActionListener(e -> showSaleSection());
        centerPanel.add(enterButton);
    }

    private void showSaleSection() {
        centerPanel.removeAll();
        centerPanel.setVisible(false);
        centerPanel.setVisible(true);
        centerPanel.setLayout(null);
        centerPanel.add(screenSale.insertCartPanel(sale));
        centerPanel.add(screenSale.insertPointsPanel(sale));
        centerPanel.add(screenSale.insertFinishPanel(sale));
    }
}
