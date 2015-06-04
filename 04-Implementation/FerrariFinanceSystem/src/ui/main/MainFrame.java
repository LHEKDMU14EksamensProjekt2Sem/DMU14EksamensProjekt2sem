package ui.main;

import logic.session.main.MainFacade;
import logic.session.main.MainViewToken;
import logic.util.AssetsUtil;
import util.session.SessionPresenter;
import util.session.SessionView;
import util.session.UnsupportedViewException;
import util.swing.ImagePanel;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.CardLayout;
import java.awt.Component;
import java.io.IOException;

import static logic.session.main.MainViewToken.*;

public class MainFrame extends JFrame implements
        SessionPresenter<MainFacade, MainViewToken> {

   private MainFacade facade;

   private CardLayout layout;
   private SessionView loginPanel;
   private SessionView mainMenuPanel;

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

      addView(loginPanel, LOGIN);
      addView(mainMenuPanel, MAIN_MENU);
   }

   private void addView(SessionView view, MainViewToken token) {
      add((Component) view);
      layout.addLayoutComponent((Component) view, token.toString());
   }

   @Override
   public MainFacade getFacade() {
      return facade;
   }

   @Override
   public void go(MainViewToken token) {
      switch (token) {
         case LOGIN:
            loginPanel.enter();
            break;
         case MAIN_MENU:
            mainMenuPanel.enter();
            break;
         default:
            throw new UnsupportedViewException(token);
      }

      facade.setViewToken(token);
      layout.show(getContentPane(), token.toString());
   }
}
