package data.access;

import domain.Identity;
import domain.Person;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Statement.*;

public class IdentityAccessImpl implements IdentityAccess {
   private ConnectionHandler con;

   public IdentityAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createIdentity(Identity identity) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(
              SQL.INSERT_ONE, RETURN_GENERATED_KEYS)) {
         st.setString(1, identity.getCPR());
         st.executeUpdate();

         try (ResultSet rs = st.getGeneratedKeys()) {
            rs.next();
            identity.getPerson().setId(rs.getInt(1));
         }
      }
   }

   @Override
   public Identity readIdentity(Person person) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_ONE)) {
         st.setInt(1, person.getId());

         try (ResultSet rs = st.executeQuery()) {
            Identity identity = new Identity();
            identity.setPerson(person);

            if (rs.next())
               identity.setCPR(rs.getString("cpr"));

            return identity;
         }
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO cpr(cpr) VALUES (?)";

      static final String SELECT_ONE
              = "SELECT cpr FROM cpr WHERE id = ?";
   }
}
