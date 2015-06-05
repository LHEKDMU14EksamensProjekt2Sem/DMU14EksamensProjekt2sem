package ui.panel;

import javax.swing.JLabel;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import static java.awt.GridBagConstraints.*;
import static ui.UIFactory.*;

public class DataPanelBuilder {
   private final Container container;
   private final GridBagConstraints gbc;

   public DataPanelBuilder(Container container, GridBagConstraints gbc) {
      this.container = container;
      this.gbc = gbc;
   }

   public void addHeader(String header) {
      gbc.gridy++;
      gbc.gridwidth = 2;
      gbc.insets = new Insets(12, 0, 6, 0);
      gbc.anchor = WEST;
      container.add(createBoldLabel(header), gbc);
   }

   public void addField(String name, Object value) {
      addField(name, createLabel(value.toString()));
   }

   public void addField(String name, JLabel label) {
      gbc.gridy++;
      gbc.gridwidth = 1;
      gbc.insets = new Insets(4, 12, 4, 4);
      gbc.anchor = EAST;
      if (name != null)
         container.add(createLabel(name + ":"), gbc);

      gbc.gridx++;
      gbc.insets = new Insets(4, 4, 4, 12);
      gbc.anchor = WEST;
      container.add(label, gbc);

      gbc.gridx--;
   }
}
