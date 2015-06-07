package ui.panel;

import domain.LoanOffer;
import logic.format.GeneralDateFormat;
import logic.format.GeneralNumberFormat;

import java.awt.Container;
import java.awt.GridBagConstraints;

public class OfferDataPanelBuilder extends DataPanelBuilder {
   private static final String PLACEHOLDER = "—";

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

      String
              strMonthlyPayment = PLACEHOLDER,
              strTerm = PLACEHOLDER,
              strDateOfFirstPayment = PLACEHOLDER,
              strDateOfLastPayment = PLACEHOLDER;

      if (loanOffer.getTerm() > 0) {
         strMonthlyPayment = "DKK " + numberFormat.formatAmount(loanOffer.getMonthlyPayment());
         strTerm = loanOffer.getTerm() + " mdr.";
         strDateOfFirstPayment = dateFormat.formatLongDate(loanOffer.getDateOfFirstPayment());
         strDateOfLastPayment = dateFormat.formatLongDate(loanOffer.getDateOfLastPayment());
      }

      addField("Id", loanOffer.getId());
      addField("Oprettet", dateFormat.formatLongDate(loanOffer.getDate()));
      addField("Hovedstol", "DKK " + numberFormat.formatAmount(loanOffer.getPrincipal()));
      addField("Lånerente (ÅOP)", numberFormat.formatPercent(loanOffer.getInterestRate()) + " %");
      addField("Månedlig ydelse", strMonthlyPayment);
      addField("Løbetid", strTerm);
      addField("Første ydelse", strDateOfFirstPayment);
      addField("Sidste ydelse", strDateOfLastPayment);
   }
}
