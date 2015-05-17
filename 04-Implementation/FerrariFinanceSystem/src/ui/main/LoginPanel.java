package ui.main;

import logic.session.main.MainSessionFacade;
import logic.session.main.MainView;
import util.session.SessionPresenter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import static java.awt.GridBagConstraints.*;
import static logic.session.main.MainView.*;
import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class LoginPanel extends JPanel {
   private SessionPresenter<MainView, MainSessionFacade> presenter;

   private JLabel lblUsername, lblPassword, lblMessage;
   private JTextField tfUsername;
   private JPasswordField pfPassword;
   private JButton btnLogin;

   public LoginPanel(SessionPresenter<MainView, MainSessionFacade> presenter) {
      this.presenter = presenter;

      setOpaque(false);
      initComponents();
      layoutComponents();
   }

   private void initComponents() {
      lblUsername = createLabel("Brugernavn:");
      tfUsername = createTextField(12);

      lblPassword = createLabel("Adgangskode:");
      pfPassword = createPasswordField(12);

      lblMessage = createLabel(" ", BOLD_FONT);
      lblMessage.setForeground(Color.RED);

      btnLogin = createButton("Log ind");
      btnLogin.addActionListener(e -> {
         String username = tfUsername.getText();
         char[] password = pfPassword.getPassword();

         MainSessionFacade facade = presenter.getFacade();
         facade.login(username, password, () -> {
            if (facade.isLoggedIn())
               presenter.go(MAIN_MENU);
            else
               lblMessage.setText("Brugernavn eller adgangskode er forkert :(");
         });
      });
   }

   private void layoutComponents() {
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      // Add labels
      gbc.insets = DEFAULT_GBC_INSETS;
      gbc.gridx = 0;
      gbc.gridy = -1;
      gbc.anchor = EAST;
      addNext(lblUsername, gbc);
      addNext(lblPassword, gbc);

      // Add input fields
      gbc.gridx++;
      gbc.gridy = -1;
      gbc.anchor = WEST;
      addNext(tfUsername, gbc);
      addNext(pfPassword, gbc);

      gbc.gridx = 0;
      gbc.gridwidth = 2;
      gbc.anchor = CENTER;
      addNext(lblMessage, gbc);
      addNext(btnLogin, gbc);
   }

   private void addNext(Component comp, GridBagConstraints gbc) {
      gbc.gridy++;
      add(comp, gbc);
   }

   public void update() {
      // No-op
   }
}
