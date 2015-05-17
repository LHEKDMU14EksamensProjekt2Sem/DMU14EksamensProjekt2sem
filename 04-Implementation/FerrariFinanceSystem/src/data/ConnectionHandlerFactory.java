package data;

import util.jdbc.ConnectionHandler;
import util.jdbc.DefaultConnectionHandler;

public class ConnectionHandlerFactory {
   public static ConnectionHandler create() {
      String url = "jdbc:sqlite:" + DBPath.getPath();
      return new DefaultConnectionHandler(url);
   }
}
