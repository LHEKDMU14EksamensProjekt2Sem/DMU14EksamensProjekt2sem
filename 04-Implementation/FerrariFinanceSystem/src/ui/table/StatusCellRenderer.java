package ui.table;

import domain.LoanRequestStatus;

public class StatusCellRenderer extends TextCellRenderer {
   @Override
   protected void setValue(Object value) {
      switch ((LoanRequestStatus) value) {
         case PENDING:
            setText("Afventer");
            break;
         case APPROVED:
            setText("Godkendt");
            break;
         case DECLINED:
            setText("Afvist");
            break;
         default:
            setText(value.toString());
      }
   }
}
