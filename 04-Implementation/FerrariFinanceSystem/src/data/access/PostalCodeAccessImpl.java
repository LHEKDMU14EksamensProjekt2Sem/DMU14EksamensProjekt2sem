package data.access;

import domain.PostalCode;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class PostalCodeAccessImpl implements PostalCodeAccess {
   private ConnectionHandler con;

   public PostalCodeAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createPostalCodes(List<PostalCode> postalCodes) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         for (PostalCode postalCode : postalCodes) {
            st.setInt(1, postalCode.getPostalCode());
            st.setString(2, postalCode.getCity());
            st.executeUpdate();
         }
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO postal_code(postal_code, city) VALUES (?, ?)";
   }
}
