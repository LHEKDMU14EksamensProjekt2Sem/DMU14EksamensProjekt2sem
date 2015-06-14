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
              strAPR = PLACEHOLDER,
              strTotalCost = PLACEHOLDER,
              strMonthlyPayment = PLACEHOLDER,
              strTerm = PLACEHOLDER,
              strDateOfFirstPayment = PLACEHOLDER,
              strDateOfLastPayment = PLACEHOLDER;

      if (loanOffer.getTerm() > 0) {
         strAPR = numberFormat.formatPercent(loanOffer.getAPR()) + " %";
         strTotalCost = "DKK " + numberFormat.formatAmount(loanOffer.getTotalCost());
         strMonthlyPayment = "DKK " + numberFormat.formatAmount(loanOffer.getMonthlyPayment());
         strTerm = loanOffer.getTerm() + " mdr.";
         strDateOfFirstPayment = dateFormat.formatLongDate(loanOffer.getDateOfFirstPayment());
         strDateOfLastPayment = dateFormat.formatLongDate(loanOffer.getDateOfLastPayment());
      }

      addField("Id", loanOffer.getId());
      addField("Oprettet", dateFormat.formatLongDate(loanOffer.getDate()));
      addField("Hovedstol", "DKK " + numberFormat.formatAmount(loanOffer.getPrincipal()));
      addField("Lånerente", numberFormat.formatPercent(loanOffer.getInterestRate()) + " %");
      addField("ÅOP", strAPR);
      addField("Omkostninger", strTotalCost);
      addField("Månedlig ydelse", strMonthlyPayment);
      addField("Løbetid", strTerm);
      addField("Første ydelse", strDateOfFirstPayment);
      addField("Sidste ydelse", strDateOfLastPayment);
   }
}
