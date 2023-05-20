import Home.HomeScreen;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.run();
        });
    }

    private void run() {
        HomeScreen screenMain = new HomeScreen();
        screenMain.insertSaleSection();

        screenMain.createPanels();
        screenMain.createButtons();
        screenMain.configureLeftPanel();
        screenMain.showDefaultScreen();

        screenMain.getFrame().setVisible(true);
    }
}
