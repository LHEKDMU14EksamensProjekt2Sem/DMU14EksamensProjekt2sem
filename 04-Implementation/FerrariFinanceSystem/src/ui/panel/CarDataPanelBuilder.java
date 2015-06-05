package ui.panel;

import domain.Car;
import domain.CarConfig;
import domain.Sale;
import logic.format.GeneralNumberFormat;

import java.awt.Container;
import java.awt.GridBagConstraints;

public class CarDataPanelBuilder extends DataPanelBuilder {
   private final GeneralNumberFormat numberFormat;

   public CarDataPanelBuilder(Container container, GridBagConstraints gbc, GeneralNumberFormat numberFormat) {
      super(container, gbc);
      this.numberFormat = numberFormat;
   }

   public void addCarData(Sale sale) {
      addHeader("Bil");

      Car car = sale.getCar();
      CarConfig config = car.getConfig();
      addField("Serienummer", car.getId());
      addField("Konfiguration", config.getName());
      addField("Model", config.getModel().getName());
      addField("Salgspris", "DKK " + numberFormat.formatAmount(sale.getSellingPrice()));
   }
}
