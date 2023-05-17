import Home.HomeScreen;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    public static void main(String[] args) {
        HomeScreen screenMain = new HomeScreen();
        screenMain.insertSaleSection();

        screenMain.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screenMain.getFrame().setSize(1280,720);
        screenMain.getFrame().setLayout(new BorderLayout());
        screenMain.getFrame().setResizable (false);

        screenMain.getFrame().add(screenMain.getTopPanel(), BorderLayout.NORTH);
        screenMain.getFrame().add(screenMain.getLeftPanel(), BorderLayout.WEST);
        screenMain.getFrame().add(screenMain.getCenterPanel(), BorderLayout.CENTER);
        screenMain.getFrame().add(screenMain.getRightPanel(), BorderLayout.EAST);
        screenMain.getFrame().add(screenMain.getBottomPanel(), BorderLayout.SOUTH);

        screenMain.getFrame().setVisible(true);
    }
}
