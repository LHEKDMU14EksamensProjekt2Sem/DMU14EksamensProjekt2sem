package logic.session.viewloanrequests;

import domain.LoanRequest;

import java.util.List;
import java.util.function.Consumer;

public interface LoanRequestsController {
   void fetchLoanRequests(Consumer<List<LoanRequest>> resultConsumer,
                          Consumer<Throwable> exceptionConsumer);
}
