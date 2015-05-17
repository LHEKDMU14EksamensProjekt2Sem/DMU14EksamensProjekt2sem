package data.access;

import domain.Person;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Statement.*;

public class PersonAccess {
   private ConnectionHandler con;

   public PersonAccess(ConnectionHandler con) {
      this.con = con;
   }

   public void createPerson(Person person) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(
              SQL.INSERT_ONE, RETURN_GENERATED_KEYS)) {
         st.setString(1, person.getFirstName());
         st.setString(2, person.getLastName());
         st.setString(3, person.getStreet());
         st.setInt(4, person.getPostalCode().getPostalCode());
         st.setInt(5, person.getPhone());
         st.setString(6, person.getEmail());
         st.executeUpdate();

         try (ResultSet rs = st.getGeneratedKeys()) {
            rs.next();
            person.setId(rs.getInt(1));
         }
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO person"
              + "(first_name, last_name, street, postal_code, phone, email)"
              + "VALUES (?, ?, ?, ?, ?, ?)";
   }
}
