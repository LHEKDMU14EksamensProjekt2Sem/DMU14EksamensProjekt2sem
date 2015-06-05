package ui.table;

import logic.format.GeneralNumberFormat;
import util.finance.Money;

public class MoneyCellRenderer extends NumberCellRenderer {
   private final GeneralNumberFormat format;

   public MoneyCellRenderer(GeneralNumberFormat format) {
      this.format = format;
   }

   @Override
   protected void setValue(Object value) {
      setText(format.formatAmount((Money) value));
   }
}
