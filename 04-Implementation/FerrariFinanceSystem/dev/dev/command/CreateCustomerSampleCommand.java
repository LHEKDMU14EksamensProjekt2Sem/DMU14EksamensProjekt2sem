package dev.command;

import domain.Customer;
import domain.Identity;
import logic.service.CustomerService;
import logic.service.CustomerServiceImpl;
import util.command.Command;
import util.jdbc.ConnectionHandler;

import java.sql.SQLException;
import java.util.List;

import static dev.sample.CustomerSample.*;

public class CreateCustomerSampleCommand implements Command<Void> {
   private final ConnectionHandler con;

   public CreateCustomerSampleCommand(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public Void execute() throws SQLException {
      createCustomers();
      return null;
   }

   private void createCustomers() throws SQLException {
      List<Customer> customers = newCustomers();
      List<String> cprs = newCustomerCPRs();
      CustomerService logic = new CustomerServiceImpl();
      for (int i = 0; i < cprs.size(); i++) {
         Customer c = customers.get(i);
         Identity identity = new Identity();
         identity.setCPR(cprs.get(i));
         identity.setPerson(c.getPerson());
         logic.createCustomer(c, identity, con);
      }
   }
}
