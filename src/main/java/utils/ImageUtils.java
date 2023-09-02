package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageUtils {
  public static byte[] toByteArray(BufferedImage image, String format)
      throws IOException {
    var byteArrayOutputStream = new ByteArrayOutputStream();
    ImageIO.write(image, format, byteArrayOutputStream);
    return byteArrayOutputStream.toByteArray();
  }

  public static BufferedImage bytesToBufferedImage(byte[] imageBytes) {
    try {
      var byteArrayInputStream = new ByteArrayInputStream(imageBytes);
      return ImageIO.read(byteArrayInputStream);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

  }
}
