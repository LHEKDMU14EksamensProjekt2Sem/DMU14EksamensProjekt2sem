package data;

import org.sqlite.SQLiteConfig;
import util.jdbc.ConnectionHandler;
import util.jdbc.DataQuery;
import util.jdbc.DataTask;
import util.jdbc.DefaultConnectionHandler;

import java.sql.SQLException;

public class ConnectionService {
   public static ConnectionHandler connect() {
      String url = "jdbc:sqlite:" + DBPath.getPath();
      SQLiteConfig config = new SQLiteConfig();
      config.enforceForeignKeys(true);
      return new DefaultConnectionHandler(url, config.toProperties());
   }

   public static void execute(DataTask task) throws SQLException {
      try (ConnectionHandler con = ConnectionService.connect()) {
         try {
            task.execute(con);
            con.commit();
         } catch (SQLException e) {
            con.rollback();
            throw e;
         }
      }
   }

   public static <R> R query(DataQuery<R> query) throws SQLException {
      try (ConnectionHandler con = ConnectionService.connect()) {
         try {
            return query.execute(con);
         } catch (SQLException e) {
            throw e;
         }
      }
   }
}
