package logic.command;

import domain.Customer;
import domain.Identity;
import logic.service.CustomerServiceImpl;

import java.util.Optional;
import java.util.concurrent.Callable;

public class FetchCustomerCommand implements Callable<Optional<Customer>> {
   private final Identity identity;

   public FetchCustomerCommand(Identity identity) {
      this.identity = identity;
   }

   @Override
   public Optional<Customer> call() throws Exception {
      return new CustomerServiceImpl().readCustomer(identity);
   }
}
