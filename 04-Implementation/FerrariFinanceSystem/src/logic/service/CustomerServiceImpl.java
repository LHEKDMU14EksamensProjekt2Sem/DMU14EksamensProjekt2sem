package logic.service;

import data.ConnectionService;
import data.access.CustomerAccessImpl;
import domain.Customer;
import domain.Identity;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.Optional;

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
   public Optional<Customer> readCustomer(int id) throws SQLException {
      return ConnectionService.query(con ->
              new CustomerAccessImpl(con).readCustomer(id));
   }

   @Override
   public void updateCustomer(Customer customer) throws SQLException {
      // TODO
   }
}
