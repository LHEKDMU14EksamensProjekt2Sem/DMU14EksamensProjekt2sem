package ui.panel;

import domain.LoanOffer;
import logic.format.GeneralDateFormat;
import logic.format.GeneralNumberFormat;

import java.awt.Container;
import java.awt.GridBagConstraints;

public class OfferDataPanelBuilder extends DataPanelBuilder {
   private final GeneralNumberFormat numberFormat;
   private final GeneralDateFormat dateFormat;

   public OfferDataPanelBuilder(Container container, GridBagConstraints gbc,
                                GeneralNumberFormat numberFormat, GeneralDateFormat dateFormat) {
      super(container, gbc);
      this.numberFormat = numberFormat;
      this.dateFormat = dateFormat;
   }

   public void addData(LoanOffer loanOffer) {
      addHeader("Lånetilbud");

      addField("Id", loanOffer.getId());
      addField("Oprettet", dateFormat.formatLongDate(loanOffer.getDate()));
      addField("Hovedstol", "DKK " + numberFormat.formatAmount(loanOffer.getPrincipal()));
      addField("Lånerente (ÅOP)", numberFormat.formatPercent(loanOffer.getInterestRate()) + " %");
      addField("Månedlig ydelse", "DKK " + numberFormat.formatAmount(loanOffer.getMonthlyPayment()));
      addField("Løbetid", numberFormat.formatInteger(loanOffer.getTerm()) + " mdr.");
      addField("Første ydelse", dateFormat.formatLongDate(loanOffer.getDateOfFirstPayment()));
      addField("Sidste ydelse", dateFormat.formatLongDate(loanOffer.getDateOfLastPayment()));
   }
}
