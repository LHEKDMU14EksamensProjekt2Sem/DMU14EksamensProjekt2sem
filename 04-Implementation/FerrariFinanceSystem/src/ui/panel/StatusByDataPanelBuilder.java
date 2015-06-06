package ui.panel;

import domain.Employee;
import domain.LoanRequestStatus;
import domain.Person;
import ui.translation.LoanRequestStatusTranslator;

import java.awt.Container;
import java.awt.GridBagConstraints;

public class StatusByDataPanelBuilder extends DataPanelBuilder {
   public StatusByDataPanelBuilder(Container container, GridBagConstraints gbc) {
      super(container, gbc);
   }

   public void addStatusByData(LoanRequestStatus status, Employee employee) {
      addHeader(LoanRequestStatusTranslator.translate(status) + " af");

      Person p = employee.getPerson();
      addField("Navn", p.getFullName());
   }
}
