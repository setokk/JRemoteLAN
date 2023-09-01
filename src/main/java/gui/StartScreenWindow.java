package gui;

import gui.client.ClientWindow;
import gui.host.HostWindow;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StartScreenWindow extends JFrame {

  public static final int WIDTH = 400;
  public static final int HEIGHT = 400;

  private JPanel panel;
  private JLabel clientOrHostLabel;
  private JButton clientBtn;
  private JButton hostBtn;

  public StartScreenWindow() {
    // Initialize Frame
    this.setTitle("JRemoteLAN");
    this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Initialize Text Area
    clientOrHostLabel = new JLabel("Choose an option below:");

    // Initialize Buttons
    clientBtn = new JButton("Continue as client");
    hostBtn = new JButton("Continue as host");
    configureButtonListeners();

    int height = (HEIGHT / 2) - 100;
    clientBtn.setPreferredSize(new Dimension(WIDTH / 2, height));
    hostBtn.setPreferredSize(new Dimension(WIDTH / 2, height));

    // Initialize Panel with GridBadLayout
    panel = new JPanel();
    panel.setLayout(new GridBagLayout());

    // Add client or host option TextArea
    var c = new GridBagConstraints();
    c.fill = GridBagConstraints.VERTICAL;
    c.gridx = 1;
    c.gridy = 0;
    c.gridwidth = WIDTH;
    panel.add(clientOrHostLabel, c);

    // Add client button
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 1;
    c.insets = new Insets(30, 0, 0, 0);
    c.gridwidth = WIDTH / 2;
    panel.add(clientBtn, c);

    // Add host button
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 2;
    c.insets = new Insets(0, 0, 0, 0);
    c.gridwidth = WIDTH / 2;
    panel.add(hostBtn, c);

    // Finish initialization
    this.setContentPane(panel);
    this.setVisible(true);
    pack();
  }

  private void configureButtonListeners() {
    clientBtn.addActionListener((e) -> {
      dispose();
      new ClientWindow();
    });

    hostBtn.addActionListener((event) -> {
      dispose();
      try {
        new HostWindow();
      } catch (IOException | AWTException e) {
        JOptionPane.showMessageDialog(null,
            "There was an error starting the JRemoteLAN server." +
                "Check firewall settings and try again." +
                "If the problem persists, check for IO issues.",
            "Initialization of Server Error!",
            JOptionPane.ERROR_MESSAGE);
      }
    });
  }
}
