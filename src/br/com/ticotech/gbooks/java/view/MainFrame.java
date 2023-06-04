package br.com.ticotech.gbooks.java.view;
import javax.swing.*;
import java.awt.*;

import br.com.ticotech.gbooks.java.controllers.SaleController;
import br.com.ticotech.gbooks.java.repository.SaleRepository;
import br.com.ticotech.gbooks.java.repository.StockRepository;
import br.com.ticotech.gbooks.java.view.sale.SaleScreen;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.Constants;
import br.com.ticotech.gbooks.java.view.stock.StockScreen;

public class MainFrame {
    private JFrame frame;
    private final SaleScreen saleScreen;
    private final StockScreen stockScreen;
    private final SaleRepository saleRepository = new SaleRepository();
    private final StockRepository stockRepository = new StockRepository();
    private SaleController saleController = new SaleController(stockRepository, saleRepository);
    private JPanel leftPanel;
    private JPanel centerPanel;
    private Button cashierButton;
    private Button stockButton;
    private Button reportButton;
    private Button usersButton;
    private Button logoutButton;

    public MainFrame() {
        createFrame();
        createPanels();
        configureLeftPanel();

        saleScreen = new SaleScreen(saleController);
        centerPanel.add(saleScreen.getCartPanel());
        centerPanel.add(saleScreen.getFinishPanel());
        saleScreen.setVisible(false);

        stockScreen = new StockScreen();
        centerPanel.add(stockScreen.getStockPanel());
        stockScreen.setVisible(false);

        showHomeScreen();

        frame.setVisible(true);
    }

    private void createFrame(){
        frame = new JFrame("G-Books System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setResizable(true);
    }

    private void createPanels() {
        JPanel topPanel = new JPanel();
        leftPanel = new JPanel();
        centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        leftPanel.setLayout(null);
        centerPanel.setLayout(null);

        topPanel.setBackground(Constants.DARK_GRAY);
        leftPanel.setBackground(Constants.MID_GRAY);
        centerPanel.setBackground(Constants.LIGHT_GRAY);
        bottomPanel.setBackground(Constants.DARK_GRAY);

        topPanel.setPreferredSize(new Dimension(100, 30));
        leftPanel.setPreferredSize(new Dimension(300, 100));
        centerPanel.setPreferredSize(new Dimension(100, 100));
        bottomPanel.setPreferredSize(new Dimension(100, 30));

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void createButtons() {
        cashierButton = new Button("Cashier");
        stockButton = new Button("Stock");
        reportButton = new Button("Report");
        usersButton = new Button("Users");
        logoutButton = new Button("Logout");

        cashierButton.addActionListener(e -> showSaleSection());
        stockButton.addActionListener(e -> showStockSection());
        reportButton.setEnabled(false);
        usersButton.setEnabled(false);
        logoutButton.addActionListener(e -> showHomeScreen());
    }

    private void configureLeftPanel() {
        createButtons();
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
        JLabel titleLabel = new JLabel("G-BOOKS");
        titleLabel.setBounds(590,400,440,100);
        titleLabel.setOpaque(false);
        titleLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN,100));
        titleLabel.setForeground(Constants.DARK_GRAY);
        centerPanel.add(titleLabel);

        saleScreen.setVisible(false);
        stockScreen.setVisible(false);

        cashierButton.setEnabled(false);
        stockButton.setEnabled(false);
        reportButton.setEnabled(false);
        usersButton.setEnabled(false);
        logoutButton.setEnabled(false);

        Button enterButton = new Button("Enter");
        enterButton.setBounds(765,530, 90,40);
        enterButton.addActionListener(e -> {
            cashierButton.setEnabled(true);
            stockButton.setEnabled(true);
            //reportButton.setEnabled(true); TODO
            //usersButton.setEnabled(true); TODO
            logoutButton.setEnabled(true);
            titleLabel.setVisible(false);
            enterButton.setVisible(false);
            showSaleSection();
        });
        centerPanel.add(enterButton);
    }

    private void showSaleSection() {
        saleScreen.setVisible(true);
        stockScreen.setVisible(false);
    }

    private void showStockSection(){
        saleScreen.setVisible(false);
        stockScreen.setVisible(true);
    }
}
