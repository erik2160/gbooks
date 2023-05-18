import Home.HomeScreen;

import javax.swing.*;
import java.awt.*;

public class Main {
    private HomeScreen screenMain;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.run();
        });
    }

    private void run() {
        screenMain = new HomeScreen();
        screenMain.insertSaleSection();

        screenMain.createPanels();
        screenMain.createButtons();
        screenMain.showDefaultScreen();

        screenMain.getFrame().setVisible(true);
    }
}
