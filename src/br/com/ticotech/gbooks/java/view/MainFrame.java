package br.com.ticotech.gbooks.java.view;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;

import br.com.ticotech.gbooks.java.controllers.SaleController;
import br.com.ticotech.gbooks.java.controllers.StockController;
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
    private StockController stockController = new StockController(stockRepository);
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

        stockScreen = new StockScreen(stockController);
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

        topPanel.setBackground(Constants.BABY_BLUE);
        leftPanel.setBackground(Constants.BABY_BLUE);
        centerPanel.setBackground(Constants.BABY_BLUE);
        bottomPanel.setBackground(Constants.BABY_BLUE);

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
        cashierButton = new Button("");
        cashierButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.CASHIER_BUTTON))));
        cashierButton.setBorderPainted(false);
        cashierButton.setContentAreaFilled(false);

        stockButton = new Button("");
        stockButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.STOCK_BUTTON))));
        stockButton.setBorderPainted(false);
        stockButton.setContentAreaFilled(false);

        reportButton = new Button("");
        reportButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.REPORT_BUTTON))));
        reportButton.setBorderPainted(false);
        reportButton.setContentAreaFilled(false);

        usersButton = new Button("");
        usersButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.USERS_BUTTON))));
        usersButton.setBorderPainted(false);
        usersButton.setContentAreaFilled(false);

        logoutButton = new Button("");
        logoutButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.LOGOUT_BUTTON))));
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);

        cashierButton.addActionListener(e -> showSaleSection());
        stockButton.addActionListener(e -> showStockSection());
        reportButton.setEnabled(false);
        usersButton.setEnabled(false);
        logoutButton.addActionListener(e -> showHomeScreen());
    }

    private void configureLeftPanel() {
        createButtons();
        JLabel menuLabel = new JLabel();
        menuLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.LOGO_ICON))));
        menuLabel.setBounds(22,26,254,243);

        cashierButton.setBounds(38, 320, 222, 45);
        stockButton.setBounds(38, 390, 222, 45);
        reportButton.setBounds(38, 460, 222, 45);
        usersButton.setBounds(38, 530, 222, 45);
        logoutButton.setBounds(38, 600, 222, 45);

        leftPanel.add(menuLabel);
        leftPanel.add(cashierButton);
        leftPanel.add(stockButton);
        leftPanel.add(reportButton);
        leftPanel.add(usersButton);
        leftPanel.add(logoutButton);
    }

    private void showHomeScreen() {
        centerPanel.setBackground(Constants.BABY_BLUE);
        JLabel titleLabel = new JLabel();
        titleLabel.setBounds(840,250,254,243);
        titleLabel.setOpaque(false);
        //titleLabel.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN,100));
        //titleLabel.setForeground(Constants.DARK_GRAY);
        titleLabel.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.LOGO_ICON))));
        centerPanel.add(titleLabel);

        saleScreen.setVisible(false);
        stockScreen.setVisible(false);
        leftPanel.setVisible(false);

        cashierButton.setEnabled(false);
        stockButton.setEnabled(false);
        reportButton.setEnabled(false);
        usersButton.setEnabled(false);
        logoutButton.setEnabled(false);

        Button enterButton = new Button("");
        enterButton.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.LOGIN_BUTTON))));
        enterButton.setBorderPainted(false);
        enterButton.setContentAreaFilled(false);
        enterButton.setBounds(867,530, 197,74);
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
        leftPanel.setVisible(true);
        centerPanel.setBackground(Color.WHITE);
    }

    private void showStockSection(){
        saleScreen.setVisible(false);
        stockScreen.setVisible(true);
        leftPanel.setVisible(true);
        centerPanel.setBackground(Color.WHITE);
    }
}
