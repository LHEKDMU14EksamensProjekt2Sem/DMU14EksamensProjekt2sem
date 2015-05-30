package dev.command;

import data.ConnectionService;
import dev.option.Option;
import logic.command.CreateDatabaseCommand;
import logic.util.DataUtil;
import util.jdbc.ConnectionHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class StartupCommand implements Callable<Void> {
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
   public Void call() throws IOException, SQLException {
      if (Option.DESTROY.get())
         DataUtil.destroyDatabase();

      if (!DataUtil.databaseExists()) {
         try (ConnectionHandler con = ConnectionService.connect()) {
            try {
               new CreateDatabaseCommand(con).call();

               if (Option.SAMPLE.get()) {
                  new CreateEmployeeSampleCommand(con).call();
                  new CreateCustomerSampleCommand(con).call();
                  new CreateCarSampleCommand(con).call();
               }

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
