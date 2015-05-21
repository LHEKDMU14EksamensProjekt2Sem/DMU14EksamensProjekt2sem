package data.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import util.jdbc.ConnectionHandler;
import domain.Customer;
import domain.Person;
import domain.PostalCode;

public class CustomerAccessImpl implements CustomerAccess {
	private ConnectionHandler con;

	public CustomerAccessImpl(ConnectionHandler con) {
		this.con = con;
	}

	@Override
	public void createCustomer(Customer customer) throws SQLException {
		createCustomers(Collections.singletonList(customer));
	}

	@Override
	public void createCustomers(List<Customer> customers) throws SQLException {
		try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
			for (Customer customer : customers) {
				st.setInt(1, customer.getPerson().getId());
				st.setBoolean(2, customer.inGoodStanding());
				st.executeUpdate();
			}
		}
	}

	@Override
	public Customer readCustomer(int id) throws SQLException {
		try (PreparedStatement st = con.get().prepareStatement(SQL.SELECT_ONE)) {
			st.setInt(1, id);

			try (ResultSet rs = st.executeQuery()) {
				Customer customer = new Customer();
				Person person = new Person();
				PostalCode postalCode = new PostalCode();
				
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
		static final String SELECT_ONE = "SELECT id, standing, first_name, last_name, street, postal_code, city, phone, email"
				+ " FROM customer c"
				+ " WHERE c.id = ?"
				+ " JOIN person p"
				+ " ON c.id = p.id"
				+ " JOIN postal_code pc"
				+ " ON p.postal_code = pc.postal_code";

	}
}
