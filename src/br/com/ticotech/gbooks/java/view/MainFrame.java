package br.com.ticotech.gbooks.java.view;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import br.com.ticotech.gbooks.java.controllers.ReportController;
import br.com.ticotech.gbooks.java.controllers.SaleController;
import br.com.ticotech.gbooks.java.controllers.StockController;
import br.com.ticotech.gbooks.java.controllers.UserController;
import br.com.ticotech.gbooks.java.repository.SaleRepository;
import br.com.ticotech.gbooks.java.repository.StockRepository;
import br.com.ticotech.gbooks.java.repository.UserRepository;
import br.com.ticotech.gbooks.java.view.login.LoginScreen;
import br.com.ticotech.gbooks.java.view.report.ReportScreen;
import br.com.ticotech.gbooks.java.view.sale.SaleScreen;
import br.com.ticotech.gbooks.java.view.shared.Button;
import br.com.ticotech.gbooks.java.view.shared.Constants;
import br.com.ticotech.gbooks.java.view.stock.StockScreen;

public class MainFrame {
    private JFrame frame;
    private final SaleScreen saleScreen;
    private final StockScreen stockScreen;
    private final ReportScreen reportScreen;
    private final LoginScreen loginScreen;
    private final UserController userController;
    private final JLabel textUserName = new JLabel();
    private final JLabel textUserRole = new JLabel();
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

        SaleRepository saleRepository = new SaleRepository();
        StockRepository stockRepository = new StockRepository();
        UserRepository userRepository = new UserRepository();

        this.userController = new UserController(userRepository);
        loginScreen = new LoginScreen(userController, this);
        centerPanel.add(loginScreen.getLoginPanel());

        SaleController saleController = new SaleController(stockRepository, saleRepository);
        saleScreen = new SaleScreen(saleController);
        saleScreen.setVisible(false);
        centerPanel.add(saleScreen.getCartPanel());
        centerPanel.add(saleScreen.getFinishPanel());

        StockController stockController = new StockController(stockRepository);
        stockScreen = new StockScreen(stockController, userController);
        stockScreen.setVisible(false);
        centerPanel.add(stockScreen.getStockPanel());

        ReportController reportController = new ReportController(saleRepository);
        reportScreen = new ReportScreen(reportController);
        reportScreen.setVisible(false);
        centerPanel.add(reportScreen.getReportPanel());

        configureLeftPanel();
        showLoginSection();

        frame.setVisible(true);}

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

        topPanel.setBackground(Constants.DARK_BLUE);
        leftPanel.setBackground(Constants.BABY_BLUE);
        centerPanel.setBackground(Constants.BABY_BLUE);
        bottomPanel.setBackground(Constants.DARK_BLUE);

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
        reportButton.addActionListener(e -> showReportSection());
        usersButton.setEnabled(false);
        logoutButton.addActionListener(e -> {
            showLoginSection();
            leftPanel.setVisible(false);
        });
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

        JLabel stickBooks = new JLabel();
        stickBooks.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(Constants.STICK_BOOKS))));
        stickBooks.setBounds(-20, 860, 340, 111);

        leftPanel.add(menuLabel);
        leftPanel.add(cashierButton);
        leftPanel.add(stockButton);
        leftPanel.add(reportButton);
        leftPanel.add(usersButton);
        leftPanel.add(logoutButton);
        leftPanel.add(stickBooks);
    }

    private void setCredentialsText(){
        textUserName.setText("Operator: " + userController.getName());
        textUserName.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN, 20));
        textUserName.setBounds(40, 577, 300, 300);

        textUserRole.setText("Role: " + userController.getRoleName());
        textUserRole.setFont(new Font(Constants.DEFAULT_FONT, Font.PLAIN, 20));
        textUserRole.setBounds(40, 600, 300, 300);

        leftPanel.add(textUserName);
        leftPanel.add(textUserRole);
    }

    public void doLogin(int accessType){
        switch (accessType){
            case 1 -> {
                cashierButton.setEnabled(true);
                stockButton.setEnabled(true);
                reportButton.setEnabled(false);
                logoutButton.setEnabled(true);
            }
            case 2 -> {
                cashierButton.setEnabled(true);
                stockButton.setEnabled(true);
                reportButton.setEnabled(true);
                logoutButton.setEnabled(true);
            }
        }
        showSaleSection();
        setCredentialsText();
    }

    private void showSaleSection() {
        saleScreen.setVisible(true);
        stockScreen.setVisible(false);
        reportScreen.setVisible(false);
        loginScreen.setVisible(false);
        leftPanel.setVisible(true);
        centerPanel.setBackground(Color.WHITE);
    }

    private void showStockSection(){
        saleScreen.setVisible(false);
        stockScreen.setVisible(true);
        reportScreen.setVisible(false);
        loginScreen.setVisible(false);
    }

    private void showReportSection(){
        saleScreen.setVisible(false);
        stockScreen.setVisible(false);
        reportScreen.setVisible(true);
        loginScreen.setVisible(false);
    }

    private void showLoginSection() {
        saleScreen.setVisible(false);
        stockScreen.setVisible(false);
        reportScreen.setVisible(false);
        loginScreen.setVisible(true);
        leftPanel.setVisible(false);
        centerPanel.setBackground(Constants.BABY_BLUE);
    }
}
