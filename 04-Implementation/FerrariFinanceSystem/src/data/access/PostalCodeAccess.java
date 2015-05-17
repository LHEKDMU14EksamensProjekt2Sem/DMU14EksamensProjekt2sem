package data.access;

import domain.PostalCode;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostalCodeAccess {
   private ConnectionHandler con;

   public PostalCodeAccess(ConnectionHandler con) {
      this.con = con;
   }

   public void createPostalCode(PostalCode postalCode) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         st.setInt(1, postalCode.getPostalCode());
         st.setString(2, postalCode.getCity());
         st.executeUpdate();
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO postal_code(postal_code, city) VALUES (?, ?)";
   }
}
