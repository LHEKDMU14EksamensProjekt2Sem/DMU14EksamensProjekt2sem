package logic.session.main;

import logic.session.createloanrequest.CreateLoanRequestFacade;
import util.session.SessionFacade;

public interface MainFacade extends
        SessionFacade<MainViewToken>,
        MainController,
        LoginController {
   CreateLoanRequestFacade newCreateLoanRequestFacade();
}
