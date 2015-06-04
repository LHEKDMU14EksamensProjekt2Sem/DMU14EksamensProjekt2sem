package ui.requestloan;

import com.ferrari.finances.dk.rki.Rating;
import exceptions.InvalidCPRException;
import exceptions.ValueRequiredException;
import logic.session.requestloan.RequestLoanFacade;
import logic.util.AssetsUtil;
import ui.UIFactory;
import ui.XTextField;
import util.session.SessionView;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import static java.awt.GridBagConstraints.*;
import static logic.session.requestloan.RequestLoanViewToken.*;
import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class CPRPanel extends JPanel implements SessionView {
   private static final String
           LABEL_CPR = "CPR:",
           BUTTON_SEARCH = "Søg kunde",
           ERR_CPR_REQUIRED = "CPR skal angives",
           ERR_CPR_INVALID = "CPR er ugyldigt";

   private RequestLoanDialog presenter;

   private JLabel lblCPR;
   private XTextField tfCPR;
   private JButton btnSearch;

   public CPRPanel(RequestLoanDialog presenter) {
      this.presenter = presenter;

      setOpaque(false);
      initComponents();
      layoutComponents();
   }

   private void initComponents() {
      RequestLoanFacade facade = presenter.getFacade();

      lblCPR = createLabel(LABEL_CPR);
      tfCPR = createTextField(12);
      tfCPR.setMessageLabel(createLabel());
      tfCPR.restrictInput(facade.getPartialCPRPattern());
      tfCPR.setVerifier(tf -> {
         try {
            facade.validateCPR(tf.getText());
         } catch (ValueRequiredException e) {
            tf.setError(ERR_CPR_REQUIRED);
         } catch (InvalidCPRException e) {
            tf.setError(ERR_CPR_INVALID);
         }
         SwingUtilities.invokeLater(this::updateNavigation);
      });
      tfCPR.setCommitter(tf -> {
         try {
            facade.specifyCPR(tf.getText());
         } catch (ValueRequiredException e) {
            tf.setError(ERR_CPR_REQUIRED);
         } catch (InvalidCPRException e) {
            tf.setError(ERR_CPR_INVALID);
         }
         SwingUtilities.invokeLater(this::updateNavigation);
      });
      tfCPR.addActionListener(e -> {
         tfCPR.commit();
         fetchCreditRating();
      });

      // TODO REMOVE
//      tfCPR.setText("1504619887");

      btnSearch = UIFactory.createButton(BUTTON_SEARCH);
      btnSearch.addActionListener(e -> fetchCreditRating());
   }

   private void layoutComponents() {
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.insets = DEFAULT_GBC_INSETS;
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.anchor = EAST;
      add(lblCPR, gbc);

      gbc.gridx++;
      gbc.anchor = WEST;
      add(tfCPR, gbc);

      gbc.gridx++;
      add(btnSearch, gbc);

      gbc.gridx--;
      gbc.gridy++;
      gbc.gridwidth = 2;
      add(tfCPR.getMessageLabel(), gbc);
   }

   private void fetchCreditRating() {
      if (!tfCPR.isVerified())
         return;

      presenter.getFacade().fetchCreditRating(
              r -> {
                 presenter.clearMessage();

                 if (r == Rating.D) {
                    JOptionPane.showMessageDialog(presenter,
                            String.format("Kreditværdighed %s.%nLåneanmodning afvist.", r),
                            "Låneanmodning afvist",
                            JOptionPane.WARNING_MESSAGE);
                    presenter.dispose();
                 } else {
                    String msg = "✓ Kreditværdighed " + r;
                    presenter.setMessage(msg);
                 }
              },
              x -> {
                 String msg = ":( Fejl: Kunne ikke hente kreditværdighed";
                 presenter.setMessage(msg);
              }
      );

      try {
         String msg = "Henter kreditværdighed...";
         ImageIcon icon = AssetsUtil.loadLoaderIcon();
         presenter.setMessage(icon, msg);
      } catch (IOException e) {
         // No-op
      }

      presenter.go(CUSTOMER_DETAILS);
   }

   private void updateNavigation() {
      btnSearch.setEnabled(tfCPR.isVerified());
   }

   @Override
   public void enter() {
      updateNavigation();
      tfCPR.requestFocus();
   }
}
