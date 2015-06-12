package logic.session.viewloanoffers;

import domain.LoanOffer;
import domain.RepaymentPlanPayment;
import logic.command.FetchLoanOfferCommand;
import logic.command.FetchLoanOffersCommand;
import logic.session.main.MainFacade;
import util.csv.CSVField;
import util.csv.CSVFormatter;
import util.io.FileIO;
import util.swing.SwingCommand;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class LoanOffersControllerImpl implements LoanOffersController {
   private static final String EXT_CSV = ".csv";

   private final ViewLoanOffersFacade facade;
   private final MainFacade mainFacade;

   private Optional<LoanOffer> selectedLoanOffer;

   public LoanOffersControllerImpl(ViewLoanOffersFacade facade, MainFacade mainFacade) {
      this.facade = facade;
      this.mainFacade = mainFacade;

      selectedLoanOffer = Optional.empty();
   }

   @Override
   public boolean hasSelectedLoanOffer() {
      return selectedLoanOffer.isPresent();
   }

   @Override
   public LoanOffer getSelectedLoanOffer() {
      return selectedLoanOffer.get();
   }

   @Override
   public void setSelectedLoanOffer(LoanOffer loanOffer) {
      selectedLoanOffer = Optional.ofNullable(loanOffer);
   }

   @Override
   public void fetchLoanOffers(Consumer<List<LoanOffer>> resultConsumer,
                               Consumer<Throwable> exceptionConsumer) {
      new SwingCommand<>(
              new FetchLoanOffersCommand(),
              resultConsumer,
              exceptionConsumer
      ).execute();
   }

   @Override
   public void fetchLoanOffer(Consumer<Optional<LoanOffer>> resultConsumer,
                              Consumer<Throwable> exceptionConsumer) {
      new SwingCommand<>(
              new FetchLoanOfferCommand(selectedLoanOffer.get().getId()),
              r -> {
                 selectedLoanOffer = r;
                 resultConsumer.accept(r);
              },
              exceptionConsumer
      ).execute();
   }

   @Override
   public void exportRepaymentPlan(File file) throws IOException {
      // Make sure file has .csv extension
      if (!file.getName().endsWith(EXT_CSV))
         file = new File(file.getPath() + EXT_CSV);

      CSVFormatter<RepaymentPlanPayment> formatter = new CSVFormatter<>();

      formatter.addField(new CSVField<>("Date", RepaymentPlanPayment::getDate));
      formatter.addField(new CSVField<>("Payment", RepaymentPlanPayment::getAmount));
      formatter.addField(new CSVField<>("Principal paid", RepaymentPlanPayment::getRepayment));
      formatter.addField(new CSVField<>("Interest paid", RepaymentPlanPayment::getInterest));
      formatter.addField(new CSVField<>("Ending balance", RepaymentPlanPayment::getEndingBalance));

      FileIO.writeFile(file, formatter.format(getSelectedLoanOffer().getPayments()));
   }
}
