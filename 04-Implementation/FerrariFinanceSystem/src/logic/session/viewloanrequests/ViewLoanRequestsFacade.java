package logic.session.viewloanrequests;

import logic.format.GeneralDateFormat;
import logic.format.GeneralNumberFormat;
import util.session.SessionFacade;

public interface ViewLoanRequestsFacade extends
        SessionFacade<ViewLoanRequestsViewToken>,
        LoanRequestsController {
   GeneralNumberFormat getGeneralNumberFormat();

   GeneralDateFormat getGeneralDateFormat();
}
