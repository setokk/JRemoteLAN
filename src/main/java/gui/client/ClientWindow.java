package gui.client;

import javax.swing.*;
import java.awt.*;

public class ClientWindow extends JFrame {

  public static final int WIDTH = 500;
  public static final int HEIGHT = 300;

  private JPanel panel;
  private JLabel hostIPAddressLabel;
  private JTextField hostIPAddressField;
  private JButton connectToHostBtn;


  public ClientWindow() {
    this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("JRemoteLAN");
    this.setResizable(false);
    this.setLocationRelativeTo(null);

    hostIPAddressLabel = new JLabel("Enter host IP address:");
    hostIPAddressField = new JTextField("");
    hostIPAddressField.setPreferredSize(new Dimension(300, 50));
    hostIPAddressField.setHorizontalAlignment(JTextField.CENTER);
    connectToHostBtn = new JButton("Connect");

    panel = new JPanel(new GridBagLayout());

    final var c = new GridBagConstraints();
    c.anchor = GridBagConstraints.CENTER;
    c.insets = new Insets(5, 0, 5, 0);
    c.gridx = 0;
    c.gridy = 0;
    panel.add(hostIPAddressLabel, c);

    c.gridx = 0;
    c.gridy = 1;
    panel.add(hostIPAddressField, c);

    c.gridx = 0;
    c.gridy = 2;
    panel.add(connectToHostBtn, c);

    this.setContentPane(panel);
    this.setVisible(true);
    this.pack();
  }
}
