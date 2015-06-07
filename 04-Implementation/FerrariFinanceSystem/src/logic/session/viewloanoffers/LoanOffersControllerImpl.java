package logic.session.viewloanoffers;

import domain.LoanOffer;
import logic.command.FetchLoanOfferCommand;
import logic.command.FetchLoanOffersCommand;
import logic.session.main.MainFacade;
import util.swing.SwingCommand;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class LoanOffersControllerImpl implements LoanOffersController {
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
   public void exportRepaymentPlan() {
      // TODO
   }
}
