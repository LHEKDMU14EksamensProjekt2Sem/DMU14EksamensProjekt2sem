package logic.session.viewloanrequests;

import com.ferrari.finances.dk.rki.Rating;
import domain.LoanRequest;
import domain.LoanRequestStatus;
import domain.Person;
import logic.command.FetchIdentityCommand;
import logic.command.FetchLoanRequestsCommand;
import logic.command.UpdateLoanRequestStatusCommand;
import logic.session.main.MainFacade;
import util.swing.SwingCommand;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class LoanRequestsControllerImpl implements LoanRequestsController {
   private final ViewLoanRequestsFacade facade;
   private final MainFacade mainFacade;

   private Optional<LoanRequest> selectedLoanRequest;
   private Optional<Rating> creditRating;
   private Optional<Double> overnightRate;

   public LoanRequestsControllerImpl(ViewLoanRequestsFacade facade, MainFacade mainFacade) {
      this.facade = facade;
      this.mainFacade = mainFacade;
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
   public boolean hasAcceptedCreditRating() {
      return (creditRating.isPresent() && creditRating.get() != Rating.D);
   }

   @Override
   public boolean hasOvernightRate() {
      return overnightRate.isPresent();
   }

   @Override
   public void fetchLoanRequests(Consumer<List<LoanRequest>> resultConsumer,
                                 Consumer<Throwable> exceptionConsumer) {
      new SwingCommand<>(
              new FetchLoanRequestsCommand(),
              resultConsumer,
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
      LoanRequest lr = selectedLoanRequest.get();
      lr.setStatus(LoanRequestStatus.APPROVED);
      lr.setStatusByEmployee(mainFacade.getUser().getEntity());
      new SwingCommand<>(
              new UpdateLoanRequestStatusCommand(selectedLoanRequest.get()),
              resultConsumer,
              exceptionConsumer
      ).execute();
   }

   @Override
   public void declineLoanRequest(Consumer<Void> resultConsumer,
                                  Consumer<Throwable> exceptionConsumer) {
      LoanRequest lr = selectedLoanRequest.get();
      lr.setStatus(LoanRequestStatus.DECLINED);
      lr.setStatusByEmployee(mainFacade.getUser().getEntity());
      new SwingCommand<>(
              new UpdateLoanRequestStatusCommand(selectedLoanRequest.get()),
              resultConsumer,
              exceptionConsumer
      ).execute();
   }
}
