package logic.session.main;

import logic.session.requestloan.RequestLoanFacade;
import util.session.SessionFacade;

import java.util.concurrent.Executor;

public interface MainFacade extends
        SessionFacade<MainView>,
        LoginController {
   RequestLoanFacade newRequestLoanFacade();
}
