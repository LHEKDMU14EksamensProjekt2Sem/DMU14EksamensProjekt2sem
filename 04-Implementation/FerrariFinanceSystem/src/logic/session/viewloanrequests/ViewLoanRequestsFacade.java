package logic.session.viewloanrequests;

import util.session.SessionFacade;

public interface ViewLoanRequestsFacade extends
        SessionFacade<ViewLoanRequestsViewToken>,
        LoanRequestsController {
}
