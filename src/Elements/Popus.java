//package Elements;
//
//public
//class Popus {
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class CustomDialogExample {
    public static void pop ups(String[] args) {
        // Criar um JFrame para servir como o componente pai

        JFrame frame = new JFrame();

        // Configurar um botão para exibir o diálogo personalizado
        JButton button = new JButton("Exibir Diálogo");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCustomDialog(frame);
            }
        });

        // Adicionar o botão ao JFrame
        frame.getContentPane().add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void showCustomDialog(Frame parent) {
        // Criar um JDialog personalizado
        JDialog dialog = new JDialog(parent, "Diálogo Personalizado", true);
        dialog.setLayout(new BorderLayout());
        dialog.setResizable (false);

        // Configurar o conteúdo do diálogo
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(234, 229, 223));
        contentPanel.setLayout (new BorderLayout ());
        JLabel contentLabel = new JLabel("Conteúdo do Diálogo");
        contentLabel.setForeground (Color.BLACK);
        contentLabel.setHorizontalAlignment (JLabel.CENTER);
        contentLabel.setFont(new Font("Ubuntu", Font.BOLD, 16));
        contentPanel.add(contentLabel);

        // Configurar os botões do diálogo
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize (new Dimension (100,40));
        buttonPanel.setBackground(new Color(234, 229, 223));
        Button okButton = new Button("OK");
        okButton.setFont (new Font ("Ubuntu",Font.PLAIN,15));
        Button cancelButton = new Button("Cancelar");
        cancelButton.setFont (new Font ("Ubuntu",Font.PLAIN,15));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Configurar icone
//        JPanel iconPanel = new JPanel ();
//        iconPanel.setLayout (null);
//        iconPanel.setBounds (100,100,10,10);
//        JLabel iconLabel = new JLabel ();
//        iconLabel.setBounds (0, 0, 2 ,2);
//        ImageIcon image = new ImageIcon ("/home/fabs/Downloads/alert(1).png");
//        iconLabel.setIcon (image);
//        iconPanel.add (iconLabel);
//        iconLabel.setVisible (true);

        // Adicionar os componentes ao diálogo
        dialog.add(contentPanel);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
//        dialog.add (iconPanel);

        // Configurar as propriedades do diálogo
        dialog.setSize(300, 127);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}

