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
      con.commit();
   }

   @Override
   public void rollback() throws SQLException {
      con.rollback();
   }

   @Override
   public void close() throws SQLException {
      if (con != null)
         con.close();
   }
}
