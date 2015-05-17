package util.jdbc;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DDLDataAccess {
   private final ConnectionHandler con;

   public DDLDataAccess(ConnectionHandler con) {
      this.con = con;
   }

   public void execute(String sql) throws SQLException {
      try (Statement st = con.get().createStatement()) {
         st.executeUpdate(sql);
      }
   }

   public int[] executeBatch(List<String> batch) throws SQLException {
      try (Statement st = con.get().createStatement()) {
         for (String sql : batch)
            st.addBatch(sql);
         return st.executeBatch();
      }
   }
}
