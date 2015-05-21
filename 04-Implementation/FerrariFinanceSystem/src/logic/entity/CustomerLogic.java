package logic.entity;

import java.sql.SQLException;

import util.jdbc.ConnectionHandler;
import data.ConnectionHandlerFactory;
import data.access.CustomerAccess;
import domain.Customer;

public class CustomerLogic {

	public void createCustomer(Customer customer, String cpr,
			ConnectionHandler con) throws SQLException {
		new PersonLogic().createPerson(customer.getPerson(), cpr, con);
		new CustomerAccess(con).createCustomer(customer);
	}

	public void createCustomer(Customer customer, String cpr)
			throws SQLException {
		try (ConnectionHandler con = ConnectionHandlerFactory.create()) {
			try {
				createCustomer(customer, cpr, con);
				con.commit();
			} catch (SQLException e) {
				con.rollback();
				throw e;
			}
		}
	}

	// TODO
	public Customer readCustomer(String cpr, ConnectionHandler con)
			throws SQLException {
		Customer customer = new Customer();
		return customer;
	}
}
