package logic.session.viewloanoffers;

import domain.LoanOffer;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface LoanOffersController {
   boolean hasSelectedLoanOffer();

   LoanOffer getSelectedLoanOffer();

   void setSelectedLoanOffer(LoanOffer loanOffer);

   void fetchLoanOffers(Consumer<List<LoanOffer>> resultConsumer,
                        Consumer<Throwable> exceptionConsumer);

   void fetchLoanOffer(Consumer<Optional<LoanOffer>> resultConsumer,
                       Consumer<Throwable> exceptionConsumer);

   void exportRepaymentPlan();
}
