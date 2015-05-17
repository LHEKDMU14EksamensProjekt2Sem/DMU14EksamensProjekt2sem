package util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DefaultConnectionHandler implements ConnectionHandler {
   private String url;
   private Connection con;

   public DefaultConnectionHandler(String url) {
      this.url = url;
   }

   @Override
   public Connection get() throws SQLException {
      if (con == null) {
         con = DriverManager.getConnection(url);
         con.setAutoCommit(false);
      }
      return con;
   }

   @Override
   public void commit() throws SQLException {
      if (con == null)
         throw new SQLException("Attempting commit on uninstantiated connection");

      con.commit();
   }

   @Override
   public void rollback() throws SQLException {
      if (con == null)
         throw new SQLException("Attempting rollback on uninstantiated connection");

      con.rollback();
   }

   @Override
   public void close() throws SQLException {
      if (con != null)
         con.close();
   }
}
