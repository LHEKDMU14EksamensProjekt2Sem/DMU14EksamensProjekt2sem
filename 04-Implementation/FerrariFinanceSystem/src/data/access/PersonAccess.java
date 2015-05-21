package data.access;

import domain.Person;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonAccess {
   private ConnectionHandler con;

   public PersonAccess(ConnectionHandler con) {
      this.con = con;
   }

   public void createPerson(Person person) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         st.setInt(1, person.getId());
         st.setString(2, person.getFirstName());
         st.setString(3, person.getLastName());
         st.setString(4, person.getStreet());
         st.setInt(5, person.getPostalCode().getPostalCode());
         st.setInt(6, person.getPhone());
         st.setString(7, person.getEmail());
         st.executeUpdate();
      }
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO person"
              + "(id, first_name, last_name, street, postal_code, phone, email)"
              + "VALUES (?, ?, ?, ?, ?, ?, ?)";
   }
}
