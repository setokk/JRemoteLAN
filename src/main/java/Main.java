import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import gui.StartScreenWindow;

import javax.swing.*;
import java.awt.*;

public class Main {
  public static void main(String[] args) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatMacDarkLaf());

    Font resizedFont = new Font("Segoe UI", Font.PLAIN, 20);
    UIManager.put("Label.font", resizedFont);
    UIManager.put("Button.font", resizedFont);
    UIManager.put("TextArea.font", resizedFont);
    new StartScreenWindow();
  }
}
