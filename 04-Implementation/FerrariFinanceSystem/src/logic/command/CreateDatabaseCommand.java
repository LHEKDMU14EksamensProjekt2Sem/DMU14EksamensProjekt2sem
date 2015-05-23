package logic.command;

import logic.util.DataUtil;
import util.command.Command;
import util.jdbc.ConnectionHandler;

import java.io.IOException;
import java.sql.SQLException;

public class CreateDatabaseCommand implements Command<Void> {
   private final ConnectionHandler con;

   public CreateDatabaseCommand(ConnectionHandler con) {
      this.con = con;
   }

   @Override
   public Void execute() throws IOException, SQLException {
      DataUtil data = new DataUtil(con);
      data.createDatabase();
      data.importPostalCodes();
      data.storeEmployeeRoles();
      data.storeLoanRequestStatuses();
      return null;
   }
}
