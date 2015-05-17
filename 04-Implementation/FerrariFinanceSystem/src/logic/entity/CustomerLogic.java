package logic.entity;

import data.ConnectionHandlerFactory;
import data.access.CPRAccess;
import data.access.CustomerAccess;
import data.access.PersonAccess;
import domain.Customer;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;

public class CustomerLogic {
   public void createCustomer(Customer customer, String cpr, ConnectionHandler con) throws SQLException {
      new PersonAccess(con).createPerson(customer.getPerson());
      new CPRAccess(con).createCPR(cpr, customer.getPerson());
      new CustomerAccess(con).createCustomer(customer);
   }

   public void createCustomer(Customer customer, String cpr) throws SQLException {
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
}
