package data;

import org.sqlite.SQLiteConfig;
import util.jdbc.ConnectionHandler;
import util.jdbc.DefaultConnectionHandler;

public class ConnectionHandlerFactory {
   public static ConnectionHandler create() {
      String url = "jdbc:sqlite:" + DBPath.getPath();
      SQLiteConfig config = new SQLiteConfig();
      config.enforceForeignKeys(true);
      return new DefaultConnectionHandler(url, config.toProperties());
   }
}
