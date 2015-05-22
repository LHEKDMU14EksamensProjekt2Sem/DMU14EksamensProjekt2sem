package ui.requestloan;

import com.ferrari.finances.dk.rki.Rating;
import logic.session.requestloan.RequestLoanFacade;
import logic.session.requestloan.RequestLoanView;
import logic.util.AssetsUtil;
import ui.UIFactory;
import util.session.SessionPresenter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
   private SessionPresenter<RequestLoanView, RequestLoanFacade> presenter;

   private JLabel lblCPR;
   private JTextField tfCPR;
   private JButton btnSearch;

   public CPRPanel(SessionPresenter<RequestLoanView, RequestLoanFacade> presenter) {
      this.presenter = presenter;

      setOpaque(false);
      initComponents();
      layoutComponents();
   }

   private void initComponents() {
      lblCPR = createLabel("CPR:");
      tfCPR = createTextField(12);
      // TODO: Use a document filter to restrict input to 0-10 digits

      tfCPR.addKeyListener(new KeyAdapter() {
         @Override
         public void keyReleased(KeyEvent e) {
            updateSearchButton();
            if (e.getKeyCode() == KeyEvent.VK_ENTER
                    && tfCPR.getText().length() == 10)
               fetchCreditRating();
         }
      });

      btnSearch = UIFactory.createButton("Søg");
      btnSearch.addActionListener(e -> fetchCreditRating());
      updateSearchButton();
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
   }

   public void update() {
      // No-op
   }

   private void updateSearchButton() {
      btnSearch.setEnabled(tfCPR.getText().length() == 10);
   }

   private void fetchCreditRating() {
      presenter.go(CUSTOMER_DETAILS);
      presenter.getFacade().specifyCPR(tfCPR.getText());
      presenter.getFacade().fetchCreditRating(
              r -> {
                 ((RequestLoanDialog) presenter).clearMessage();

                 if (r == Rating.D) {
                    JOptionPane.showMessageDialog((Window) presenter,
                            String.format("Kreditværdighed %s.%nLåneanmodning afvist.", r),
                            "Låneanmodning afvist",
                            JOptionPane.WARNING_MESSAGE);
                    ((Window) presenter).dispose();
                 } else {
                    String msg = "✓ Kreditværdighed " + r;
                    ((RequestLoanDialog) presenter).setMessage(msg);
                 }
              }
      );

      try {
         String msg = "Henter kreditværdighed...";
         ImageIcon icon = AssetsUtil.loadLoaderIcon();
         ((RequestLoanDialog) presenter).setMessage(icon, msg);
      } catch (IOException ex) {
         // No-op
      }
   }
}
