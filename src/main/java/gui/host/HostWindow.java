package gui.host;

import host.Server;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class HostWindow extends JFrame {

  private static Server server = null;
  private JPanel panel;
  private JLabel connectionLabel;

  public HostWindow() throws IOException, AWTException {
    this.setMinimumSize(new Dimension(500, 300));
    this.setTitle("JRemoteLAN");
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new JPanel();
    panel.setLayout(new GridLayout(1,1));

    connectionLabel = new JLabel("Waiting for a connection...");
    connectionLabel.setHorizontalAlignment(JLabel.CENTER);
    panel.add(connectionLabel);

    this.setContentPane(panel);
    this.setVisible(true);
    pack();

    // Create and start server
    if (server == null)
      server = new Server();
    server.addPropertyChangeListener((evt -> {
      if (evt.getPropertyName().equals("address")) {
        var clientIPAddress = (String) evt.getNewValue();
        connectionLabel.setText("New connection from: " + clientIPAddress);
      }
    }));
    server.execute();
  }
}
