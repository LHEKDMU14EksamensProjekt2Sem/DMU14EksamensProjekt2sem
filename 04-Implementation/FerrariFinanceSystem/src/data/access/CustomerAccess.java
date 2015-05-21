package data.access;

import domain.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerAccess {
   void createCustomer(Customer customer) throws SQLException;

   void createCustomers(List<Customer> customers) throws SQLException;

   Customer readCustomer(int id) throws SQLException;
}
