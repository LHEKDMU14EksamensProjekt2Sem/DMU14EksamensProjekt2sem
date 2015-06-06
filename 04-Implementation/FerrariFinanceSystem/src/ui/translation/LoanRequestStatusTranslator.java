package ui.translation;

import domain.LoanRequestStatus;

public class LoanRequestStatusTranslator {
   public static String translate(LoanRequestStatus status) {
      switch (status) {
         case PENDING:
            return "Afventer";
         case APPROVED:
            return "Godkendt";
         case DECLINED:
            return "Afvist";
         default:
            return status.toString();
      }
   }
}
