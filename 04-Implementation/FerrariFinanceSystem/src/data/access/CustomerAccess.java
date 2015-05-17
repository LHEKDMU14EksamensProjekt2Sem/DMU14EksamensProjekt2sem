package data.access;

import domain.Customer;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerAccess {
   private ConnectionHandler con;

   public CustomerAccess(ConnectionHandler con) {
      this.con = con;
   }

   public void createCustomer(Customer customer) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         st.setInt(1, customer.getPerson().getId());
         st.setBoolean(2, customer.inGoodStanding());
         st.executeUpdate();
      }
   }

   // TODO
   public Customer readCustomer(int id) throws SQLException {
      return null;
   }

   // TODO
   public void updateCustomer(Customer customer) throws SQLException {
      return;
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO customer(id, standing) VALUES (?, ?)";

      // TODO
      static final String SELECT_ONE = "";

      // TODO
      static final String UPDATE_ONE = "";
   }
}
