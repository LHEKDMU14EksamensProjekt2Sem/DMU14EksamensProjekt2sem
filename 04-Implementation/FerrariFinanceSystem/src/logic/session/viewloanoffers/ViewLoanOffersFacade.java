package logic.session.viewloanoffers;

import logic.format.GeneralDateFormat;
import logic.format.GeneralNumberFormat;
import util.session.SessionFacade;

public interface ViewLoanOffersFacade extends
        SessionFacade<ViewLoanOffersViewToken>,
        LoanOffersController {
   GeneralNumberFormat getGeneralNumberFormat();

   GeneralDateFormat getGeneralDateFormat();
}
