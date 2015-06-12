package logic.service;

import domain.Customer;
import domain.Identity;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.Optional;

public interface CustomerService {
   void createCustomer(Customer customer, Identity identity, ConnectionHandler con) throws SQLException;

   void createCustomer(Customer customer, Identity identity) throws SQLException;

   Optional<Customer> readCustomer(int id) throws SQLException;

   Optional<Customer> readCustomer(Identity identity) throws SQLException;

   void updateCustomer(Customer customer) throws SQLException;
}
