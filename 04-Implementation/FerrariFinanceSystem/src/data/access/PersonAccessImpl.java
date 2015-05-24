package data.access;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.jdbc.ConnectionHandler;
import domain.Person;

public class PersonAccessImpl implements PersonAccess {
   private ConnectionHandler con;

   public PersonAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
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

   @Override
	public void updatePerson(Person person) throws SQLException {
		try(PreparedStatement st = con.get().prepareStatement(SQL.UPDATE_ONE)) {
			st.setString(1, person.getFirstName());
			st.setString(2, person.getLastName());
			st.setString(3, person.getStreet());
			st.setInt(4, person.getPostalCode().getPostalCode());
			st.setInt(5, person.getPhone());
			st.setString(6, person.getEmail());
         st.executeUpdate();
		}
	}
   
   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO person"
              + " (id, first_name, last_name, street, postal_code, phone, email)"
              + " VALUES (?, ?, ?, ?, ?, ?, ?)";
      
	static final String UPDATE_ONE
            = "UPDATE person"
				+ " SET first_name = ?,"
				+ " last_name = ?,"
				+ " street = ?,"
				+ " postal_code = ?,"
				+ " phone = ?,"
				+ " email = ?"
				+ " WHERE id = ?";
   }
}
