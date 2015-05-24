package data.access;

import domain.PostalCode;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

   @Override
   public Optional<PostalCode> readPostalCode(int postalCode) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_ONE)) {
         st.setInt(1, postalCode);

         try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
               PostalCode pc = new PostalCode();
               pc.setPostalCode(postalCode);
               pc.setCity(rs.getString("city"));
               return Optional.of(pc);
            } else {
               return Optional.empty();
            }
         }
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO postal_code(postal_code, city) VALUES (?, ?)";

      static final String SELECT_ONE
              = "SELECT city FROM postal_code WHERE postal_code = ?";
   }
}
