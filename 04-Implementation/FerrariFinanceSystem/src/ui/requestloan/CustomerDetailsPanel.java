package ui.requestloan;

import domain.PostalCode;
import exceptions.InvalidEmailException;
import exceptions.InvalidNameException;
import exceptions.InvalidPhoneException;
import exceptions.InvalidPostalCodeException;
import exceptions.InvalidStreetException;
import exceptions.StreetMissingHouseNumberException;
import exceptions.ValueRequiredException;
import logic.session.requestloan.RequestLoanFacade;
import ui.XTextField;
import util.session.SessionView;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.awt.GridBagConstraints.*;
import static logic.session.requestloan.RequestLoanViewToken.*;
import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class CustomerDetailsPanel extends JPanel implements SessionView {
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
           ERR_POSTAL_CODE_NOT_FOUND = "Postnummer findes ikke",
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

   private XTextField
           tfFirstName, tfLastName,
           tfStreet, tfPostalCode,
           tfPhone, tfEmail;

   private Set<XTextField> fieldSet;

   private JButton btnNext;

   private boolean forwardScheduled;
   private boolean hasValidPostalCode;

   public CustomerDetailsPanel(RequestLoanDialog presenter) {
      this.presenter = presenter;

      setOpaque(false);
      initComponents();
      layoutComponents();

      fieldSet = new HashSet<>(
              Arrays.asList(
                      tfFirstName, tfLastName,
                      tfStreet, tfPostalCode,
                      tfPhone, tfEmail
              ));

      updateNavigation();
   }

   private void initComponents() {
      RequestLoanFacade facade = presenter.getFacade();

      lblFirstName = createLabel(LABEL_FIRST_NAME);
      tfFirstName = createTextField(18);
      tfFirstName.setMessageLabel(createLabel());
      tfFirstName.setVerifier(tf -> {
         try {
            facade.validateFirstName(tf.getText());
         } catch (InvalidNameException e) {
            tf.setError(ERR_FIRST_NAME_INVALID);
         } catch (ValueRequiredException e) {
            tf.setError(ERR_FIRST_NAME_REQUIRED);
         }
         SwingUtilities.invokeLater(this::updateNavigation);
      });
      tfFirstName.setCommitter(tf -> {
         try {
            facade.specifyFirstName(tf.getText());
         } catch (InvalidNameException e) {
            tf.setError(ERR_FIRST_NAME_INVALID);
         } catch (ValueRequiredException e) {
            tf.setError(ERR_FIRST_NAME_REQUIRED);
         }
         SwingUtilities.invokeLater(this::updateNavigation);
      });
      addDefaultActionListener(tfFirstName);

      lblLastName = createLabel(LABEL_LAST_NAME);
      tfLastName = createTextField(18);
      tfLastName.setMessageLabel(createLabel());
      tfLastName.setVerifier(tf -> {
         try {
            facade.validateLastName(tf.getText());
         } catch (InvalidNameException e) {
            tf.setError(ERR_LAST_NAME_INVALID);
         } catch (ValueRequiredException e) {
            tf.setError(ERR_LAST_NAME_REQUIRED);
         }
         SwingUtilities.invokeLater(this::updateNavigation);
      });
      tfLastName.setCommitter(tf -> {
         try {
            facade.specifyLastName(tf.getText());
         } catch (InvalidNameException e) {
            tf.setError(ERR_LAST_NAME_INVALID);
         } catch (ValueRequiredException e) {
            tf.setError(ERR_LAST_NAME_REQUIRED);
         }
         SwingUtilities.invokeLater(this::updateNavigation);
      });
      addDefaultActionListener(tfLastName);

      lblStreet = createLabel(LABEL_STREET);
      tfStreet = createTextField(18);
      tfStreet.setMessageLabel(createLabel());
      tfStreet.setVerifier(tf -> {
         try {
            facade.validateStreet(tf.getText());
         } catch (InvalidStreetException e) {
            tf.setError(ERR_STREET_INVALID);
         } catch (StreetMissingHouseNumberException e) {
            tf.setError(ERR_STREET_MISSING_HOUSE_NUMBER);
         } catch (ValueRequiredException e) {
            tf.setError(ERR_STREET_REQUIRED);
         }
         SwingUtilities.invokeLater(this::updateNavigation);
      });
      tfStreet.setCommitter(tf -> {
         try {
            facade.specifyStreet(tf.getText());
         } catch (InvalidStreetException e) {
            tf.setError(ERR_STREET_INVALID);
         } catch (StreetMissingHouseNumberException e) {
            tf.setError(ERR_STREET_MISSING_HOUSE_NUMBER);
         } catch (ValueRequiredException e) {
            tf.setError(ERR_STREET_REQUIRED);
         }
         SwingUtilities.invokeLater(this::updateNavigation);
      });
      addDefaultActionListener(tfStreet);

      lblPostalCode = createLabel(LABEL_POSTAL_CODE);
      tfPostalCode = createTextField(4);
      tfPostalCode.setCommitDelayed(true);
      tfPostalCode.setMessageLabel(createLabel());
      tfPostalCode.setVerifier(tf -> {
         try {
            facade.validatePostalCode(tf.getText());
         } catch (InvalidPostalCodeException e) {
            tf.setError(ERR_POSTAL_CODE_INVALID);
         } catch (ValueRequiredException e) {
            tf.setError(ERR_POSTAL_CODE_REQUIRED);
         }
         SwingUtilities.invokeLater(this::updateNavigation);
      });
      tfPostalCode.setCommitter(tf -> {
         try {
            hasValidPostalCode = false;
            lblCity.setText(null);

            facade.specifyPostalCode(
                    tf.getText(),
                    this::handlePostalCodeResult,
                    this::handlePostalCodeException);
         } catch (InvalidPostalCodeException e) {
            tf.setError(ERR_POSTAL_CODE_INVALID);
         } catch (ValueRequiredException e) {
            tf.setError(ERR_POSTAL_CODE_REQUIRED);
         }
         SwingUtilities.invokeLater(this::updateNavigation);
      });
      addDefaultActionListener(tfPostalCode);

      lblCity = createLabel();

      lblPhone = createLabel(LABEL_PHONE);
      tfPhone = createTextField(9);
      tfPhone.setMessageLabel(createLabel());
      tfPhone.restrictInput(facade.getPartialPhonePattern());
      tfPhone.setVerifier(tf -> {
         try {
            facade.validatePhone(tf.getText());
         } catch (InvalidPhoneException e) {
            tf.setError(ERR_PHONE_INVALID);
         } catch (ValueRequiredException e) {
            tf.setError(ERR_PHONE_REQUIRED);
         }
         SwingUtilities.invokeLater(this::updateNavigation);
      });
      tfPhone.setCommitter(tf -> {
         try {
            facade.specifyPhone(tf.getText());
         } catch (InvalidPhoneException e) {
            tf.setError(ERR_PHONE_INVALID);
         } catch (ValueRequiredException e) {
            tf.setError(ERR_PHONE_REQUIRED);
         }
         SwingUtilities.invokeLater(this::updateNavigation);
      });
      addDefaultActionListener(tfPhone);

      lblEmail = createLabel(LABEL_EMAIL);
      tfEmail = createTextField(18);
      tfEmail.setMessageLabel(createLabel());
      tfEmail.setVerifier(tf -> {
         try {
            facade.validateEmail(tf.getText());
         } catch (InvalidEmailException e) {
            tf.setError(ERR_EMAIL_INVALID);
         } catch (ValueRequiredException e) {
            tf.setError(ERR_EMAIL_REQUIRED);
         }
         SwingUtilities.invokeLater(this::updateNavigation);
      });
      tfEmail.setCommitter(tf -> {
         try {
            facade.specifyEmail(tf.getText());
         } catch (InvalidEmailException e) {
            tf.setError(ERR_EMAIL_INVALID);
         } catch (ValueRequiredException e) {
            tf.setError(ERR_EMAIL_REQUIRED);
         }
         SwingUtilities.invokeLater(this::updateNavigation);
      });
      // TODO: Should be same as btnNext click
      addDefaultActionListener(tfEmail);

      btnNext = createButton(BUTTON_NEXT);
      btnNext.addActionListener(e -> next());

      // TODO REMOVE
//      tfFirstName.setText("John");
//      tfLastName.setText("Doe");
//      tfStreet.setText("Vejlealle 34");
//      tfPostalCode.setText("9000");
//      tfPhone.setText("12345678");
//      tfEmail.setText("e@mail.com");
   }

   private void addDefaultActionListener(JTextField tf) {
      tf.addActionListener(e -> tf.transferFocus());
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
      addNext(tfFirstName.getMessageLabel(), gbc);
      addNext(tfLastName, gbc);
      addNext(tfLastName.getMessageLabel(), gbc);
      addNext(tfStreet, gbc);
      addNext(tfStreet.getMessageLabel(), gbc);

      gbc.gridwidth = 1;
      addNext(tfPostalCode, gbc);
      gbc.gridx++;
      add(lblCity, gbc);

      gbc.gridx--;
      gbc.gridwidth = 2;
      addNext(tfPostalCode.getMessageLabel(), gbc);
      addNext(tfPhone, gbc);
      addNext(tfPhone.getMessageLabel(), gbc);
      addNext(tfEmail, gbc);
      addNext(tfEmail.getMessageLabel(), gbc);

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

   private void updateNavigation() {
      forwardScheduled = false;

      boolean canContinue = true;
      for (XTextField tf : fieldSet) {
         if (!tf.isVerified() && !tf.isCommitting()) {
            canContinue = false;
            break;
         }
      }

      btnNext.setEnabled(canContinue);
   }

   private void next() {
      // Make sure we have a valid postal code,
      // or schedule forward action if it is under way
      if (hasValidPostalCode) {
         forward();
      } else {
         // Schedule forward action
         forwardScheduled = true;

         // Move focus to window so that when we
         // disable the button, the next component
         // in the focus cycle won't receive focus
         requestFocusInWindow();

         btnNext.setEnabled(false);
      }
   }

   private void forward() {
      presenter.go(REQUEST_DETAILS);
   }

   private void handlePostalCodeResult(Optional<PostalCode> result) {
      if (result.isPresent()) {
         hasValidPostalCode = true;
         lblCity.setText(result.get().getCity());
         tfPostalCode.confirmCommit();

         // Perform forward action if scheduled
         if (forwardScheduled)
            forward();
      } else {
         tfPostalCode.setError(ERR_POSTAL_CODE_NOT_FOUND);

         // Grab focus if forward action was attempted
         if (forwardScheduled)
            tfPostalCode.requestFocus();
      }

      updateNavigation();
   }

   private void handlePostalCodeException(Throwable e) {
      tfPostalCode.setError("Fejl: Kunne ikke validere postnummer");
      updateNavigation();
   }

   @Override
   public void enter() {
      SwingUtilities.invokeLater(tfFirstName::requestFocus);
   }
}
