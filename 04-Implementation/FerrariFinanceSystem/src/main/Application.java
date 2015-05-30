package main;

import logic.session.main.MainFacade;
import logic.session.main.MainFacadeImpl;
import ui.main.MainFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.util.concurrent.Callable;

public class Application {
   private static final String
           NAME = "Ferrari Finance System",
           VERSION = "0.3";

   private MainFacade facade;

   public Application(Callable<Void> startup) {
      try {
         facade = new MainFacadeImpl();
         startup.call();
         setSystemLookAndFeel();
         invokeMainFrame();
      } catch (Exception e) {
         System.out.println("Fatal error: Database setup failed: " + e);
//         e.printStackTrace();
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
