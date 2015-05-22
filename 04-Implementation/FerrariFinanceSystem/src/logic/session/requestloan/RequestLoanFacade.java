package logic.session.requestloan;

import util.session.SessionFacade;

import java.util.concurrent.Executor;

public interface RequestLoanFacade extends
        SessionFacade<RequestLoanView>, CPRController, RequestDetailsController {
   Executor getExecutor();
}
