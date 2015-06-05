package logic.session.viewloanrequests;

import domain.LoanRequest;
import logic.command.FetchLoanRequestsCommand;
import util.swing.SwingCommand;

import java.util.List;
import java.util.function.Consumer;

public class LoanRequestsControllerImpl implements LoanRequestsController {
   private final ViewLoanRequestsFacade facade;

   public LoanRequestsControllerImpl(ViewLoanRequestsFacade facade) {
      this.facade = facade;
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
}
