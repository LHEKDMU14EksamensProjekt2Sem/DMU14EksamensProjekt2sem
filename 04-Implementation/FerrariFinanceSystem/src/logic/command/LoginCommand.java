package logic.command;

import domain.Employee;
import util.auth.User;
import util.auth.UserAuth;
import util.command.Response;
import util.function.AsyncCallback;
import util.function.AsyncCommand;

import java.sql.SQLException;

public class LoginCommand implements AsyncCommand<User<Employee>, SQLException> {
   private final String username;
   private final char[] password;

   public LoginCommand(String username, char[] password) {
      this.username = username;
      this.password = password;
   }

   @Override
   public void execute(AsyncCallback<User<Employee>, SQLException> callback) {
      new Thread(() -> {
         Response<User<Employee>, SQLException> resp = new Response<>();

         try {
            resp.setOptional(new UserAuth().login(username, password));
         } catch (SQLException e) {
            resp.setException(e);
         }

         callback.call(resp);
      }).start();
   }
}
