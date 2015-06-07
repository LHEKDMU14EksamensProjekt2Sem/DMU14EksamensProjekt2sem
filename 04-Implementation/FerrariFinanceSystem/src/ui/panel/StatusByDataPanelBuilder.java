package ui.panel;

import domain.Employee;
import domain.LoanRequest;
import ui.translation.LoanRequestStatusTranslator;

import java.awt.Container;
import java.awt.GridBagConstraints;

public class StatusByDataPanelBuilder extends DataPanelBuilder {
   private static final String PLACEHOLDER = "—";

   public StatusByDataPanelBuilder(Container container, GridBagConstraints gbc) {
      super(container, gbc);
   }

   public void addData(LoanRequest loanRequest) {
      addHeader(LoanRequestStatusTranslator.translate(loanRequest.getStatus()) + " af");

      Employee em = loanRequest.getStatusByEmployee();
      String strFullName = (em == null ? PLACEHOLDER : em.getPerson().getFullName());
      addField("Navn", strFullName);
   }
}
