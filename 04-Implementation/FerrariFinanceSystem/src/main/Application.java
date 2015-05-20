package main;

import data.ConnectionHandlerFactory;
import logic.command.CreateDatabaseCommand;
import logic.command.CreateSampleCommand;
import logic.session.main.MainSessionFacade;
import logic.session.main.MainSessionFacadeImpl;
import logic.util.DataUtil;
import ui.main.MainFrame;
import util.jdbc.ConnectionHandler;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.io.IOException;
import java.sql.SQLException;

public class Application {
   private static final String
           NAME = "Ferrari Finance System",
           VERSION = "0.2";

   private MainSessionFacade facade;

   public Application() {
      facade = new MainSessionFacadeImpl();

      try {
         createDatabase(true);
         setSystemLookAndFeel();
         invokeMainFrame();
      } catch (IOException | SQLException e) {
         System.out.println("Fatal error: Database setup failed: " + e);
      }
   }

   /**
    * Sets up the database and stores initial data. If a database file
    * already exists, and the <code>reset</code> flag is set to <code>false</code>,
    * this is a no-op. CAUTION: If <code>reset</code> is set to <code>true</code>,
    * any existing database will be destroyed and a new one created. A reset
    * will also store a fictional data sample for development purposes.
    *
    * @param reset flag indicating whether to reset the database
    * @throws IOException
    * @throws SQLException
    */
   private void createDatabase(boolean reset) throws IOException, SQLException {
      if (reset)
         DataUtil.destroyDatabase();

      if (!DataUtil.databaseExists()) {
         try (ConnectionHandler con = ConnectionHandlerFactory.create()) {
            try {
               new CreateDatabaseCommand(con).execute();
               if (reset)
                  new CreateSampleCommand(con).execute();

               con.commit();
            } catch (SQLException e) {
               con.rollback();
               throw e;
            }
         }
      }
   }

   private void setSystemLookAndFeel() {
      try {
         UIManager.setLookAndFeel(
                 UIManager.getSystemLookAndFeelClassName());
      } catch (Exception e) {
         // No-op
      }
   }

   private void invokeMainFrame() {
      String title = NAME + " v" + VERSION;
      SwingUtilities.invokeLater(() ->
              new MainFrame(facade, title).setVisible(true));
   }
}
