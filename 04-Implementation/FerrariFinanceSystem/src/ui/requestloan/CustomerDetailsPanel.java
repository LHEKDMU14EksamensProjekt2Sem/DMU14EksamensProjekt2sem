package ui.requestloan;

import exceptions.InvalidEmailException;
import exceptions.InvalidNameException;
import exceptions.InvalidPhoneException;
import exceptions.InvalidPostalCodeException;
import exceptions.InvalidStreetException;
import exceptions.StreetMissingHouseNumberException;
import exceptions.ValueRequiredException;
import logic.session.requestloan.RequestLoanFacade;
import ui.XTextField;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import static java.awt.GridBagConstraints.*;
import static logic.session.requestloan.RequestLoanView.*;
import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class CustomerDetailsPanel extends JPanel {
   private static final String
           LABEL_FIRST_NAME = "Fornavn:",
           LABEL_LAST_NAME = "Efternavn:",
           LABEL_STREET = "Adresse:",
           LABEL_POSTAL_CODE = "Postnummer:",
           LABEL_PHONE = "Telefon:",
           LABEL_EMAIL = "Email:",
           ERR_FIRST_NAME_REQUIRED = "Fornavn skal udfyldes",
           ERR_FIRST_NAME_INVALID = "Fornavn er ugyldigt",
           ERR_LAST_NAME_REQUIRED = "Efternavn skal udfyldes",
           ERR_LAST_NAME_INVALID = "Efternavn er ugyldigt",
           ERR_STREET_REQUIRED = "Adresse skal udfyldes",
           ERR_STREET_INVALID = "Adresse er ugyldig",
           ERR_STREET_MISSING_HOUSE_NUMBER = "Adresse mangler husnummer",
           ERR_POSTAL_CODE_REQUIRED = "Postnummer skal udfyldes",
           ERR_POSTAL_CODE_INVALID = "Postnummer er ugyldigt",
           ERR_PHONE_REQUIRED = "Telefon skal udfyldes",
           ERR_PHONE_INVALID = "Telefon er ugyldig",
           ERR_EMAIL_REQUIRED = "Email skal udfyldes",
           ERR_EMAIL_INVALID = "Email er ugyldig",
           BUTTON_NEXT = "Næste >";

   private RequestLoanDialog presenter;

   private JLabel
           lblFirstName, lblLastName,
           lblStreet, lblPostalCode, lblCity,
           lblPhone, lblEmail;

   private JLabel
           lblFirstNameError, lblLastNameError,
           lblStreetError, lblPostalCodeError,
           lblPhoneError, lblEmailError;

   private XTextField
           tfFirstName, tfLastName,
           tfStreet, tfPostalCode,
           tfPhone, tfEmail;

   private JButton btnNext;

   public CustomerDetailsPanel(RequestLoanDialog presenter) {
      this.presenter = presenter;

      setOpaque(false);
      initComponents();
      layoutComponents();
   }

   private void initComponents() {
      RequestLoanFacade facade = presenter.getFacade();

      lblFirstName = createLabel(LABEL_FIRST_NAME);
      tfFirstName = createTextField(18);
      tfFirstName.setInputVerifier(tf -> {
         String err = "";
         try {
            facade.specifyFirstName(tf.getText());
            tf.setValid(true);
         } catch (InvalidNameException e) {
            err = ERR_FIRST_NAME_INVALID;
         } catch (ValueRequiredException e) {
            err = ERR_FIRST_NAME_REQUIRED;
         }
         lblFirstNameError.setText(err);
         lblFirstNameError.setVisible(!err.isEmpty());
      });

      lblLastName = createLabel(LABEL_LAST_NAME);
      tfLastName = createTextField(18);
      tfLastName.setInputVerifier(tf -> {
         String err = "";
         try {
            facade.specifyLastName(tf.getText());
            tf.setValid(true);
         } catch (InvalidNameException e) {
            err = ERR_LAST_NAME_INVALID;
         } catch (ValueRequiredException e) {
            err = ERR_LAST_NAME_REQUIRED;
         }
         lblLastNameError.setText(err);
         lblLastNameError.setVisible(!err.isEmpty());
      });

      lblStreet = createLabel(LABEL_STREET);
      tfStreet = createTextField(18);
      tfStreet.setInputVerifier(tf -> {
         String err = "";
         try {
            facade.specifyStreet(tf.getText());
            tf.setValid(true);
         } catch (InvalidStreetException e) {
            err = ERR_STREET_INVALID;
         } catch (StreetMissingHouseNumberException e) {
            err = ERR_STREET_MISSING_HOUSE_NUMBER;
         } catch (ValueRequiredException e) {
            err = ERR_STREET_REQUIRED;
         }
         lblStreetError.setText(err);
         lblStreetError.setVisible(!err.isEmpty());
      });

      lblPostalCode = createLabel(LABEL_POSTAL_CODE);
      tfPostalCode = createTextField(4);
      tfPostalCode.setInputVerifier(tf -> {
         String err = "";
         try {
            facade.specifyPostalCode(
                    tf.getText(),
                    r -> {
                       tf.setValid(r.isPresent());
                       lblCity.setText(r.isPresent() ? r.get().getCity() : "");
                    },
                    x -> {
                       lblCity.setText("");
                       lblPostalCodeError.setText("Fejl: Kunne ikke validere postnummer");
                    });
            tf.setDelayedValidation(true);
         } catch (InvalidPostalCodeException e) {
            err = ERR_POSTAL_CODE_INVALID;
         } catch (ValueRequiredException e) {
            err = ERR_POSTAL_CODE_REQUIRED;
         }
         lblPostalCodeError.setText(err);
         lblPostalCodeError.setVisible(!err.isEmpty());
      });

      lblCity = createLabel("");

      lblPhone = createLabel(LABEL_PHONE);
      tfPhone = createTextField(9);
      tfPhone.setInputVerifier(tf -> {
         String err = "";
         try {
            facade.specifyPhone(tf.getText());
            tf.setValid(true);
         } catch (InvalidPhoneException e) {
            err = ERR_PHONE_INVALID;
         } catch (ValueRequiredException e) {
            err = ERR_PHONE_REQUIRED;
         }
         lblPhoneError.setText(err);
         lblPhoneError.setVisible(!err.isEmpty());
      });

      lblEmail = createLabel(LABEL_EMAIL);
      tfEmail = createTextField(18);
      tfEmail.setInputVerifier(tf -> {
         String err = "";
         try {
            facade.specifyEmail(tf.getText());
            tf.setValid(true);
         } catch (InvalidEmailException e) {
            err = ERR_EMAIL_INVALID;
         } catch (ValueRequiredException e) {
            err = ERR_EMAIL_REQUIRED;
         }
         lblEmailError.setText(err);
         lblEmailError.setVisible(!err.isEmpty());
      });

      btnNext = createButton(BUTTON_NEXT);
      btnNext.addActionListener(e -> presenter.go(REQUEST_DETAILS));

      lblFirstNameError = createErrorLabel();
      lblLastNameError = createErrorLabel();
      lblStreetError = createErrorLabel();
      lblPostalCodeError = createErrorLabel();
      lblPhoneError = createErrorLabel();
      lblEmailError = createErrorLabel();

      // TODO REMOVE
//      tfFirstName.setText("John");
//      tfLastName.setText("Doe");
//      tfStreet.setText("Vejlealle 34");
//      tfPostalCode.setText("9000");
//      tfPhone.setText("12345678");
//      tfEmail.setText("e@mail.com");
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
      gbc.gridy++;
      addNext(lblLastName, gbc);
      gbc.gridy++;
      addNext(lblStreet, gbc);
      gbc.gridy++;
      addNext(lblPostalCode, gbc);
      gbc.gridy++;
      addNext(lblPhone, gbc);
      gbc.gridy++;
      addNext(lblEmail, gbc);

      // Add input fields and error labels
      gbc.gridx++;
      gbc.gridy = -1;
      gbc.gridwidth = 2;
      gbc.anchor = WEST;
      addNext(tfFirstName, gbc);
      addNext(lblFirstNameError, gbc);
      addNext(tfLastName, gbc);
      addNext(lblLastNameError, gbc);
      addNext(tfStreet, gbc);
      addNext(lblStreetError, gbc);

      gbc.gridwidth = 1;
      addNext(tfPostalCode, gbc);
      gbc.gridx++;
      add(lblCity, gbc);

      gbc.gridx--;
      gbc.gridwidth = 2;
      addNext(lblPostalCodeError, gbc);
      addNext(tfPhone, gbc);
      addNext(lblPhoneError, gbc);
      addNext(tfEmail, gbc);
      addNext(lblEmailError, gbc);

      gbc.gridx = 0;
      gbc.gridy++;
      gbc.gridwidth = 3;
      gbc.anchor = EAST;
      add(btnNext, gbc);
   }

   private void addNext(Component comp, GridBagConstraints gbc) {
      gbc.gridy++;
      add(comp, gbc);
   }

   public void enter() {
      // No-op
   }
}
