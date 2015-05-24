package logic.command;

import domain.Identity;
import domain.LoanOffer;
import domain.LoanRequest;
import logic.service.LoanRequestServiceImpl;
import util.command.Command;

import java.util.Optional;

public class SubmitLoanRequestCommand implements Command<Optional<LoanOffer>> {
   private final LoanRequest loanRequest;
   private final Identity identity;

   public SubmitLoanRequestCommand(LoanRequest loanRequest, Identity identity) {
      this.loanRequest = loanRequest;
      this.identity = identity;
   }

   @Override
   public Optional<LoanOffer> execute() throws Exception {
      return new LoanRequestServiceImpl()
              .submitLoanRequest(loanRequest, Optional.ofNullable(identity));
   }
}
