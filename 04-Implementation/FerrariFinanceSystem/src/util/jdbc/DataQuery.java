package util.jdbc;

import java.sql.SQLException;

@FunctionalInterface
public interface DataQuery<R> {
   R execute(ConnectionHandler con) throws SQLException;
}
