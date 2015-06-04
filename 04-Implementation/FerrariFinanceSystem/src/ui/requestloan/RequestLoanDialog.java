package ui.requestloan;

import logic.session.requestloan.RequestLoanFacade;
import logic.session.requestloan.RequestLoanViewToken;
import logic.util.AssetsUtil;
import ui.UIFactory;
import util.session.SessionPresenter;
import util.session.UnsupportedViewException;
import util.swing.ImagePanel;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.io.IOException;

import static java.awt.GridBagConstraints.*;
import static logic.session.requestloan.RequestLoanViewToken.*;

public class RequestLoanDialog extends JDialog implements
        SessionPresenter<RequestLoanFacade, RequestLoanViewToken> {

   private RequestLoanFacade facade;
   private JPanel contentPanel;
   private JLabel messageLabel;

   private CardLayout layout;
   private CPRPanel cprPanel;
   private CustomerDetailsPanel customerDetailsPanel;
   private RequestDetailsPanel requestDetailsPanel;

   public RequestLoanDialog(Window owner, RequestLoanFacade facade, String title) {
      super(owner, title, ModalityType.APPLICATION_MODAL);
      this.facade = facade;

      setResizable(false);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      initComponents();
      layoutComponents();
      pack();

      // Center on screen
      setLocationRelativeTo(null);
   }

   private void initComponents() {
      // Attempt to load background image
      try {
         setContentPane(new ImagePanel(AssetsUtil.loadBackgroundImage()));
      } catch (IOException e) {
         // No-op
      }

      layout = new CardLayout();
      contentPanel = new JPanel(layout);
      contentPanel.setOpaque(false);
      contentPanel.setBorder(UIFactory.createContentBorder());
      messageLabel = UIFactory.createLabel("");

      cprPanel = new CPRPanel(this);
      customerDetailsPanel = new CustomerDetailsPanel(this);
      requestDetailsPanel = new RequestDetailsPanel(this);
   }

   private void layoutComponents() {
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.weightx = 1;
      gbc.fill = HORIZONTAL;
      gbc.anchor = WEST;
      gbc.insets = new Insets(20, 12, 20, 12);
      add(messageLabel, gbc);

      gbc.gridy++;
      gbc.anchor = CENTER;
      add(contentPanel, gbc);

      contentPanel.add(cprPanel);
      layout.addLayoutComponent(cprPanel, CPR.toString());

      contentPanel.add(customerDetailsPanel);
      layout.addLayoutComponent(customerDetailsPanel, CUSTOMER_DETAILS.toString());

      contentPanel.add(requestDetailsPanel);
      layout.addLayoutComponent(requestDetailsPanel, REQUEST_DETAILS.toString());
   @Override
   public RequestLoanFacade getFacade() {
      return facade;
   }

   @Override
   public void go(RequestLoanViewToken token) {
      switch (token) {
         case CPR:
            cprPanel.enter();
            break;
         case CUSTOMER_DETAILS:
            customerDetailsPanel.enter();
            break;
         case REQUEST_DETAILS:
            requestDetailsPanel.enter();
            break;
         default:
            throw new UnsupportedViewException(token);
      }

      facade.setViewToken(token);
      layout.show(contentPanel, token.toString());
   }

   public void setMessage(String message) {
      setMessage(null, message);
   }

   public void setMessage(ImageIcon icon, String message) {
      messageLabel.setText(message);
      messageLabel.setIcon(icon);
   }

   public void clearMessage() {
      messageLabel.setText(" ");
      messageLabel.setIcon(null);
   }
}
