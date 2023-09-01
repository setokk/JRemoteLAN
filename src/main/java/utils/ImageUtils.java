package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageUtils {
  public static byte[] toByteArray(BufferedImage image, String format)
      throws IOException {
    var byteArrayOutputStream = new ByteArrayOutputStream();
    ImageIO.write(image, format, byteArrayOutputStream);
    return byteArrayOutputStream.toByteArray();
  }
}
