package ui.main;

import util.session.SessionView;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import static java.awt.GridBagConstraints.*;
import static logic.session.main.MainViewToken.*;
import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class LoginPanel extends JPanel implements SessionView {
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
      pfPassword.addActionListener(e -> login());

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

   private void login() {
      presenter.getFacade().login(tfUsername.getText(), pfPassword.getPassword(),
              r -> {
                 if (r.isPresent()) {
                    presenter.go(MAIN_MENU);
                 } else {
                    showWarning("Brugernavn eller adgangskode er forkert");
                    reset();
                 }
              },
              x -> {
                 showError("Der er desværre sket en fejl. Prøv igen senere.");
                 x.printStackTrace();
              }
      );
   }

   private void reset() {
      pfPassword.setText(null);
      tfUsername.setText(null);
      tfUsername.requestFocus();

      // TODO REM
      tfUsername.setText("alwu");
      pfPassword.setText("foobar");
   }

   @Override
   public void enter() {
      reset();
   }

   private void showWarning(String message) {
      showDialog(message, "Fejl i login", JOptionPane.WARNING_MESSAGE);
   }

   private void showError(String message) {
      showDialog(message, "Uventet fejl", JOptionPane.ERROR_MESSAGE);
   }

   private void showDialog(String message, String title, int type) {
      JOptionPane.showMessageDialog(presenter, message, title, type);
   }
}
