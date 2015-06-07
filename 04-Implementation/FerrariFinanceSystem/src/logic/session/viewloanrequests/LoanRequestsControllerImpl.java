package logic.session.viewloanrequests;

import com.ferrari.finances.dk.rki.Rating;
import domain.LoanOffer;
import domain.LoanRequest;
import domain.LoanRequestStatus;
import domain.Person;
import logic.calculator.AnnuityCalculator;
import logic.calculator.InterestRateCalculator;
import logic.calculator.RepaymentPlanner;
import logic.calculator.TermUnit;
import logic.command.CreateLoanOfferCommand;
import logic.command.FetchIdentityCommand;
import logic.command.FetchLoanRequestCommand;
import logic.command.FetchLoanRequestsCommand;
import logic.command.UpdateLoanRequestStatusCommand;
import logic.session.main.MainFacade;
import util.swing.SwingCommand;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class LoanRequestsControllerImpl implements LoanRequestsController {
   private final ViewLoanRequestsFacade facade;
   private final MainFacade mainFacade;

   private final InterestRateCalculator interestRateCalculator;
   private final AnnuityCalculator annuityCalculator;

   private Optional<LoanRequest> selectedLoanRequest;
   private Optional<Rating> creditRating;
   private Optional<Double> overnightRate;

   public LoanRequestsControllerImpl(ViewLoanRequestsFacade facade, MainFacade mainFacade) {
      this.facade = facade;
      this.mainFacade = mainFacade;

      interestRateCalculator = new InterestRateCalculator();
      annuityCalculator = new AnnuityCalculator();

      selectedLoanRequest = Optional.empty();
      creditRating = Optional.empty();
      overnightRate = Optional.empty();
   }

   @Override
   public boolean hasSelectedLoanRequest() {
      return selectedLoanRequest.isPresent();
   }

   @Override
   public LoanRequest getSelectedLoanRequest() {
      return selectedLoanRequest.get();
   }

   @Override
   public void setSelectedLoanRequest(LoanRequest loanRequest) {
      selectedLoanRequest = Optional.ofNullable(loanRequest);
   }

   @Override
   public void setCreditRating(Rating rating) {
      creditRating = Optional.of(rating);
   }

   @Override
   public boolean hasAcceptedCreditRating() {
      return (creditRating.isPresent() && creditRating.get() != Rating.D);
   }

   @Override
   public boolean hasOvernightRate() {
      return overnightRate.isPresent();
   }

   @Override
   public void fetchLoanRequests(LoanRequestStatus status,
                                 Consumer<List<LoanRequest>> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) {
      new SwingCommand<>(
              new FetchLoanRequestsCommand(status),
              resultConsumer,
              exceptionConsumer
      ).execute();
   }

   @Override
   public void fetchLoanRequest(Consumer<Optional<LoanRequest>> resultConsumer,
                                Consumer<Throwable> exceptionConsumer) {
      new SwingCommand<>(
              new FetchLoanRequestCommand(selectedLoanRequest.get().getId()),
              r -> {
                 selectedLoanRequest = r;
                 resultConsumer.accept(r);
              },
              exceptionConsumer
      ).execute();
   }

   @Override
   public void fetchCreditRating(Consumer<Rating> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) {
      Person person = selectedLoanRequest.get().getSale().getCustomer().getPerson();

      new SwingCommand<>(
              new FetchIdentityCommand(person),
              identity ->
                      mainFacade.fetchCreditRating(
                              identity,
                              rating -> {
                                 creditRating = Optional.of(rating);
                                 resultConsumer.accept(rating);
                              },
                              exceptionConsumer::accept),
              exceptionConsumer::accept
      ).execute();
   }

   @Override
   public void fetchOvernightRate(Consumer<Double> resultConsumer,
                                  Consumer<Throwable> exceptionConsumer) {
      mainFacade.fetchOvernightRate(
              r -> {
                 overnightRate = Optional.of(r);
                 resultConsumer.accept(r);
              },
              exceptionConsumer::accept);
   }

   @Override
   public void approveLoanRequest(Consumer<Void> resultConsumer,
                                  Consumer<Throwable> exceptionConsumer) {
      updateLoanRequestStatus(LoanRequestStatus.APPROVED);
      LoanOffer offer = createLoanOffer();
      new SwingCommand<>(
              new CreateLoanOfferCommand(offer),
              resultConsumer,
              exceptionConsumer
      ).execute();
   }

   @Override
   public void declineLoanRequest(Consumer<Void> resultConsumer,
                                  Consumer<Throwable> exceptionConsumer) {
      updateLoanRequestStatus(LoanRequestStatus.DECLINED);
      new SwingCommand<>(
              new UpdateLoanRequestStatusCommand(selectedLoanRequest.get()),
              resultConsumer,
              exceptionConsumer
      ).execute();
   }

   private void updateLoanRequestStatus(LoanRequestStatus newStatus) {
      LoanRequest lr = selectedLoanRequest.get();
      lr.setStatus(newStatus);
      lr.setStatusByEmployee(mainFacade.getUser().getEntity());
   }

   private LoanOffer createLoanOffer() {
      LoanRequest lr = selectedLoanRequest.get();
      LoanOffer lo = new LoanOffer();
      lo.setId(lr.getId());
      lo.setLoanRequest(lr);
      lo.setDate(LocalDate.now());
      lo.setPrincipal(lr.getLoanAmount());

      int term = 0;
      double interestRate = 0;

      if (lr.hasPreferredPayment()) {
         // Customer requests a specific monthly payment.
         // Try interest rate with minimum term.
         interestRate = computeInterestRate(1);
         double r;
         do {
            term = annuityCalculator.computeTerm(
                    lo.getPrincipal(), interestRate, lr.getPreferredPayment());

            if (term == 0) {
               // Requested payment is less than required
               // to repay the principal plus interest
               break;
            }

            r = interestRate;
            interestRate = computeInterestRate(term);
         } while (r != interestRate);
      }

      if (term == 0 || term > 240) {
         term = lr.getPreferredTerm();
         interestRate = computeInterestRate(term);
      }

      lo.setInterestRate(interestRate);

      // Payments start on the 1st of the second month from now
      LocalDate startsOn = LocalDate.now().withDayOfMonth(1).plusMonths(2);

      RepaymentPlanner planner = new RepaymentPlanner(new AnnuityCalculator(), TermUnit.MONTH);
      lo.setPayments(planner.listPaymentsFor(lo, term, startsOn));

      return lo;
   }

   private double computeInterestRate(int term) {
      return interestRateCalculator.computeInterestRate(
              overnightRate.get(), creditRating.get(),
              selectedLoanRequest.get().getDownPaymentPct(), term);
   }
}
