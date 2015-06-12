package data.access;

import domain.Customer;
import domain.Identity;
import domain.Sale;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CustomerAccess {
   void createCustomer(Customer customer) throws SQLException;

   void createCustomers(List<Customer> customers) throws SQLException;

   Optional<Customer> readCustomer(int id) throws SQLException;

   Optional<Customer> readCustomer(Identity identity) throws SQLException;

   Customer readCustomer(Sale sale) throws SQLException;
}
