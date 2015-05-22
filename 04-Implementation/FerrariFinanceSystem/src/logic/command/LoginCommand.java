package logic.command;

import domain.Employee;
import util.auth.User;
import util.auth.UserAuth;
import util.command.AsyncCommand;
import util.command.Receiver;

import java.sql.SQLException;
import java.util.Optional;

public class LoginCommand implements AsyncCommand {
   private final String username;
   private final char[] password;
   private final Receiver<Optional<User<Employee>>> resultReceiver;
   private final Receiver<SQLException> faultReceiver;

   public LoginCommand(String username, char[] password,
                       Receiver<Optional<User<Employee>>> resultReceiver,
                       Receiver<SQLException> faultReceiver) {
      this.username = username;
      this.password = password;
      this.resultReceiver = resultReceiver;
      this.faultReceiver = faultReceiver;
   }

   @Override
   public void execute() {
      try {
         resultReceiver.receive(
                 new UserAuth().login(username, password));
      } catch (SQLException e) {
         faultReceiver.receive(e);
      }
   }
}
