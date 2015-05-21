package logic.session.main;

import logic.session.requestloan.RequestLoanSessionFacade;
import util.session.SessionFacade;

import java.util.concurrent.Executor;

public interface MainSessionFacade extends
        SessionFacade<MainView>, LoginController {
   Executor getExecutor();

   RequestLoanSessionFacade getRequestLoanSessionFacade();
}
