package gui.client;

import client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ConnectionWindow extends JFrame {

  private Client client;

  private ScreenPanel panel;

  public ConnectionWindow(String hostAddress) {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setMinimumSize(new Dimension(1728, 928));

    panel = new ScreenPanel(null);
    panel.setMinimumSize(new Dimension(1728, 928));
    this.setContentPane(panel);
    this.setVisible(true);
    this.pack();

    try {
      client = new Client(hostAddress);
      client.addPropertyChangeListener((evt) -> {
        if (evt.getPropertyName().equals("image")) {
          SwingUtilities.invokeLater(() -> panel.setImage((BufferedImage) evt.getNewValue()));
        }
      });
      client.execute();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  private class ScreenPanel extends JPanel {
    private BufferedImage image; // The BufferedImage to be displayed

    public ScreenPanel(BufferedImage image) {
      this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);

      // Draw the BufferedImage onto the panel
      if (image != null) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Calculate the scaling factors to fit the image within the panel
        double scaleX = (double) panelWidth / image.getWidth();
        double scaleY = (double) panelHeight / image.getHeight();

        // Use the smaller scale factor to ensure the entire image fits within the panel
        double scale = Math.min(scaleX, scaleY);

        // Calculate the new dimensions for the scaled image
        int scaledWidth = (int) (image.getWidth() * scale);
        int scaledHeight = (int) (image.getHeight() * scale);

        // Calculate the position to center the scaled image
        int x = (panelWidth - scaledWidth) / 2;
        int y = (panelHeight - scaledHeight) / 2;

        // Draw the scaled image
        g.drawImage(image, x, y, scaledWidth, scaledHeight, this);
      } else {
        System.err.println("ERROR drawing image!");
      }
    }

    public void setImage(BufferedImage image) {
      if (image == null)
        return;

      this.image = image;
      repaint(); // Redraw the panel with the new image
    }
  }
}
