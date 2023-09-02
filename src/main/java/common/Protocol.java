package common;

import utils.ImageUtils;

import java.awt.image.BufferedImage;

public final class Protocol {
  public static final int PORT = 13699;

  public static BufferedImage processHostReply(byte[] imageBytes) {
    return ImageUtils.bytesToBufferedImage(imageBytes);
  }
}
