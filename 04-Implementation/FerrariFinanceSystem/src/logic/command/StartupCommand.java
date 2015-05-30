package logic.command;

import data.ConnectionService;
import logic.util.DataUtil;
import util.jdbc.ConnectionHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class StartupCommand implements Callable<Void> {
   @Override
   public Void call() throws IOException, SQLException {
      if (!DataUtil.databaseExists()) {
         try (ConnectionHandler con = ConnectionService.connect()) {
            try {
               new CreateDatabaseCommand(con).call();
               con.commit();
            } catch (SQLException e) {
               con.rollback();
               throw e;
            }
         }
      }

      return null;
   }
}
