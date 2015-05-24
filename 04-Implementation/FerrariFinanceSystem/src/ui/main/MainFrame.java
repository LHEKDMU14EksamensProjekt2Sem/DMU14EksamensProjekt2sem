package ui.main;

import logic.session.main.MainFacade;
import logic.session.main.MainView;
import logic.util.AssetsUtil;
import util.session.SessionPresenter;
import util.session.UnsupportedViewException;
import util.swing.ImagePanel;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.CardLayout;
import java.io.IOException;

import static logic.session.main.MainView.*;

public class MainFrame extends JFrame implements
        SessionPresenter<MainView, MainFacade> {

   private MainFacade facade;

   private CardLayout layout;
   private LoginPanel loginPanel;
   private MainMenuPanel mainMenuPanel;

   public MainFrame(MainFacade facade, String title) {
      super(title);
      this.facade = facade;

      setResizable(false);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      initComponents();
      layoutComponents();
      pack();

      // Center on screen
      setLocationRelativeTo(null);
   }

   private void initComponents() {
      // Attempt to load background image
      try {
         setContentPane(new ImagePanel(AssetsUtil.loadBackgroundImage()));
      } catch (IOException e) {
         // No-op
      }

      ((JPanel) getContentPane()).setBorder(
              BorderFactory.createEmptyBorder(60, 30, 30, 30));

      loginPanel = new LoginPanel(this);
      mainMenuPanel = new MainMenuPanel(this);
   }

   private void layoutComponents() {
      layout = new CardLayout();
      setLayout(layout);

      add(loginPanel);
      layout.addLayoutComponent(loginPanel, LOGIN.toString());

      add(mainMenuPanel);
      layout.addLayoutComponent(mainMenuPanel, MAIN_MENU.toString());
   }

   @Override
   public void go(MainView view) {
      switch (view) {
         case LOGIN:
            loginPanel.enter();
            break;
         case MAIN_MENU:
            mainMenuPanel.enter();
            break;
         default:
            throw new UnsupportedViewException(view);
      }

      facade.setView(view);
      layout.show(getContentPane(), view.toString());
   }

   @Override
   public MainFacade getFacade() {
      return facade;
   }
}
