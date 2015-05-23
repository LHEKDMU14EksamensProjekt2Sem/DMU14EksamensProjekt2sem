package ui.main;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import static java.awt.GridBagConstraints.*;
import static logic.session.main.MainView.*;
import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class LoginPanel extends JPanel {
   private MainFrame presenter;

   private JLabel lblUsername, lblPassword, lblMessage;
   private JTextField tfUsername;
   private JPasswordField pfPassword;
   private JButton btnLogin;

   public LoginPanel(MainFrame presenter) {
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
      btnLogin.addActionListener(e -> login());
   }

   private void layoutComponents() {
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      // Add labels
      gbc.insets = new Insets(8, 8, 8, 8);
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

      gbc.gridx++;
      gbc.gridwidth = 1;
      gbc.anchor = WEST;
      addNext(btnLogin, gbc);
   }

   private void addNext(Component comp, GridBagConstraints gbc) {
      gbc.gridy++;
      add(comp, gbc);
   }

   public void update() {
      // No-op
   }

   private void login() {
      presenter.getFacade().login(tfUsername.getText(), pfPassword.getPassword(),
              r -> {
                 if (r.isPresent())
                    presenter.go(MAIN_MENU);
                 else
                    lblMessage.setText("Brugernavn eller adgangskode er forkert");
              },
              x -> lblMessage.setText("Der er desværre sket en fejl. Prøv igen senere.")
      );
   }
}
