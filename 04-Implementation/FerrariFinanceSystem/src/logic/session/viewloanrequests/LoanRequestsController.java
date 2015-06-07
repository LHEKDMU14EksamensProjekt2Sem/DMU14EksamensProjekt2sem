package logic.session.viewloanrequests;

import com.ferrari.finances.dk.rki.Rating;
import domain.LoanRequest;
import domain.LoanRequestStatus;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface LoanRequestsController {
   boolean hasSelectedLoanRequest();

   LoanRequest getSelectedLoanRequest();

   void setSelectedLoanRequest(LoanRequest loanRequest);

   void setCreditRating(Rating rating);

   boolean hasAcceptedCreditRating();

   boolean hasOvernightRate();

   void fetchLoanRequests(LoanRequestStatus status,
                          Consumer<List<LoanRequest>> resultConsumer,
                          Consumer<Throwable> exceptionConsumer);

   void fetchLoanRequest(Consumer<Optional<LoanRequest>> resultConsumer,
                         Consumer<Throwable> exceptionConsumer);

   void fetchCreditRating(Consumer<Rating> resultConsumer,
                          Consumer<Throwable> exceptionConsumer);

   void fetchOvernightRate(Consumer<Double> resultConsumer,
                           Consumer<Throwable> exceptionConsumer);

   void approveLoanRequest(Consumer<Void> resultConsumer,
                           Consumer<Throwable> exceptionConsumer);

   void declineLoanRequest(Consumer<Void> resultConsumer,
                           Consumer<Throwable> exceptionConsumer);
}
