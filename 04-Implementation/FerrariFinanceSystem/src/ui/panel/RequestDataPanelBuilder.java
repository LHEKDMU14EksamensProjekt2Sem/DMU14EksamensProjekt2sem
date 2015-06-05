package ui.panel;

import domain.LoanRequest;
import logic.format.GeneralDateFormat;
import logic.format.GeneralNumberFormat;

import java.awt.Container;
import java.awt.GridBagConstraints;

public class RequestDataPanelBuilder extends DataPanelBuilder {
   private final GeneralNumberFormat numberFormat;
   private final GeneralDateFormat dateFormat;

   public RequestDataPanelBuilder(Container container, GridBagConstraints gbc,
                                  GeneralNumberFormat numberFormat, GeneralDateFormat dateFormat) {
      super(container, gbc);
      this.numberFormat = numberFormat;
      this.dateFormat = dateFormat;
   }

   public void addRequestData(LoanRequest loanRequest) {
      addHeader("Låneanmodning");

      addField("Id", loanRequest.getId());
      addField("Dato", dateFormat.formatLongDate(loanRequest.getDate()));
      addField("Lånebeløb", "DKK " + numberFormat.formatAmount(loanRequest.getLoanAmount()));
      addField("Udbetaling", "DKK " + numberFormat.formatAmount(loanRequest.getDownPayment()));
      addField("Udbetalingspct.", numberFormat.formatPercent(loanRequest.getDownPaymentPct()) + " %");

      if (loanRequest.hasPreferredPayment())
         addField("Ønsket ydelse", "DKK " + numberFormat.formatAmount(loanRequest.getPreferredPayment()));
      if (loanRequest.hasPreferredTerm())
         addField("Ønsket løbetid", numberFormat.formatInteger(loanRequest.getPreferredTerm()) + " mdr.");

      String status;
      switch (loanRequest.getStatus()) {
         case PENDING:
            status = "Afventer";
            break;
         case APPROVED:
            status = "Godkendt";
            break;
         case DECLINED:
            status = "Afvist";
            break;
         default:
            status = "N/A";
      }
      addField("Status", status);
   }
}
