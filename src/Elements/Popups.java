package Elements;
import javax.swing.*;
import java.awt.*;

public class Popups extends JDialog {
      public void showWarningDialog(Frame parent, String menssage) {
        // Criar um JDialog personalizado
        JDialog dialog = new JDialog(parent, "WARNING", true);
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);

        // Configurar o conteúdo do diálogo
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(234, 229, 223));
        contentPanel.setLayout(new BorderLayout());

        JLabel contentLabel = new JLabel(menssage);
        contentLabel.setForeground(Color.BLACK);
        contentLabel.setHorizontalAlignment(JLabel.CENTER);
        contentLabel.setFont(new Font("Ubuntu", Font.BOLD, 16));
        contentPanel.add(contentLabel, BorderLayout.CENTER);

        // Configurar os botões do diálogo
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(100, 40));
        buttonPanel.setBackground(new Color(234, 229, 223));

        Button okButton = new Button("OK");
        okButton.setFont(new Font("Ubuntu", Font.PLAIN, 15));
        Button cancelButton = new Button("Cancelar");
        cancelButton.setFont(new Font("Ubuntu", Font.PLAIN, 15));

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Configurar ícone
        JPanel iconPanel = new JPanel();
        iconPanel.setBackground(new Color(234, 229, 223));
        iconPanel.setLayout(new BorderLayout());
        iconPanel.setPreferredSize(new Dimension(60, 60)); // Diminui o tamanho do ícone

        JLabel iconLabel = new JLabel(new ImageIcon("/home/fabs/Downloads/alert (5)-PhotoRoom.png-PhotoRoom.png")); // Substitua pelo caminho do seu ícone
        iconLabel.setHorizontalAlignment(JLabel.CENTER);
        iconPanel.add(iconLabel, BorderLayout.CENTER);

        // Adicionar os componentes ao diálogo
        contentPanel.add(iconPanel, BorderLayout.WEST); // Adiciona o painel do ícone ao contentPanel
        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Configurar as propriedades do diálogo
        dialog.setSize(300, 127);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }

    public static void showErrorDialog(Frame parent, String menssage) {
        // Criar um JDialog personalizado
        JDialog dialog = new JDialog(parent, "ERROR", true);
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);

        // Configurar o conteúdo do diálogo
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(234, 229, 223));
        contentPanel.setLayout(new BorderLayout());

        JLabel contentLabel = new JLabel(menssage);
        contentLabel.setForeground(Color.BLACK);
        contentLabel.setHorizontalAlignment(JLabel.CENTER);
        contentLabel.setFont(new Font("Ubuntu", Font.BOLD, 16));
        contentPanel.add(contentLabel, BorderLayout.CENTER);

        // Configurar os botões do diálogo
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(100, 40));
        buttonPanel.setBackground(new Color(234, 229, 223));

        Button okButton = new Button("OK");
        okButton.setFont(new Font("Ubuntu", Font.PLAIN, 15));
        Button cancelButton = new Button("Cancelar");
        cancelButton.setFont(new Font("Ubuntu", Font.PLAIN, 15));

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Configurar ícone
        JPanel iconPanel = new JPanel();
        iconPanel.setBackground(new Color(234, 229, 223));
        iconPanel.setLayout(new BorderLayout());
        iconPanel.setPreferredSize(new Dimension(60, 60)); // Diminui o tamanho do ícone

        JLabel iconLabel = new JLabel(new ImageIcon("/home/fabs/Downloads/Limites_-Por-que-Te-los_-artigo--PhotoRoom.png-PhotoRoom.png")); // Substitua pelo caminho do seu ícone
        iconLabel.setHorizontalAlignment(JLabel.CENTER);
        iconPanel.add(iconLabel, BorderLayout.CENTER);

        // Adicionar os componentes ao diálogo
        contentPanel.add(iconPanel, BorderLayout.WEST); // Adiciona o painel do ícone ao contentPanel
        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Configurar as propriedades do diálogo
        dialog.setSize(300, 127);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
