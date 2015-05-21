package logic.entity;

import domain.Customer;
import domain.Identity;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;

public interface CustomerLogic {
   void createCustomer(Customer customer, Identity identity, ConnectionHandler con) throws SQLException;

   void createCustomer(Customer customer, Identity identity) throws SQLException;

   Customer readCustomer(int id) throws SQLException;

   void updateCustomer(Customer customer) throws SQLException;
}
