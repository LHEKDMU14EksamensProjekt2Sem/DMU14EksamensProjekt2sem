package ui.translation;

import ui.viewloanrequests.LoanRequestStatusFilter;

public class LoanRequestStatusFilterTranslator {
   public static String translate(LoanRequestStatusFilter filter) {
      switch (filter) {
         case ALL:
            return "Alle";
         case PENDING:
            return "Afventende";
         case APPROVED:
            return "Godkendte";
         case DECLINED:
            return "Afviste";
         default:
            return filter.toString();
      }
   }
}
