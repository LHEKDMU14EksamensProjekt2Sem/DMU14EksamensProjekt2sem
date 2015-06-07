package ui.panel;

import domain.Customer;
import domain.Person;

import java.awt.Container;
import java.awt.GridBagConstraints;

public class CustomerDataPanelBuilder extends DataPanelBuilder {
   public CustomerDataPanelBuilder(Container container, GridBagConstraints gbc) {
      super(container, gbc);
   }

   public void addData(Customer customer) {
      addHeader("Kunde");

      Person p = customer.getPerson();
      addField("Navn", p.getFullName());
      addField("Adresse", p.getStreet());
      addField(null, p.getPostalCode());
      addField("Telefon", p.getPhone());
      addField("Email", p.getEmail());
   }
}
