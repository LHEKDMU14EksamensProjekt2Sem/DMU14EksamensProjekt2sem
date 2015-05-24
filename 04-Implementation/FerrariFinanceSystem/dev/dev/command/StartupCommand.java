package dev.command;

import data.ConnectionService;
import dev.option.Option;
import logic.command.CreateDatabaseCommand;
import logic.util.DataUtil;
import util.command.Command;
import util.jdbc.ConnectionHandler;

import java.io.IOException;
import java.sql.SQLException;

public class StartupCommand implements Command<Void> {
   /**
    * Sets up the database and stores initial data. If a database file
    * already exists, and <code>Option.DESTROY</code> is set to <code>false</code>,
    * this is a no-op. CAUTION: If <code>Option.DESTROY</code> is set to
    * <code>true</code>, any existing database will be destroyed and a new one created.
    * If <code>Option.SAMPLE</code> is <code>true</code>, a fictional data sample
    * will also be created.
    *
    * @throws IOException
    * @throws SQLException
    */
   @Override
   public Void execute() throws IOException, SQLException {
//      if (Option.DESTROY.get())
         DataUtil.destroyDatabase();

      if (!DataUtil.databaseExists()) {
         try (ConnectionHandler con = ConnectionService.connect()) {
            try {
               new CreateDatabaseCommand(con).execute();

//               if (Option.SAMPLE.get())
                  new CreateSampleCommand(con).execute();

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
