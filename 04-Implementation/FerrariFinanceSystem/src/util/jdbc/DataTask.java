package util.jdbc;

import java.sql.SQLException;

@FunctionalInterface
public interface DataTask {
   void execute(ConnectionHandler con) throws SQLException;
}
