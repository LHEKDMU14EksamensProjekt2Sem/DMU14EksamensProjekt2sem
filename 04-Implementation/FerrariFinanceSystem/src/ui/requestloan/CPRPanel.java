package ui.requestloan;

import logic.session.requestloan.RequestLoanSessionFacade;
import logic.session.requestloan.RequestLoanView;
import util.session.SessionPresenter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
      tfCPR.addKeyListener(new KeyAdapter() {
         @Override
         public void keyTyped(KeyEvent e) {
            if (tfCPR.getText().length() == 9)
               presenter.go(CUSTOMER_DETAILS);
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
}
