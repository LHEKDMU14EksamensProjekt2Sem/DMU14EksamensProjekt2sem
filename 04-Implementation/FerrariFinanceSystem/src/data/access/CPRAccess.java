package data.access;

import domain.Person;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Statement.*;

public class CPRAccess {
   private ConnectionHandler con;

   public CPRAccess(ConnectionHandler con) {
      this.con = con;
   }

   public void createCPR(String cpr, Person person) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(
              SQL.INSERT_ONE, RETURN_GENERATED_KEYS)) {
         st.setString(1, cpr);
         st.executeUpdate();

         try (ResultSet rs = st.getGeneratedKeys()) {
            rs.next();
            person.setId(rs.getInt(1));
         }
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO cpr(cpr) VALUES (?)";
   }
}
