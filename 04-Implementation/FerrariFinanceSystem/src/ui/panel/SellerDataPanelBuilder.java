package ui.panel;

import domain.Employee;
import domain.Person;

import java.awt.Container;
import java.awt.GridBagConstraints;

public class SellerDataPanelBuilder extends DataPanelBuilder {
   public SellerDataPanelBuilder(Container container, GridBagConstraints gbc) {
      super(container, gbc);
   }

   public void addSellerData(Employee seller) {
      addHeader("Sælger");

      Person p = seller.getPerson();
      addField("Navn", p.getFullName());
   }
}
