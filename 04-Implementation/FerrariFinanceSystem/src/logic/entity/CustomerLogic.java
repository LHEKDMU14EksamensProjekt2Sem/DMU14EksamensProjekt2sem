package logic.entity;

import data.ConnectionHandlerFactory;
import data.access.CustomerAccess;
import domain.Customer;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;

public class CustomerLogic {
   public void createCustomer(Customer customer, String cpr, ConnectionHandler con) throws SQLException {
      new PersonLogic().createPerson(customer.getPerson(), cpr, con);
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
