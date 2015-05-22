package ui.main;

import logic.session.main.MainFacade;
import logic.session.main.MainView;
import logic.session.requestloan.RequestLoanFacade;
import ui.requestloan.RequestLoanDialog;
import util.session.SessionPresenter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;

import static java.awt.GridBagConstraints.*;
import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class MainMenuPanel extends JPanel {
   private SessionPresenter<MainView, MainFacade> presenter;

   private JLabel lblCurrentUser;
   private JButton btnRequestLoan;

   public MainMenuPanel(SessionPresenter<MainView, MainFacade> presenter) {
      this.presenter = presenter;

      setOpaque(false);
      initComponents();
      layoutComponents();
   }

   private void initComponents() {
      lblCurrentUser = createLabel(" ");

      btnRequestLoan = createButton("Anmod om lån");
      btnRequestLoan.addActionListener(e -> {
         String title = "Anmod om lån";
         RequestLoanFacade facade = presenter.getFacade().getRequestLoanSessionFacade();
         new RequestLoanDialog((Window) presenter, facade, title).setVisible(true);
      });
   }

   private void layoutComponents() {
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.insets = DEFAULT_GBC_INSETS;
      gbc.gridx = 0;
      gbc.gridy = -1;
      gbc.anchor = EAST;
      addNext(lblCurrentUser, gbc);

      JPanel grid = new JPanel(new GridLayout(2, 2));
      grid.setOpaque(false);
      grid.add(btnRequestLoan);

      gbc.anchor = CENTER;
      addNext(grid, gbc);
   }

   private void addNext(Component comp, GridBagConstraints gbc) {
      gbc.gridy++;
      add(comp, gbc);
   }

   public void update() {
      String fullName = presenter.getFacade().getUser().getEntity().getPerson().getFullName();
      lblCurrentUser.setText(fullName);
   }
}
