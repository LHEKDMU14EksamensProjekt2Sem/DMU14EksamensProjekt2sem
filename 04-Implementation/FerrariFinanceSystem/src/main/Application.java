package main;

import logic.session.main.MainSessionFacade;
import logic.session.main.MainSessionFacadeImpl;
import ui.main.MainFrame;
import util.function.Command;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Application {
   private static final String
           NAME = "Ferrari Finance System",
           VERSION = "0.2";

   private MainSessionFacade facade;

   public Application(Command startup) {
      facade = new MainSessionFacadeImpl();

      try {
         startup.execute();
         setSystemLookAndFeel();
         invokeMainFrame();
      } catch (Exception e) {
         System.out.println("Fatal error: Database setup failed: " + e);
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
