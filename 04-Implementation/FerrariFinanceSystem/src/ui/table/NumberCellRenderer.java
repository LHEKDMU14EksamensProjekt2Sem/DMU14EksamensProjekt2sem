package ui.table;

import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Component;

public class NumberCellRenderer extends TextCellRenderer {
   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
                                                  boolean isSelected, boolean hasFocus,
                                                  int row, int column) {
      JLabel comp = (JLabel) super.getTableCellRendererComponent(table, value,
              isSelected, hasFocus, row, column);

      comp.setHorizontalAlignment(JLabel.RIGHT);
      return comp;
   }
}
