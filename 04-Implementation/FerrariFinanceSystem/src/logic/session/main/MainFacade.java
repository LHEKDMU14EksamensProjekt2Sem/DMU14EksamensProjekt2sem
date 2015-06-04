package logic.session.main;

import logic.session.requestloan.RequestLoanFacade;
import util.session.SessionFacade;

public interface MainFacade extends
        SessionFacade<MainViewToken>,
        MainController,
        LoginController {
   RequestLoanFacade newRequestLoanFacade();
}
