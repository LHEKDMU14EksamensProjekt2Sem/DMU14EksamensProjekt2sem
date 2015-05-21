package logic.command;

import data.ConnectionHandlerFactory;
import logic.util.DataUtil;
import util.command.Command;
import util.jdbc.ConnectionHandler;

import java.io.IOException;
import java.sql.SQLException;

public class StartupCommand implements Command {
   @Override
   public void execute() throws IOException, SQLException {
      if (!DataUtil.databaseExists()) {
         try (ConnectionHandler con = ConnectionHandlerFactory.create()) {
            try {
               new CreateDatabaseCommand(con).execute();
               con.commit();
            } catch (SQLException e) {
               con.rollback();
               throw e;
            }
         }
      }
   }
}
