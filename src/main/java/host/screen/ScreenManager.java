package host.screen;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class ScreenManager {

  private final Robot robot;
  private final Rectangle screenRect;

  public ScreenManager() throws AWTException {
    // Create a Robot object to simulate user input
    this.robot = new Robot();
    screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
  }

  public BufferedImage captureScreen() {
    return robot.createScreenCapture(screenRect);
  }
}
