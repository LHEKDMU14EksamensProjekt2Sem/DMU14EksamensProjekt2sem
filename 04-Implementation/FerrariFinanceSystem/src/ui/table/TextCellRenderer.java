package ui.table;

import ui.UIConstants;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;

public class TextCellRenderer extends DefaultTableCellRenderer {
   @Override
   public Component getTableCellRendererComponent(JTable table, Object value,
                                                  boolean isSelected, boolean hasFocus,
                                                  int row, int column) {
      JLabel comp = (JLabel) super.getTableCellRendererComponent(table, value,
              isSelected, hasFocus, row, column);

      comp.setFont(UIConstants.PLAIN_FONT);
      comp.setBorder(BorderFactory.createCompoundBorder(
              comp.getBorder(), BorderFactory.createEmptyBorder(0, 4, 0, 4)));

      return comp;
   }
}
