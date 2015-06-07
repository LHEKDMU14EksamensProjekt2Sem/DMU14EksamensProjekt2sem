package ui.panel;

import domain.Car;
import domain.CarConfig;
import domain.Sale;
import logic.format.GeneralNumberFormat;

import java.awt.Container;
import java.awt.GridBagConstraints;

public class CarDataPanelBuilder extends DataPanelBuilder {
   private static final String PLACEHOLDER = "—";

   private final GeneralNumberFormat numberFormat;

   public CarDataPanelBuilder(Container container, GridBagConstraints gbc, GeneralNumberFormat numberFormat) {
      super(container, gbc);
      this.numberFormat = numberFormat;
   }

   public void addData(Sale sale) {
      addHeader("Bil");

      String
              strSerialNumber = PLACEHOLDER,
              strConfig = PLACEHOLDER,
              strModel = PLACEHOLDER,
              strSellingPrice = PLACEHOLDER;

      if (sale.hasCar()) {
         Car car = sale.getCar();
         CarConfig config = car.getConfig();

         strSerialNumber = String.valueOf(car.getId());
         strConfig = config.getName();
         strModel = config.getModel().getName();
         strSellingPrice = "DKK " + numberFormat.formatAmount(sale.getSellingPrice());
      }

      addField("Serienummer", strSerialNumber);
      addField("Konfiguration", strConfig);
      addField("Model", strModel);
      addField("Salgspris", strSellingPrice);
   }
}
