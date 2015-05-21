package ui.main;

import logic.session.main.MainSessionFacade;
import logic.session.main.MainView;
import util.session.SessionPresenter;
import util.session.UnsupportedViewException;
import util.swing.ImagePanel;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;

import static java.lang.ClassLoader.*;
import static logic.session.main.MainView.*;
import static ui.UIFactory.*;

public class MainFrame extends JFrame implements
        SessionPresenter<MainView, MainSessionFacade> {

   private MainSessionFacade facade;

   private CardLayout layout;
   private LoginPanel loginPanel;
   private MainMenuPanel mainMenuPanel;

   public MainFrame(MainSessionFacade facade, String title) {
      super(title);
      this.facade = facade;

      setResizable(false);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      initComponents();
      layoutComponents();
      pack();

      // Center on screen
      setLocationRelativeTo(null);
   }

   private void initComponents() {
      // Attempt to load background image
      try {
         Image bg = ImageIO.read(getSystemResourceAsStream("assets/images/bg3.png"));
         setContentPane(new ImagePanel(bg, new Point(-220, -210)));
      } catch (IOException e) {
         // No-op
      }

      ((JPanel) getContentPane()).setBorder(createContentBorder());

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
            loginPanel.update();
            break;
         case MAIN_MENU:
            mainMenuPanel.update();
            break;
         default:
            throw new UnsupportedViewException(view);
      }

      facade.setView(view);
      layout.show(getContentPane(), view.toString());
   }

   @Override
   public MainSessionFacade getFacade() {
      return facade;
   }
}
