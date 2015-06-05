package logic.command;

import domain.Identity;
import domain.Person;
import logic.service.IdentityServiceImpl;

import java.sql.SQLException;
import java.util.concurrent.Callable;

public class FetchIdentityCommand implements Callable<Identity> {
   private final Person person;

   public FetchIdentityCommand(Person person) {
      this.person = person;
   }

   @Override
   public Identity call() throws SQLException {
      return new IdentityServiceImpl().readIdentity(person);
   }
}
