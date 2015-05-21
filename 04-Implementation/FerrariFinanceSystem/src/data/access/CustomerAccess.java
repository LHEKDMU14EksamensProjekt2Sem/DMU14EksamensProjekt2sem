package data.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import util.jdbc.ConnectionHandler;
import domain.Customer;
import domain.Person;
import domain.PostalCode;

public class CustomerAccess {
	private ConnectionHandler con;

	public CustomerAccess(ConnectionHandler con) {
		this.con = con;
	}

	public void createCustomer(Customer customer) throws SQLException {
		createCustomers(Arrays.asList(customer));
	}

	public void createCustomers(List<Customer> customers) throws SQLException {
		try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
			for (Customer customer : customers) {
				st.setInt(1, customer.getPerson().getId());
				st.setBoolean(2, customer.inGoodStanding());
				st.executeUpdate();
			}
		}
	}

	public Customer readCustomer(int id) throws SQLException {

		Customer customer = new Customer();
		Person person = new Person();
		PostalCode postalCode = new PostalCode();

		try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_ONE)) {
			st.setInt(1, id);

			try (ResultSet rs = st.executeQuery()) {
				customer = new Customer();
				person = new Person();
				postalCode = new PostalCode();
				
				person.setPostalCode(postalCode);
				customer.setPerson(person);

				while (rs.next()) {
					person.setId(id);
					customer.setStanding(rs.getBoolean("standing"));
					person.setFirstName(rs.getString("first_name"));
					person.setLastName(rs.getString("last_name"));
					person.setStreet(rs.getString("street"));
					postalCode.setPostalCode(rs.getShort("postal_code"));
					postalCode.setCity(rs.getString("city"));
					person.setPhone(rs.getInt("phone"));
					person.setEmail(rs.getString("email"));
				}
				return customer;

			}
		}
	}

	private static class SQL {
		static final String INSERT_ONE = "INSERT INTO customer(id, standing) VALUES (?, ?)";

		// TODO
		static final String SELECT_ONE = "SELECT id, standing, first_name, last_name, street, postal_code, phone, email"
				+ "FROM customer c"
				+ "JOIN person p"
				+ "on c.id = p.id"
				+ "JOIN postal_code pc" + "on p.postal_code = pc.postal_code";

	}
}
