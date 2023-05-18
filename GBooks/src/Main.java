import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class Main {

    private JFrame frame;
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel centerPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private final Color DARKBROWN = new Color(115, 75, 52);
    private final Color DARKGRAY = new Color(140, 128, 120);
    private final Color LIGHTGRAY = new Color(179, 162, 153);
    private final Color CANCELRED = new Color(139, 0, 0);
    private final Color CONFIRMGREEN = new Color(0, 124, 50);

    public Main(){
        initializeFrame();
        createPanels();
        createButtons();
        configureLeftPanel();
        showMainFrame();
        showDefaultScreen();
    }

    private void initializeFrame() {
        frame = new JFrame("G-Books System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
    }

    private void createPanels() {
        topPanel = new JPanel();
        leftPanel = new JPanel();
        centerPanel = new JPanel();
        rightPanel = new JPanel();
        bottomPanel = new JPanel();

        leftPanel.setLayout(null);
        centerPanel.setLayout(null);

        topPanel.setBackground(DARKBROWN);
        leftPanel.setBackground(DARKGRAY);
        centerPanel.setBackground(LIGHTGRAY);
        rightPanel.setBackground(LIGHTGRAY);
        bottomPanel.setBackground(DARKBROWN);

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

        cashierButton.addActionListener(e -> showCashierScreen());
        logoutButton.addActionListener(e -> showDefaultScreen());

        leftPanel.add(cashierButton);
        leftPanel.add(stockButton);
        leftPanel.add(reportButton);
        leftPanel.add(usersButton);
        leftPanel.add(logoutButton);
    }

    private void configureLeftPanel() {
        // Configurações específicas do painel lateral
    }

    private void showMainFrame() {
        frame.setVisible(true);
    }

    private void showDefaultScreen() {
        centerPanel.removeAll();
        centerPanel.setVisible(false);
        centerPanel.setVisible(true);

        JLabel titleLabel = new JLabel("G-BOOKS");
        titleLabel.setBounds(280,200,440,100);
        titleLabel.setOpaque(false);
        titleLabel.setFont(new Font("Ubuntu", Font.PLAIN,100));
        titleLabel.setForeground(DARKBROWN);
        centerPanel.add(titleLabel);

        Button enterButton = new Button("Login");
        enterButton.setBounds(455,330, 90,40);
        centerPanel.add(enterButton);

    }

    private void showCashierScreen() {
        centerPanel.removeAll();
        centerPanel.setVisible(false);
        centerPanel.setVisible(true);

        //criação do painel do carrinho e características do mesmo
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(null);
        cartPanel.setBackground(DARKGRAY);
        cartPanel.setBounds(40, 20, 950, 335);
        centerPanel.add(cartPanel);//adicionando o painel do carrinho no painel central

        //criação da palavra BARCODE acima do carrinho
        JLabel cartLabel = new JLabel("BARCODE: ");
        cartLabel.setForeground(Color.WHITE);
        cartLabel.setBounds(26, 26, 130, 33);
        cartLabel.setFont(new Font("Ubuntu", Font.PLAIN, 25));
        cartLabel.setOpaque(false);
        cartPanel.add(cartLabel); //adicionando a palavra no painel do carrinho

        //criação do text entry acima do carrinho e suas características
        JTextField codeBarText = new JTextField();
        codeBarText.setBounds(154, 26, 300, 33);
        codeBarText.setFont(new Font("Ubuntu", Font.PLAIN, 22));
        codeBarText.setFont(new Font("Ubuntu", Font.PLAIN, 19));
        cartPanel.add(codeBarText);

        //criação do botão ADD acima do carrinho
        Button buttonAdd = new Button("ADD");
        buttonAdd.setBounds(460, 26, 110, 33);
        cartPanel.add(buttonAdd);

        //criação do botão REMOVE e suas características
        Button buttonRemove = new Button("REMOVE");
        buttonRemove.setBounds(678, 26, 120, 33);
        cartPanel.add(buttonRemove);
        buttonRemove.setBackground(CANCELRED);
        buttonRemove.setForeground(Color.WHITE);

        //criação do botão CANCEL e suas características
        Button buttonCancel = new Button("CANCEL");
        buttonCancel.setBounds(806, 26, 120, 33);
        cartPanel.add(buttonCancel);
        buttonCancel.setBackground(CANCELRED);
        buttonCancel.setForeground(Color.WHITE);

        //criação da tabela/lista de produtos
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("CODE");
        model.addColumn("TITLE");
        model.addColumn("UNITS");
        model.addColumn("UNIT VAL.");
        model.addColumn("TOTAL VAL.");
        JTable listProduct = new JTable(model);
        JScrollPane viewTable = new JScrollPane(listProduct);
        viewTable.setBounds(26, 70, 898, 238);
        cartPanel.add(viewTable);

        //test
        model.addRow(new String[]{
                "55555", "TextBook", "5", "10,50", "52,50"
        });

        //criação do painel do points e características do mesmo
        JPanel pointsPanel = new JPanel();
        pointsPanel.setBackground(DARKGRAY);
        pointsPanel.setBounds(40, 366, 315, 240);
        centerPanel.add(pointsPanel); //adicionando o painel do points no painel central

        //criação das palavras CPF, POINTS, NEW PRICE em points
        JLabel cpfLabel = new JLabel();
        pointsPanel.setLayout(null);
        cpfLabel.setText("CPF: ");
        cpfLabel.setForeground(Color.WHITE);
        cpfLabel.setBounds(66, 6, 130, 30);
        cpfLabel.setFont(new Font("Ubuntu", Font.PLAIN, 19));
        cpfLabel.setOpaque(false);
        pointsPanel.add(cpfLabel); //adicionando a palavra CPF no painel points

        JLabel pointsLabel = new JLabel();
        pointsLabel.setText("POINTS: ");
        pointsLabel.setForeground(Color.WHITE);
        pointsLabel.setBounds(66, 64, 125, 30);
        pointsLabel.setFont(new Font("Ubuntu", Font.PLAIN, 19));
        pointsLabel.setOpaque(false);
        pointsPanel.add(pointsLabel); //adicionando a palavra POINTS no painel points

        JLabel newPriceLabel = new JLabel();
        newPriceLabel.setText("NEW PRICE: ");
        newPriceLabel.setForeground(Color.WHITE);
        newPriceLabel.setBounds(66, 124, 130, 30);
        newPriceLabel.setFont(new Font("Ubuntu", Font.PLAIN, 19));
        newPriceLabel.setOpaque(false);
        pointsPanel.add(newPriceLabel);

        JCheckBox pointsCheckBox = new JCheckBox ("USE POINTS");
        pointsCheckBox.setBackground (Color.yellow);
        pointsCheckBox.setOpaque (true);
        pointsCheckBox.setFocusPainted (false);
        pointsCheckBox.setBounds (100, 194, 115, 30);
        pointsCheckBox.setFont (new Font("Ubuntu", Font.BOLD, 16));
        pointsCheckBox.setForeground (Color.BLACK);
        pointsPanel.add(pointsCheckBox);

        //criação do text entry abaixo do CPF, POINTS, NEW PRICE e suas características
        JTextField cpfText = new JTextField();
        cpfText.setBounds(66, 36, 181, 27);
        cpfText.setFont(new Font("Ubuntu", Font.PLAIN, 19));
        cpfText.setDisabledTextColor (Color.BLACK);
        pointsPanel.add(cpfText);

        JTextField pointsDisplay = new JTextField();
        pointsDisplay.setBounds(66, 94, 181, 27);
        pointsDisplay.setFont(new Font("Ubuntu", Font.PLAIN, 19));
        pointsDisplay.setEnabled (false);
        pointsDisplay.setDisabledTextColor (Color.BLACK);
        pointsPanel.add(pointsDisplay);

        JTextField newPriceDisplay = new JTextField();
        newPriceDisplay.setBounds(66, 155, 181, 27);
        newPriceDisplay.setFont(new Font("Ubuntu", Font.PLAIN, 19));
        newPriceDisplay.setEnabled (false);
        newPriceDisplay.setDisabledTextColor (new Color(36, 124, 68));
        pointsPanel.add(newPriceDisplay);

        cpfText.addActionListener (e -> {
            pointsDisplay.setText ("3000");
            newPriceDisplay.setText ("R$ 2");
        });

        //**
        JPanel finishPanel = new JPanel();
        finishPanel.setLayout(null);
        finishPanel.setBackground(DARKGRAY);
        finishPanel.setBounds(370, 366, 620, 240);
        centerPanel.add(finishPanel);

        JLabel cashLabel = new JLabel("CASH");
        cashLabel.setBounds(52,30,60,24);
        cashLabel.setForeground(Color.WHITE);
        cashLabel.setFont(new Font("Ubuntu", Font.PLAIN, 22));
        finishPanel.add(cashLabel);

        JLabel payedLabel = new JLabel("PAYED");
        payedLabel.setBounds(30,65,60,24);
        payedLabel.setForeground(Color.WHITE);
        payedLabel.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        finishPanel.add(payedLabel);

        JTextField payedField = new JTextField();
        payedField.setBounds(30,90,100,30);
        payedField.setFont(new Font("Ubuntu", Font.BOLD, 16));
        payedField.setForeground(Color.BLACK);
        finishPanel.add(payedField);

        JLabel changeLabel = new JLabel("CHANGE");
        changeLabel.setBounds(30,125,80,24);
        changeLabel.setForeground(Color.WHITE);
        changeLabel.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        finishPanel.add(changeLabel);

        JTextField changeDisplay = new JTextField();
        changeDisplay.setBounds(30,150,100,30);
        changeDisplay.setFont(new Font("Ubuntu", Font.BOLD, 16));
        changeDisplay.setDisabledTextColor(Color.BLACK);
        changeDisplay.setEnabled(false);
        finishPanel.add(changeDisplay);
        payedField.addActionListener(e -> changeDisplay.setText("R$ 110,00"));

        JLabel creditCard = new JLabel("CREDIT CARD:");
        creditCard.setBounds(180,30,144,24);
        creditCard.setForeground(Color.WHITE);
        creditCard.setFont(new Font("Ubuntu", Font.PLAIN, 22));
        finishPanel.add(creditCard);

        RadioButton creditButton = new RadioButton("Credit");
        RadioButton debitButton = new RadioButton("Debit");
        creditButton.setBounds(210,90,100,30);
        debitButton.setBounds(210,150,100,30);
        ButtonGroup creditCardButtons = new ButtonGroup();
        creditCardButtons.add(creditButton);
        creditCardButtons.add(debitButton);
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        creditButton.setCursor(cur);
        debitButton.setCursor(cur);
        finishPanel.add(creditButton);
        finishPanel.add(debitButton);

        JLabel toPayLabel = new JLabel("TO PAY:");
        toPayLabel.setBounds(400,30,80,24);
        toPayLabel.setForeground(Color.WHITE);
        toPayLabel.setFont(new Font("Ubuntu", Font.PLAIN, 22));
        finishPanel.add(toPayLabel);

        JTextField toPayDisplay = new JTextField();
        toPayDisplay.setBounds(370, 80, 150,40);
        toPayDisplay.setText("R$ 1149,90");
        toPayDisplay.setFont(new Font("Ubuntu", Font.BOLD, 24));
        toPayDisplay.setDisabledTextColor(Color.BLACK);
        toPayDisplay.setEnabled(false);
        finishPanel.add(toPayDisplay);

        Button finishButton = new Button("FINISH");
        finishButton.setBounds(370, 130, 150, 50);
        finishButton.setBackground(CONFIRMGREEN);
        finishButton.setForeground(Color.WHITE);

        finishPanel.add(finishButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}
