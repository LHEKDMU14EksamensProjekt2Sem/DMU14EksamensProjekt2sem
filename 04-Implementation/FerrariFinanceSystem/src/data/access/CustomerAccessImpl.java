package data.access;

import domain.Customer;
import domain.Identity;
import domain.Person;
import domain.PostalCode;
import domain.Sale;
import util.jdbc.ConnectionHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CustomerAccessImpl implements CustomerAccess {
   private ConnectionHandler con;

   public CustomerAccessImpl(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public void createCustomer(Customer customer) throws SQLException {
      createCustomers(Collections.singletonList(customer));
   }

   @Override
   public void createCustomers(List<Customer> customers) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.INSERT_ONE)) {
         for (Customer customer : customers) {
            st.setInt(1, customer.getPerson().getId());
            st.setBoolean(2, customer.inGoodStanding());
            st.executeUpdate();
         }
      }
   }

   @Override
   public Optional<Customer> readCustomer(int id) throws SQLException {
      return readCustomer(id, SQL.BY_ID);
   }

   @Override
   public Optional<Customer> readCustomer(Identity identity) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.selectOne(SQL.BY_IDENTITY))) {
         st.setString(1, identity.getCPR());

         try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
               return Optional.of(extractCustomer(rs));
            } else {
               return Optional.empty();
            }
         }
      }
   }

   @Override
   public Customer readCustomer(Sale sale) throws SQLException {
      return readCustomer(sale.getId(), SQL.BY_SALE).get();
   }

   private Optional<Customer> readCustomer(int id, String by) throws SQLException {
      try (PreparedStatement st = con.get().prepareStatement(SQL.selectOne(by))) {
         st.setInt(1, id);

         try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
               return Optional.of(extractCustomer(rs));
            } else {
               return Optional.empty();
            }
         }
      }
   }

   private Customer extractCustomer(ResultSet rs) throws SQLException {
      Customer customer = new Customer();
      Person person = new Person();
      PostalCode postalCode = new PostalCode();

      person.setPostalCode(postalCode);
      customer.setPerson(person);

      person.setId(rs.getInt("id"));
      customer.setStanding(rs.getBoolean("standing"));
      person.setFirstName(rs.getString("first_name"));
      person.setLastName(rs.getString("last_name"));
      person.setStreet(rs.getString("street"));
      postalCode.setPostalCode(rs.getShort("postal_code"));
      postalCode.setCity(rs.getString("city"));
      person.setPhone(rs.getInt("phone"));
      person.setEmail(rs.getString("email"));

      return customer;
   }

   private static class SQL {
      static final String INSERT_ONE
              = "INSERT INTO customer(id, standing) VALUES (?, ?)";

      static final String SELECT_ONE
              = "SELECT c.id, standing, first_name, last_name, street, p.postal_code, city, phone, email"
              + " FROM customer c"
              + " LEFT JOIN person p ON p.id = c.id"
              + " LEFT JOIN postal_code pc ON p.postal_code = pc.postal_code"
              + " WHERE c.id = %s";

      static final String BY_ID = "?";

      static final String BY_IDENTITY
              = "(SELECT id FROM cpr WHERE cpr = ?)";

      static final String BY_SALE
              = "(SELECT customer_id FROM sale WHERE id = ?)";

      static String selectOne(String by) {
         return String.format(SELECT_ONE, by);
      }
   }
}
