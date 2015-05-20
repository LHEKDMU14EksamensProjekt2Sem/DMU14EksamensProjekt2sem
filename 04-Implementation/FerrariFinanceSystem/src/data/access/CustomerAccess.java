package data.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.jdbc.ConnectionHandler;
import domain.Customer;
import domain.Person;

public class CustomerAccess {
	private ConnectionHandler con;

	public CustomerAccess(ConnectionHandler con) {
		this.con = con;
	}

	public void createCustomer(Customer customer) throws SQLException {
		try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
			st.setInt(1, customer.getPerson().getId());
			st.setBoolean(2, customer.inGoodStanding());
			st.executeUpdate();
		}
	}

	// TODO
	public Customer readCustomer(int id) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;
		Customer customer = new Customer();
		Person person = new Person();

		try {
			st = con.get().prepareStatement(SQL.SELECT_ONE);
			st.setInt(1, id);
			rs = st.executeQuery();
			customer = new Customer();
			person = new Person();

			while (rs.next()) {
				person.setId(id);
				customer.setStanding(rs.getBoolean("standing"));
				person.setFirstName(rs.getString("first_name"));
				person.setLastName(rs.getString("last_name"));
				person.setStreet(rs.getString("street"));
				// person.setPostalCode(rs.getShort("postal_code"));
				// City
				person.setPhone(rs.getInt("phone"));
				person.setEmail(rs.getString("email"));
			}
			return customer;

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
		}
	}

	// TODO
	public void updateCustomer(Customer customer) throws SQLException {
		return;
	}

	private static class SQL {
		static final String INSERT_ONE = "INSERT INTO customer(id, standing) VALUES (?, ?)";

		// TODO
		static final String SELECT_ONE = "SELECT id, standing, first_name, last_name, street, postal_code, phone, email"
				+ "FROM customer "
				+ "JOIN person"
				+ "on id.customer = id.person";

		// TODO
		static final String UPDATE_ONE = "";
	}
}
