package ui.table;

import domain.LoanRequestStatus;
import ui.translation.LoanRequestStatusTranslator;

public class StatusCellRenderer extends TextCellRenderer {
   @Override
   protected void setValue(Object value) {
      setText(LoanRequestStatusTranslator.translate((LoanRequestStatus) value));
   }
}
