package logic.entity;

import java.sql.SQLException;

import util.jdbc.ConnectionHandler;
import data.ConnectionService;
import data.access.CustomerAccess;
import domain.Customer;

public class CustomerLogicImpl implements CustomerLogic {
	@Override
	public void createCustomer(Customer customer, String cpr,
			ConnectionHandler con) throws SQLException {
		new PersonLogicImpl().createPerson(customer.getPerson(), cpr, con);
		new CustomerAccess(con).createCustomer(customer);
	}

	@Override
	public void createCustomer(Customer customer, String cpr) throws SQLException {
		ConnectionService.execute(con ->
				  createCustomer(customer, cpr, con));
	}

	@Override
	public Customer readCustomer(int id) throws SQLException {
		return null;
	}

	@Override
	public void updateCustomer(Customer customer) throws SQLException {

	}
}
