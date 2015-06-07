package logic.session.main;

import logic.session.createloanrequest.CreateLoanRequestFacade;
import logic.session.viewloanoffers.ViewLoanOffersFacade;
import logic.session.viewloanrequests.ViewLoanRequestsFacade;
import util.session.SessionFacade;

public interface MainFacade extends
        SessionFacade<MainViewToken>,
        MainController,
        LoginController {
   CreateLoanRequestFacade newCreateLoanRequestFacade();

   ViewLoanRequestsFacade newViewLoanRequestsFacade();

   ViewLoanOffersFacade newViewLoanOffersFacade();
}
