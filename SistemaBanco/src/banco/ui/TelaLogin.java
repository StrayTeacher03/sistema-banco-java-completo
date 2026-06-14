package banco.ui;

import javax.swing.*;

public class TelaLogin extends JFrame {

  private CustomPanel1 panel1;

  public TelaLogin() {
    setTitle("TelaLogin");
    setSize(1024, 768);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);

    panel1 = new CustomPanel1();
    panel1.setBounds(0, 0, 1024, 768);
    this.add(panel1);

    setLocationRelativeTo(null);
  }
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      TelaLogin frame = new TelaLogin();
      frame.setVisible(true);
    });
  }
}
