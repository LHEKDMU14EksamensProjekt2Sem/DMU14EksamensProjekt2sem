package logic.session.main;

import logic.session.requestloan.RequestLoanFacade;
import util.session.SessionFacade;

public interface MainFacade extends
        SessionFacade<MainView>,
        MainController,
        LoginController {
   RequestLoanFacade newRequestLoanFacade();
}
