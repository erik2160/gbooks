package br.com.gbooks.java;

import br.com.gbooks.java.view.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.run();
        });
    }

    private void run() {
        new MainFrame();
    }
}
