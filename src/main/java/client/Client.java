package client;

import common.Protocol;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

public class Client extends SwingWorker<Void, BufferedImage> {

  private Socket socket = null;

  public Client(String hostAddress) throws IOException {
    super();
    socket = new Socket(hostAddress, Protocol.PORT);
  }

  @Override
  protected Void doInBackground() throws Exception {
    var objectInputStream = new DataInputStream(socket.getInputStream());
    while (true) {
      // Read the header indicating the image size
      int imageSize = objectInputStream.readInt();
      byte[] imageBytes = new byte[imageSize];

      // Read the image data
      objectInputStream.readFully(imageBytes);
      BufferedImage image = Protocol.processHostReply(imageBytes);

      publish(image);
    }
  }

  @Override
  protected void process(List<BufferedImage> chunks) {
    for (BufferedImage chunk : chunks) {
      firePropertyChange("image", null, chunk);
    }
  }
}
