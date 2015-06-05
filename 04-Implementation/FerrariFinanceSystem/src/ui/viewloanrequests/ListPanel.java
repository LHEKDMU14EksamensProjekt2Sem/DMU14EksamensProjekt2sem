package ui.viewloanrequests;

import domain.LoanRequest;
import util.session.SessionView;

import javax.swing.JPanel;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import static ui.UIConstants.*;
import static ui.UIFactory.*;

public class ListPanel extends JPanel implements SessionView {
   private final ViewLoanRequestsDialog presenter;

   public ListPanel(ViewLoanRequestsDialog presenter) {
      this.presenter = presenter;

      setOpaque(false);
      initComponents();
      layoutComponents();
   }

   private void initComponents() {

   }

   private void layoutComponents() {
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.insets = DEFAULT_GBC_INSETS;
      gbc.gridx = 0;
      gbc.gridy = -1;
      addNext(createLabel("Filter here"), gbc);
      addNext(createLabel("Table here"), gbc);
   }

   private void addNext(Component comp, GridBagConstraints gbc) {
      gbc.gridy++;
      add(comp, gbc);
   }

   private void fetchLoanRequests() {
      presenter.getFacade().fetchLoanRequests(
              this::handleFetchResult,
              this::handleFetchException);
   }

   private void handleFetchResult(List<LoanRequest> result) {
      for (LoanRequest lr : result) {
         System.out.println("Loan request: " + lr.getId() + ", customer: " + lr.getSale().getCustomer().getPerson().getFullName());
      }
   }

   private void handleFetchException(Throwable e) {
      e.printStackTrace();
   }

   @Override
   public void enter() {
      fetchLoanRequests();
   }
}
