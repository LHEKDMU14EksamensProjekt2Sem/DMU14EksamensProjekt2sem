package logic.session.requestloan;

import util.session.SessionFacade;

import java.util.concurrent.Executor;

public interface RequestLoanSessionFacade extends
        SessionFacade<RequestLoanView>, CPRController, LoanRequestDetailsController {
   Executor getExecutor();

	CustomerDetailsController getCustomerDetailsController();
}
