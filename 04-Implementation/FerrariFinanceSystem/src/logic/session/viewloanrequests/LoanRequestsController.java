package logic.session.viewloanrequests;

import com.ferrari.finances.dk.rki.Rating;
import domain.LoanRequest;

import java.util.List;
import java.util.function.Consumer;

public interface LoanRequestsController {
   boolean hasSelectedLoanRequest();

   LoanRequest getSelectedLoanRequest();

   void setSelectedLoanRequest(LoanRequest loanRequest);

   void fetchLoanRequests(Consumer<List<LoanRequest>> resultConsumer,
                          Consumer<Throwable> exceptionConsumer);

   void fetchCreditRating(Consumer<Rating> resultConsumer,
                          Consumer<Throwable> exceptionConsumer);

   void fetchOvernightRate(Consumer<Double> resultConsumer,
                           Consumer<Throwable> exceptionConsumer);
}
