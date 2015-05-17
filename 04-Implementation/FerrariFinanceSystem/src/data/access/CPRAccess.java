package data.access;

import domain.Person;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CPRAccess {
   private ConnectionHandler con;

   public CPRAccess(ConnectionHandler con) {
      this.con = con;
   }

   public void createCPR(String cpr, Person person) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         st.setInt(1, person.getId());
         st.setString(2, cpr);
         st.executeUpdate();
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO cpr(id, cpr) VALUES (?, ?)";
   }
}
