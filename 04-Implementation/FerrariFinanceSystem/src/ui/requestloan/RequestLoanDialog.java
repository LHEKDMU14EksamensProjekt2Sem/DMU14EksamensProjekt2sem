package ui.requestloan;

import logic.session.requestloan.RequestLoanSessionFacade;
import logic.session.requestloan.RequestLoanView;
import ui.UIFactory;
import util.session.SessionPresenter;
import util.session.UnsupportedViewException;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Window;

import static logic.session.requestloan.RequestLoanView.*;

public class RequestLoanDialog extends JDialog implements
        SessionPresenter<RequestLoanView, RequestLoanSessionFacade> {

   private RequestLoanSessionFacade facade;

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
      ((JPanel) getContentPane()).setBorder(UIFactory.createContentBorder());

      cprPanel = new CPRPanel(this);
      customerDetailsPanel = new CustomerDetailsPanel(this);
      requestDetailsPanel = new RequestDetailsPanel(this);
   }

   private void layoutComponents() {
      layout = new CardLayout();
      setLayout(layout);

      add(cprPanel);
      layout.addLayoutComponent(cprPanel, CPR.toString());

      add(customerDetailsPanel);
      layout.addLayoutComponent(customerDetailsPanel, CUSTOMER_DETAILS.toString());

      add(requestDetailsPanel);
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
      layout.show(getContentPane(), view.toString());
   }

   @Override
   public RequestLoanSessionFacade getFacade() {
      return facade;
   }
}
