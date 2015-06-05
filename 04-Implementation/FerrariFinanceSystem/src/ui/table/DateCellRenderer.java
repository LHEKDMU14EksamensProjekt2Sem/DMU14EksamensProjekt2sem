package ui.table;

import logic.format.GeneralDateFormat;

import java.time.LocalDate;

public class DateCellRenderer extends TextCellRenderer {
   private final GeneralDateFormat format;

   public DateCellRenderer(GeneralDateFormat format) {
      this.format = format;
   }

   @Override
   protected void setValue(Object value) {
      setText(format.formatShortDate((LocalDate) value));
   }
}
