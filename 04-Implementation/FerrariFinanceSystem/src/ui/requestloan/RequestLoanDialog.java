package ui.requestloan;

import logic.session.requestloan.RequestLoanSessionFacade;
import logic.session.requestloan.RequestLoanView;
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
import static logic.session.requestloan.RequestLoanView.*;

public class RequestLoanDialog extends JDialog implements
        SessionPresenter<RequestLoanView, RequestLoanSessionFacade> {

   private RequestLoanSessionFacade facade;
   private JPanel contentPanel;
   private JLabel messageLabel;

   private CardLayout layout;
   private CPRPanel cprPanel;
   private CustomerDetailsPanel customerDetailsPanel;
   private RequestDetailsPanel requestDetailsPanel;

   public RequestLoanDialog(Window owner, RequestLoanSessionFacade facade, String title) {
      super(owner, title, ModalityType.APPLICATION_MODAL);
      this.facade = facade;

      setLocationRelativeTo(owner);
      setResizable(false);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      initComponents();
      layoutComponents();
      pack();
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
   }

   @Override
   public void go(RequestLoanView view) {
      switch (view) {
         case CPR:
            cprPanel.update();
            break;
         case CUSTOMER_DETAILS:
            customerDetailsPanel.update();
            break;
         case REQUEST_DETAILS:
            requestDetailsPanel.update();
            break;
         default:
            throw new UnsupportedViewException(view);
      }

      facade.setView(view);
      layout.show(contentPanel, view.toString());
   }

   @Override
   public RequestLoanSessionFacade getFacade() {
      return facade;
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
