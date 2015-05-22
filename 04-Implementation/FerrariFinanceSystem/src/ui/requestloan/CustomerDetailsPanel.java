package ui.requestloan;

import logic.session.requestloan.RequestLoanSessionFacade;
import logic.session.requestloan.RequestLoanView;
import util.session.SessionPresenter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import static java.awt.GridBagConstraints.*;
import static logic.session.requestloan.RequestLoanView.*;
import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class CustomerDetailsPanel extends JPanel {
   private SessionPresenter<RequestLoanView, RequestLoanSessionFacade> presenter;

   private JLabel
           lblFirstName, lblLastName,
           lblStreet, lblPostalCode,
           lblPhone, lblEmail;

   private JTextField
           tfFirstName, tfLastName,
           tfStreet, tfPostalCode,
           tfPhone, tfEmail;

   private JButton btnNext;

   public CustomerDetailsPanel(SessionPresenter<RequestLoanView, RequestLoanSessionFacade> presenter) {
      this.presenter = presenter;

      setOpaque(false);
      initComponents();
      layoutComponents();
   }

   private void initComponents() {
      lblFirstName = createLabel("Fornavn:");
      tfFirstName = createTextField(18);

      lblLastName = createLabel("Efternavn:");
      tfLastName = createTextField(18);

      lblStreet = createLabel("Adresse:");
      tfStreet = createTextField(18);

      lblPostalCode = createLabel("Postnummer:");
      tfPostalCode = createTextField(4);

      lblPhone = createLabel("Telefon:");
      tfPhone = createTextField(11);

      lblEmail = createLabel("Email:");
      tfEmail = createTextField(18);

      btnNext = createButton("Næste >");
      btnNext.addActionListener(e ->
              presenter.go(REQUEST_DETAILS));
   }

   private void layoutComponents() {
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      // Add labels
      gbc.insets = DEFAULT_GBC_INSETS;
      gbc.gridx = 0;
      gbc.gridy = -1;
      gbc.anchor = EAST;
      addNext(lblFirstName, gbc);
      addNext(lblLastName, gbc);
      addNext(lblStreet, gbc);
      addNext(lblPostalCode, gbc);
      addNext(lblPhone, gbc);
      addNext(lblEmail, gbc);

      // Add input fields
      gbc.gridx++;
      gbc.gridy = -1;
      gbc.anchor = WEST;
      addNext(tfFirstName, gbc);
      addNext(tfLastName, gbc);
      addNext(tfStreet, gbc);
      addNext(tfPostalCode, gbc);
      addNext(tfPhone, gbc);
      addNext(tfEmail, gbc);

      gbc.gridx = 0;
      gbc.gridy++;
      gbc.gridwidth = 2;
      gbc.anchor = EAST;
      add(btnNext, gbc);
   }

   private void addNext(Component comp, GridBagConstraints gbc) {
      gbc.gridy++;
      add(comp, gbc);
   }

   public void update() {
      // No-op
   }
}
