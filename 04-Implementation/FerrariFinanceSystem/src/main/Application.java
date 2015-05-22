package main;

import logic.session.main.MainFacade;
import logic.session.main.MainFacadeImpl;
import ui.main.MainFrame;
import util.command.Command;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
   private static final String
           NAME = "Ferrari Finance System",
           VERSION = "0.2";

   private MainFacade facade;

   public Application(Command startup) {
      try {
         startup.execute();

         ExecutorService executor = Executors.newCachedThreadPool();
         facade = new MainFacadeImpl(executor);

         setSystemLookAndFeel();
         invokeMainFrame();
      } catch (Exception e) {
         System.out.println("Fatal error: Database setup failed: " + e);
         System.exit(2);
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
