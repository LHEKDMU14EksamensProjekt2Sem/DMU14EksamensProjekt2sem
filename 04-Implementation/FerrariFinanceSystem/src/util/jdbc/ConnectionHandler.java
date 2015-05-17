package util.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionHandler extends AutoCloseable {
   Connection get() throws SQLException;

   void commit() throws SQLException;

   void rollback() throws SQLException;

   @Override
   void close() throws SQLException;
}
