package host;

import common.Protocol;
import host.screen.ScreenManager;
import utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
    var objectOutputStream = client.getOutputStream();

    while (true) {
      var screenImage = screenManager.captureScreen();
      objectOutputStream.write(ImageUtils.toByteArray(screenImage, "jpg"));
      objectOutputStream.flush();
      Thread.sleep(16);
    }
  }
}
