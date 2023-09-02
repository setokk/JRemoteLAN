package host;

import common.Protocol;
import host.screen.ScreenManager;
import utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server extends SwingWorker<Void, String> {

  private final ServerSocket serverSocket;
  private final ScreenManager screenManager;

  public Server() throws IOException, AWTException {
    super();
    serverSocket = new ServerSocket(Protocol.PORT);
    screenManager = new ScreenManager();
  }

  @Override
  protected Void doInBackground() throws Exception {
    Socket client = serverSocket.accept();
    String address = client.getInetAddress().getHostAddress();
    publish(address);
    var objectOutputStream = new DataOutputStream(client.getOutputStream());

    while (true) {
      var screenImage = screenManager.captureScreen();

      byte[] imageBytes = ImageUtils.toByteArray(screenImage, "png");

      // Send image length
      objectOutputStream.writeInt(imageBytes.length);
      objectOutputStream.flush();

      // Send image data
      objectOutputStream.write(imageBytes);
      objectOutputStream.flush();
    }
  }

  @Override
  protected void process(List<String> chunks) {
    for (String chunk : chunks) {
      firePropertyChange("address", "", chunk);
    }
  }
}
