package ui.viewloanrequests;

import domain.LoanRequestStatus;
import ui.translation.LoanRequestStatusFilterTranslator;

public enum LoanRequestStatusFilter {
   ALL(null),
   PENDING(LoanRequestStatus.PENDING),
   APPROVED(LoanRequestStatus.APPROVED),
   DECLINED(LoanRequestStatus.DECLINED);

   private LoanRequestStatus status;

   LoanRequestStatusFilter(LoanRequestStatus status) {
      this.status = status;
   }

   public LoanRequestStatus getStatus() {
      return status;
   }

   @Override
   public String toString() {
      return LoanRequestStatusFilterTranslator.translate(this);
   }
}
