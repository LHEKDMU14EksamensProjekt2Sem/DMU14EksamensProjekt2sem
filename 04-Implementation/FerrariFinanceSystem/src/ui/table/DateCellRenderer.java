package ui.table;

import logic.format.GeneralDateFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Component;
import java.time.LocalDate;

public class DateCellRenderer extends TextCellRenderer {
   private final GeneralDateFormat format;

   public DateCellRenderer(GeneralDateFormat format) {
      this.format = format;
   }

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
                                                  boolean isSelected, boolean hasFocus,
                                                  int row, int column) {
      JLabel comp = (JLabel) super.getTableCellRendererComponent(table, value,
              isSelected, hasFocus, row, column);

      comp.setHorizontalAlignment(JLabel.RIGHT);
      return comp;
   }

   @Override
   protected void setValue(Object value) {
      setText(format.formatShortDate((LocalDate) value));
   }
}
