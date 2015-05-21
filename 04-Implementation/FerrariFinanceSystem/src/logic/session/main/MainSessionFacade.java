package logic.session.main;

import logic.session.requestloan.RequestLoanSessionFacade;
import util.session.SessionFacade;

public interface MainSessionFacade extends
        SessionFacade<MainView>, LoginController {
   RequestLoanSessionFacade getRequestLoanSessionFacade();
}
