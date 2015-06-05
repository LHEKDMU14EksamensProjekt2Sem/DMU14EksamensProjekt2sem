package ui.table;

import javax.swing.table.AbstractTableModel;

public abstract class TableModelBase extends AbstractTableModel {
   private final String[] columnNames;

   public TableModelBase(String... columnNames) {
      this.columnNames = columnNames;
   }

   @Override
   public String getColumnName(int column) {
      return columnNames[column];
   }

   @Override
   public Class<?> getColumnClass(int columnIndex) {
      return getValueAt(0, columnIndex).getClass();
   }

   @Override
   public int getColumnCount() {
      return columnNames.length;
   }

   public void refresh() {
      fireTableDataChanged();
   }
}
