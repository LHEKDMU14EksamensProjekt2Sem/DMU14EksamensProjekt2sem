package logic.service;

import java.sql.SQLException;

import domain.Identity;
import util.jdbc.ConnectionHandler;
import data.ConnectionService;
import data.access.CustomerAccessImpl;
import domain.Customer;

public class CustomerServiceImpl implements CustomerService {
	@Override
	public void createCustomer(Customer customer, Identity identity,
			ConnectionHandler con) throws SQLException {
		new PersonServiceImpl().createPerson(identity, con);
		new CustomerAccessImpl(con).createCustomer(customer);
	}

	@Override
	public void createCustomer(Customer customer, Identity identity) throws SQLException {
		ConnectionService.execute(con ->
				  createCustomer(customer, identity, con));
	}

	@Override
	public Customer readCustomer(int id) throws SQLException {
		return null;
	}

	@Override
	public void updateCustomer(Customer customer) throws SQLException {

	}
}
