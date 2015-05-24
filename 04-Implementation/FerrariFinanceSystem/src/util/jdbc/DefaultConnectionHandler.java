package util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DefaultConnectionHandler implements ConnectionHandler {
   private String url;
   private Properties properties;
   private Connection con;

   public DefaultConnectionHandler(String url, Properties properties) {
      this.url = url;
      this.properties = properties;
   }

   public DefaultConnectionHandler(String url) {
      this(url, new Properties());
   }

   @Override
   public Connection get() throws SQLException {
      if (con == null) {
         con = DriverManager.getConnection(url, properties);
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

   /**
    * Closes the connection. If the connection has already been closed,
    * or the connection has not yet been instantiated, this is a no-op.
    *
    * @throws SQLException
    */
   @Override
   public void close() throws SQLException {
      if (con != null)
         con.close();
   }
}
