package ui.requestloan;

import com.ferrari.finances.dk.rki.Rating;
import logic.session.requestloan.RequestLoanSessionFacade;
import logic.session.requestloan.RequestLoanView;
import logic.util.AssetsUtil;
import util.command.Callback;
import util.session.SessionPresenter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static java.awt.GridBagConstraints.*;
import static logic.session.requestloan.RequestLoanView.*;
import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class CPRPanel extends JPanel {
   private SessionPresenter<RequestLoanView, RequestLoanSessionFacade> presenter;

   private JLabel lblCPR;
   private JTextField tfCPR;

   public CPRPanel(SessionPresenter<RequestLoanView, RequestLoanSessionFacade> presenter) {
      this.presenter = presenter;

      initComponents();
      layoutComponents();
   }

   private void initComponents() {
      lblCPR = createLabel("CPR:");
      tfCPR = createTextField(12);
      // TODO: Use a document filter to restrict input to 0-10 digits

      CreditRatingCallback callback = new CreditRatingCallback((Window) presenter);
      tfCPR.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            SwingUtilities.invokeLater(() -> {
               String cpr = tfCPR.getText();
               if (cpr.length() == 10) {
                  presenter.go(CUSTOMER_DETAILS);
                  presenter.getFacade().specifyCPR(cpr);
                  presenter.getFacade().fetchCreditRating(callback);

                  try {
                     String msg = "Henter kreditværdighed...";
                     ImageIcon icon = AssetsUtil.loadLoaderIcon();
                     ((RequestLoanDialog) presenter).setMessage(icon, msg);
                  } catch (IOException ex) {
                     // No-op
                  }
               }
            });
         }
      });
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
   }

   public void update() {
      // No-op
   }

   private class CreditRatingCallback implements Callback<Rating, Void> {
      private Window parent;

      private CreditRatingCallback(Window parent) {
         this.parent = parent;
      }

      @Override
      public void success(Rating result) {
         ((RequestLoanDialog) presenter).clearMessage();
         System.out.println("Credit rating: " + result);

         if (result == Rating.D) {
            JOptionPane.showMessageDialog(parent,
                    String.format("Kreditværdighed %s.%nLåneanmodning afvist.", result),
                    "Låneanmodning afvist",
                    JOptionPane.WARNING_MESSAGE);
            parent.dispose();
         } else {
            String msg = "✓ Kreditværdighed " + result;
            ((RequestLoanDialog) presenter).setMessage(msg);
         }
      }

      @Override
      public void failure(Void exception) {
         ((RequestLoanDialog) presenter).clearMessage();
         System.out.println("Failed to fetch credit rating: " + exception);
      }
   }
}
