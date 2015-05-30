package logic.command;

import logic.util.DataUtil;
import util.jdbc.ConnectionHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class CreateDatabaseCommand implements Callable<Void> {
   private final ConnectionHandler con;

   public CreateDatabaseCommand(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public Void call() throws IOException, SQLException {
      DataUtil data = new DataUtil(con);
      data.createDatabase();
      data.importPostalCodes();
      data.storeEmployeeRoles();
      data.storeLoanRequestStatuses();
      return null;
   }
}
