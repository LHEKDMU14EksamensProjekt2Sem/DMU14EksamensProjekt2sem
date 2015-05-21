package logic.entity;

import domain.Customer;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;

public interface CustomerLogic {
   void createCustomer(Customer customer, String cpr, ConnectionHandler con) throws SQLException;

   void createCustomer(Customer customer, String cpr) throws SQLException;

   Customer readCustomer(int id) throws SQLException;

   void updateCustomer(Customer customer) throws SQLException;
}
